package com.bewant2be.doit.driverassitant;

import android.util.Log;

import java.net.URLEncoder;

/**
 * Created by user on 4/3/17.
 */
public class Config {
    private static final String TAG = "Config";
    public static final String sxgaUrl = "http://wscgs.sxga.gov.cn/wap/vehvio.do";

    public static String urlEncodePara( String licencePlate, String enginId ){
        try {
            String params = "hpzl=02"
                    + "&fzjgjc="+ "%D5%E3"  // URLEncoder.encode("浙", "UTF-8") // %E6%B5%99
                    + "&hphm=" + URLEncoder.encode(licencePlate, "UTF-8") // "hpzl=02&fzjgjc=%D5%E3&
                    + "&fdjh=" + URLEncoder.encode(enginId, "UTF-8");
            return params;
        }
        catch (Exception e){
            Log.e(TAG, "urlEncodePara error");
        }

        return null;
    }
}
