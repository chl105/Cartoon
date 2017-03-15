package org.sltpaya.cartoon.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.net.cache.RecommendCache;
import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        initViews();
    }

    private void initViews() {
        requestNet();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
               enterActivity();
            }
        }, 2000);
    }

    private void enterActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * 为了给用户更好的体验，在这里，将会在闪屏页开始异步请求推荐页面数据.
     */
    private void requestNet() {
        RecommendCache cache = RecommendCache.newInstance();
        if (cache.getData() == null) {
            cache.requestNet();
        }
    }

    /**
     * 判断网络状态
     */
    private boolean netWorkState() {
        return false;
    }

}