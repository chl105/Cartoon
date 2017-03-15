package org.sltpaya.cartoon.adapter.pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import org.sltpaya.cartoon.fragment.TestFragment;

/**
 * Author: SLTPAYA
 * Date: 2017/2/20
 */
public class TestPagerAdapter extends FragmentStatePagerAdapter {

    private String[] titles;


    public TestPagerAdapter(FragmentManager fm, String[] titles) {
        super(fm);
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return new TestFragment();
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

}
