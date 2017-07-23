package org.sltpaya.cartoon.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import org.sltpaya.cartoon.R;

/**
 * Author: SLTPAYA
 * Date: 2017/7/18
 */
public class AnimationActivity extends AppCompatActivity {

    private static final int FLAG_START = 0;
    private static final int FLAG_FINISH = 1;


    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(FLAG_START);
    }

    @Override
    public void startActivity(Intent intent, @Nullable Bundle options) {
        super.startActivity(intent, options);
        overridePendingTransition(FLAG_START);
    }

    /**
     * activity切换动画
     *
     * @param flag {@link #FLAG_START} {@link #FLAG_FINISH}
     */
    public void overridePendingTransition(int flag) {
        if (flag == FLAG_START) {
            overridePendingTransition(R.anim.slide_right_in, R.anim.slide_remain);
        } else if (flag == FLAG_FINISH) {
            overridePendingTransition(R.anim.slide_remain, R.anim.slide_right_out);
        }
    }

    /**
     * 决定activity finish的时候是否显示动画
     * @param showTransition boolean
     */
    public void finish(boolean showTransition) {
        if (showTransition) {
            finish();
        } else {
            super.finish();
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(FLAG_FINISH);
    }

}
