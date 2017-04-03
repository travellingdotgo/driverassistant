package com.bewant2be.doit.driverassitant;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bewant2be.doit.utilslib.ToastUtil;

/**
 * Created by user on 4/3/17.
 */
public class Querry {

    public final static String TAG = "Querry";

    public static void query(final Context context, final String province,final String licencePlate,final String enginID){

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
                    view.loadUrl("javascript:window.local_obj.showSource('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');");
                }

                @Override
                public void onReceivedError(WebView view, int errorCode,
                                            String description, String failingUrl) {
                    super.onReceivedError(view, errorCode, description, failingUrl);

                    TTS.play( context, "加载信息异常, onReceivedError");
                }

            });

            /**
             *  android sdk api >= 17 时需要加@JavascriptInterface
             */
            final class InJavaScriptLocalObj {
                @JavascriptInterface
                public void showSource(String html) {
                    int index = html.indexOf("共");
                    Log.e(TAG, "index: " + index);
                    int index2 = html.indexOf("条记录");
                    Log.e(TAG, "index2: " + index2);

                    final String txt = html.substring(index+1, index2);
                    Log.i(TAG, txt + "  " + Thread.currentThread().getName());

                    TTS.play(context, "你有" + txt + "条违章记录, 我替你感到难过哦");

                    ToastUtil.toastComptible(context, "Records: " + txt);
                }
            }

            WebSettings settings = webview.getSettings();
            settings.setJavaScriptEnabled(true);
            webview.addJavascriptInterface(new InJavaScriptLocalObj(), "local_obj");

            String postData = Config.urlEncodePara( province, licencePlate,enginID) ;
            Log.v(TAG, "postData: " + postData);
            webview.postUrl(Config.sxgaUrl, postData.getBytes());

        }catch (Exception e){

        }

    }

}
