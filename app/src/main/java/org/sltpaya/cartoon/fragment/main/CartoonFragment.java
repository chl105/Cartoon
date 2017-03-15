package org.sltpaya.cartoon.fragment.main;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

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
        String[] titles = {"条漫", "推荐", "分类", "排行"};
        mPager.setAdapter(new CartoonPagerAdapter(getChildFragmentManager(), titles, getFragments()));
    }

    /**
     * 设置默认显示的Tab
     */
    @Override
    protected void setDefault() {
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
