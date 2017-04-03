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

import com.bewant2be.doit.utilslib.ToastUtil;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class ExtReceiver extends BroadcastReceiver {

    public final static String TAG = "ExtReceiver";

    @Override
    public void onReceive(final Context context, Intent intent) {
        Log.i(TAG, "ExtReceiver onReceive: " + intent.getAction());

        SharedPreferences pref = context.getSharedPreferences( Config.PREF_ITEM, context.MODE_PRIVATE);
        String province = pref.getString(Config.PREF_ITEM_TYPE_PROVINCE, Config.VALUE_DEFAULT);
        String licencePlate = pref.getString(Config.PREF_ITEM_TYPE_LICENCEPLATE, Config.VALUE_DEFAULT);
        String enginID  = pref.getString(Config.PREF_ITEM_TYPE_ENGINEID,Config.VALUE_DEFAULT);

        if (        Config.VALUE_DEFAULT.equals(province)
                ||  Config.VALUE_DEFAULT.equals(licencePlate)
                ||  Config.VALUE_DEFAULT.equals(enginID)   ){
            Log.e(TAG, "Config error");
            TTS.play(context, "来吧 配置一下参数吧");

            Intent intentUI = new Intent(context, ConfigParaActivity.class);
            intentUI.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intentUI);
        }

        Querry.query(context, province, licencePlate, enginID);
    }
}