package org.sltpaya.cartoon.fragment.main;


import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.sltpaya.cartoon.R;

/**
 * Author: SLTPAYA
 * Date: 2017/2/20
 */
public abstract class NovelCartFragment extends BaseFragment {

    protected TabLayout mTabLayout;
    protected ViewPager mPager;

    @Override
    protected void inflateAppBar(ViewGroup appbarContainer) {
        View inflate = getLayoutInflater().inflate(R.layout.bar_tab, appbarContainer, true);
        mTabLayout = (TabLayout) inflate.findViewById(R.id.top_navigation);
    }

    @Override
    protected void inflateContent(ViewGroup contentContainer) {
        View inflate = getLayoutInflater().inflate(R.layout.viewpager_layout, contentContainer, true);
        mPager = (ViewPager) inflate.findViewById(R.id.fragment_pager);
        setPagerAdapter();
        mTabLayout.setupWithViewPager(mPager);
        setDefault();
    }

    protected abstract void setPagerAdapter();

    protected void setDefault() {
    }

}
