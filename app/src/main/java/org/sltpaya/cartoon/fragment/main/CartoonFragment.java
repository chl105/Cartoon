package org.sltpaya.cartoon.fragment.main;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import org.sltpaya.cartoon.adapter.pager.CartoonPagerAdapter;
import org.sltpaya.cartoon.fragment.cartoon.BarCartoonFragment;
import org.sltpaya.cartoon.fragment.cartoon.ClassifyFragment;
import org.sltpaya.cartoon.fragment.cartoon.RankTabFragment;
import org.sltpaya.cartoon.fragment.cartoon.RecommendFragment;

import java.util.ArrayList;

/**
 * Author: SLTPAYA
 * Date: 2017/2/20
 */
public class CartoonFragment extends NovelCartFragment {

    @Override
    protected void setPagerAdapter() {
        String[] titles = {"条 漫", "推 荐", "分 类", "排 行"};
        mPager.setAdapter(new CartoonPagerAdapter(getChildFragmentManager(), titles, getFragments()));
    }

    /**
     * 设置默认显示的Tab
     */
    @Override
    protected void setDefault() {
        String[] titles = {"条 漫", "推 荐", "分 类", "排 行"};
        int count = mTabLayout.getTabCount();
        for (int i = 0; i < count; i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            if (tab != null) {
                TextView text = new TextView(getActivity());
                text.setText(titles[i]);
                text.setTextSize(15);
                tab.setCustomView(text);
            }
        }
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                if (view != null && view instanceof TextView) {
                    ((TextView) view).setTextColor(Color.parseColor("#333333"));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                if (view != null && view instanceof TextView) {
                    ((TextView) view).setTextColor(Color.parseColor("#999999"));
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        TabLayout.Tab tab = mTabLayout.getTabAt(1);
        if (tab != null) {
            tab.select();
        }
    }

    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new BarCartoonFragment());
        fragments.add(new RecommendFragment());
        fragments.add(new ClassifyFragment());
        fragments.add(new RankTabFragment());
        return fragments;
    }

}
