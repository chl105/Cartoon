package org.sltpaya.cartoon.fragment.main;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import org.sltpaya.cartoon.adapter.pager.CartoonPagerAdapter;
import org.sltpaya.cartoon.fragment.novel.ClassifyFragment;
import org.sltpaya.cartoon.fragment.novel.RankTabFragment;
import org.sltpaya.cartoon.fragment.novel.RecommendFragment;
import org.sltpaya.cartoon.fragment.novel.UpdateDayFragment;
import org.sltpaya.cartoon.net.cache.NovelCache;
import org.sltpaya.tablayout.XTabLayout;

import java.util.ArrayList;

/**
 * Author: SLTPAYA
 * Date: 2017/2/20
 */
public class NovelFragment extends NovelCartFragment {

    @Override
    protected void setPagerAdapter() {
        String[] titles = {"日更", "推荐", "分类", "排行"};
        mPager.setAdapter(new CartoonPagerAdapter(getChildFragmentManager(), titles, getFragments()));
    }

    /**
     * 设置默认显示的Tab
     */
    @Override
    protected void setDefault() {
        XTabLayout.Tab tab = mTabLayout.getTabAt(1);
        if (tab != null) tab.select();
    }

    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new UpdateDayFragment());
        fragments.add(new RecommendFragment());
        fragments.add(new ClassifyFragment());
        fragments.add(new RankTabFragment());
        return fragments;
    }

}
