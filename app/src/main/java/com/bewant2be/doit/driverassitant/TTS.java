package com.bewant2be.doit.driverassitant;

import android.content.Context;
import android.os.Bundle;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.SynthesizerListener;

/**
 * Created by user on 4/3/17.
 */
public class TTS {


    public static SynthesizerListener mTtsListener = new SynthesizerListener() {
        public void onSpeakBegin() {

        }

        public void onBufferProgress(int i, int i1, int i2, String s) {

        }

        public void onSpeakPaused() {

        }

        public void onSpeakResumed() {

        }

        public void onSpeakProgress(int i, int i1, int i2) {

        }

        public void onCompleted(SpeechError speechError) {

        }

        public void onEvent(int i, int i1, int i2, Bundle bundle) {

        }
    };

    public static void play( Context context, String str){

        SpeechUtility.createUtility(context, SpeechConstant.APPID + "=58e193f9");
        //1.创建 SpeechSynthesizer 对象, 第二个参数：本地合成时传 InitListener
        SpeechSynthesizer mTts=SpeechSynthesizer.createSynthesizer(context, null);
        //2.合成参数设置
        mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoxin");
        mTts.setParameter(SpeechConstant.SPEED, "50");
        mTts.setParameter(SpeechConstant.VOLUME, "100");
        mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD); //设置云端
        mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, "./sdcard/iflytek.pcm");
        //开始合成
        mTts.startSpeaking( str,  mTtsListener);
    }


}
