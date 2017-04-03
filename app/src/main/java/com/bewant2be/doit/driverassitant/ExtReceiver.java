package com.bewant2be.doit.driverassitant;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class ExtReceiver extends BroadcastReceiver {

    public final static String TAG = "ExtReceiver";

    @Override
    public void onReceive(final Context context, Intent intent) {
        Log.i(TAG, "ExtReceiver onReceive: " + intent.getAction());

        WebView webview = new WebView( context.getApplicationContext() );

        try {
            webview.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                }

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    view.loadUrl("javascript:window.local_obj.showSource('<head>'+"
                            + "document.getElementsByTagName('html')[0].innerHTML+'</head>');");
                }

                @Override
                public void onReceivedError(WebView view, int errorCode,
                                            String description, String failingUrl) {
                    super.onReceivedError(view, errorCode, description, failingUrl);
                }

            });




            /**
             *  android sdk api >= 17 时需要加@JavascriptInterface
             * @author fei
             *
             */
            final class InJavaScriptLocalObj {
                @JavascriptInterface
                public void showSource(String html) {
                    int index = html.indexOf("共");
                    Log.e(TAG, "index: " + index);
                    int index2 = html.indexOf("条记录");
                    Log.e(TAG, "index2: " + index2);

                    final String txt = "Records: " +  html.subSequence(index+1, index2);
                    Log.e(TAG, txt + "  " + Thread.currentThread().getName());

                    MediaPlayer mp = MediaPlayer.create(context.getApplicationContext(), R.raw.violation);
                    mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.reset();
                            mp.release();
                        }
                    });
                    mp.start();


                    Handler handler = new Handler(Looper.getMainLooper());
                    Runnable r = new Runnable(){
                        @Override
                        public void run() {
                            Toast.makeText(context,txt,Toast.LENGTH_SHORT).show();
                        }
                    };

                    handler.post(r);
                }
            }

            WebSettings settings = webview.getSettings();
            settings.setJavaScriptEnabled(true);
            webview.addJavascriptInterface(new InJavaScriptLocalObj(), "local_obj");

            SharedPreferences pref = context.getSharedPreferences("config", context.MODE_PRIVATE);
            String licencePlate = pref.getString("licencePlate", "default");
            String enginID  = pref.getString("enginID","default");

            String postData = Config.urlEncodePara(licencePlate,enginID) ;
            Log.v(TAG, "postData: " + postData);
            webview.postUrl(Config.sxgaUrl, postData.getBytes());

        }catch (Exception e){

        }


    }
}