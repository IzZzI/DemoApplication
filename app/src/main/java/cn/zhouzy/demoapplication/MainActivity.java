package cn.zhouzy.demoapplication;

import android.app.Activity;
import android.os.SystemClock;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity implements TextToSpeech.OnInitListener {


    /**
     * 文本转语音API
     */
    private TextToSpeech mTextToSpeech;
    /**
     * TTS是否初始化
     */
    private boolean isTTSReady = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //初始化文本转语音API
        initTTS();
    }

    /**
     * 初始化文本转语音API
     */
    private void initTTS() {
        mTextToSpeech = new TextToSpeech(this, this);
    }

    @OnClick({R.id.main_btn_start})
    void onClick(View v){
        switch (v.getId()){
            //启动任务
            case R.id.main_btn_start:
                start();
                break;

            default:
                break;
        }
    }

    /**
     * 启动任务
     */
    private void start() {
       if (isTTSReady){
           mTextToSpeech.speak("123", TextToSpeech.QUEUE_FLUSH, null);
           Log.e("zhouzy", "speak is ok");
       }else {
           Toast.makeText(this, "tts is not ready", Toast.LENGTH_SHORT).show();
       }
    }

    /**
     * 实现TextToSpeech.OnInitListener接口的方法
     * @param status
     */
    @Override
    public void onInit(int status) {
        Log.e("zhouzy", "status ：" + status);
        if (status == TextToSpeech.SUCCESS){
            //tts初始化成功
            isTTSReady = true;
            //设置语言为中文
            mTextToSpeech.setLanguage(Locale.CHINA);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mTextToSpeech.stop();
        mTextToSpeech.shutdown();
    }
}
