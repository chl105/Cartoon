package org.sltpaya.cartoon.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.fragment.main.CartoonFragment;
import org.sltpaya.cartoon.fragment.main.CollectFragment;
import org.sltpaya.cartoon.fragment.main.MineFragment;
import org.sltpaya.cartoon.fragment.main.NovelFragment;
import org.sltpaya.cartoon.fragment.main.SquareFragment;
import org.sltpaya.cartoon.view.ExitDialog;
import org.sltpaya.tablayout.XTabLayout;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private XTabLayout mTabLayout;
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
        bottomNavigationEvent();
    }

    private void initData() {
        fragments.add(new CartoonFragment());
        fragments.add(new NovelFragment());
        fragments.add(new CollectFragment());
        fragments.add(new SquareFragment());
        fragments.add(new MineFragment());
    }

    private void initViews() {
        tabLayoutInit();
    }

    private void tabLayoutInit() {
        mTabLayout = (XTabLayout) findViewById(R.id.main_navigation);
        int[] resId = {
                R.drawable.hp_selector,
                R.drawable.bs_selector,
                R.drawable.collect_selector,
                R.drawable.sq_selector,
                R.drawable.mine_selector
        };
        for (int aResId : resId) {
            XTabLayout.Tab tab = mTabLayout.newTab();
            tab.setCustomView(createTabView(aResId));
            mTabLayout.addTab(tab);
        }
        setDefault(0);
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

    private void bottomNavigationEvent() {
        mTabLayout.addOnTabSelectedListener(new XTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(XTabLayout.Tab tab) {
                initFragment(tab.getPosition());
            }

            @Override
            public void onTabUnselected(XTabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(XTabLayout.Tab tab) {
            }
        });
    }

    private void setDefault(int defaultIndex) {
        int count = mTabLayout.getTabCount();
        for (int i = 0; i < count; i++) {
            XTabLayout.Tab tab = mTabLayout.getTabAt(i);
            if (tab != null && i == defaultIndex) {
                tab.select();
            }
        }
        initFragment(0);
    }

    private View createTabView(int resId) {
        LayoutInflater inflater = getLayoutInflater();
        LinearLayout layout = new LinearLayout(this);
        View view = inflater.inflate(R.layout.item_bottom, layout, false);
        ImageView img = (ImageView) view.findViewById(R.id.bottom_tab);
        img.setImageResource(resId);
        return view;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
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
    }

}
