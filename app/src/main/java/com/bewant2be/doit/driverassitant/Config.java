package com.bewant2be.doit.driverassitant;

import android.util.Log;

import java.net.URLEncoder;

/**
 * Created by user on 4/3/17.
 */
public class Config {
    private static final String TAG = "Config";
    public static final String sxgaUrl = "http://wscgs.sxga.gov.cn/wap/vehvio.do";

    public final static String PREF_ITEM ="config";
    public final static String VALUE_DEFAULT = "default";

    public final static String PREF_ITEM_TYPE_PROVINCE ="province";
    public final static String PREF_ITEM_TYPE_LICENCEPLATE ="licencePlate";
    public final static String PREF_ITEM_TYPE_ENGINEID ="enginID";

    public static String urlEncodePara( String province, String licencePlate, String enginId ){
        try {
            String params = "hpzl=02"
                    + "&fzjgjc="+ URLEncoder.encode(province, "gb2312")
                    + "&hphm=" + URLEncoder.encode(licencePlate, "UTF-8")
                    + "&fdjh=" + URLEncoder.encode(enginId, "UTF-8");
            return params;
        }
        catch (Exception e){
            Log.e(TAG, "urlEncodePara error");
        }

        return null;
    }
}
