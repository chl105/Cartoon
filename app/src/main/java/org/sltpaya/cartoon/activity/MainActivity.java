package org.sltpaya.cartoon.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.fragment.main.CartoonFragment;
import org.sltpaya.cartoon.fragment.main.CollectFragment;
import org.sltpaya.cartoon.fragment.main.MineFragment;
import org.sltpaya.cartoon.fragment.main.NovelFragment;
import org.sltpaya.cartoon.fragment.main.SquareFragment;
import org.sltpaya.cartoon.view.ExitDialog;
import org.sltpaya.tablayout.XTabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ArrayList<Fragment> alreadyAddFragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        initData();
        initViews();
    }

    private void initData() {
        fragments.add(new CartoonFragment());
        fragments.add(new NovelFragment());
        fragments.add(new CollectFragment());
        fragments.add(new SquareFragment());
        fragments.add(new MineFragment());
    }

    private void initViews() {
        bottomNavigationInit();
    }

    /**
     * 底部栏相关处理
     */
    private void bottomNavigationInit() {
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        final List<RadioButton> buttons = new ArrayList<>(5);
        final int[] radioButtonIds = {
                R.id.radio_cartoon,
                R.id.radio_novel,
                R.id.radio_collect,
                R.id.radio_square,
                R.id.radio_mine
        };
        for (int id : radioButtonIds) {
            RadioButton button = (RadioButton) findViewById(id);
            buttons.add(button);
        }
        showButton(0, buttons);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int index = 0;
                for (int i = 0; i < radioButtonIds.length; i++) {
                    if (radioButtonIds[i] == checkedId) {
                        index = i;
                    }
                }
                showButton(index, buttons);
            }
        });
    }

    private void showButton(int index, List<RadioButton> list) {
        for (int i = 0; i < list.size(); i++) {
            if (i != index) {
                list.get(i).setSelected(false);
            }
        }
        list.get(index).setSelected(true);
        initFragment(index);
    }

    /**
     * 初始化所有的Fragment，显示和隐藏，添加，每点击一个添加一个Fragment.
     *
     * @param index {@link #fragments}中对应的Fragment
     */
    private void initFragment(int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment nowFragment = fragments.get(index);

        boolean isAdd = alreadyAddFragments.contains(nowFragment);
        if (!isAdd) {
            transaction.add(R.id.fragment_container, nowFragment).show(nowFragment);
            alreadyAddFragments.add(nowFragment);
        }
        transaction.show(nowFragment);

        for (int i = 0; i < fragments.size(); i++) {
            if (i != index) {
                transaction.hide(fragments.get(i));
            }
        }

        transaction.commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            showDialog();
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 显示退出的时候弹出对话框确认
     */
    private void showDialog() {
        ExitDialog dialog = new ExitDialog(this);
        Window dialogWindow = dialog.getWindow();
        if (dialogWindow != null) {
            WindowManager.LayoutParams attributes = dialogWindow.getAttributes();
            attributes.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            attributes.dimAmount = 0.8f;//值越高，背影越暗
            dialogWindow.setAttributes(attributes);
        }
        dialog.show();
        dialog.setNegativeButton(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.setPositiveButton(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                clearActivity();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        System.exit(0);
        android.os.Process.killProcess(android.os.Process.myPid());//结束掉进程，释放空间
    }
}
