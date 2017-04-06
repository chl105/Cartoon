package org.sltpaya.cartoon.fragment.main;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;

import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.adapter.pager.CollectPagerAdapter;
import org.sltpaya.cartoon.adapter.pager.TestPagerAdapter;
import org.sltpaya.cartoon.fragment.collect.*;
import org.sltpaya.cartoon.fragment.collect.CartoonFragment;
import org.sltpaya.cartoon.fragment.collect.NovelFragment;
import org.sltpaya.tablayout.XTabLayout;

import java.util.ArrayList;

/**
 * Author: SLTPAYA
 * Date: 2017/2/20
 */
public class CollectFragment extends NovelCartFragment {

    @Override
    protected void inflateAppBar(ViewGroup appbarContainer) {
        View inflate = getLayoutInflater().inflate(R.layout.bar_collect, appbarContainer, true);
        mTabLayout = (XTabLayout) inflate.findViewById(R.id.top_navigation);
    }

    @Override
    protected void setPagerAdapter() {
        String[] titles = {"漫画", "小说"};
        mPager.setAdapter(new CollectPagerAdapter(getChildFragmentManager(), titles, getFragments()));
    }

    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new CartoonFragment());
        fragments.add(new NovelFragment());
        return fragments;
    }

}
