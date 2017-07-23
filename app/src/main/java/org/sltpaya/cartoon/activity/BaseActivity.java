package org.sltpaya.cartoon.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import org.sltpaya.tool.Utils;

/**
 * Author: SLTPAYA
 * Date: 2017/4/1
 */
public class BaseActivity extends AnimationActivity {

    private Utils.ToolApplication application;
    private Activity activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (Utils.ToolApplication) getApplication();
        activity = this;
        application.addActivity(activity);
    }

    public void clearActivity() {
        application.clearActivity();
    }

    public void removeActivity() {
        application.removeActivity(activity);
    }

}
