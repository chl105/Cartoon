package org.sltpaya.cartoon.adapter.pager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.sltpaya.cartoon.ActivityItem;
import org.sltpaya.cartoon.fragment.rank.RankListFragment;

import static android.R.attr.fragment;
import static android.R.attr.label;

/**
 * Author: SLTPAYA
 * Date: 2017/3/8
 */
public class RankItemAdapter extends FragmentPagerAdapter {

    private String[] mTitles;
    private String mLabel;//这是传给Fragment的label
    private String classType;

    public RankItemAdapter(FragmentManager fm, String[] titles,
                           ActivityItem item, String classType) {
        super(fm);
        mTitles = titles;
        this.classType = classType;
        mLabel = item.getCallFeature();
    }

    @Override
    public Fragment getItem(int position) {
        int[] types = {0, 1 , 6};//(Cartoon typeId为其中一个0,1,6，这是业务规定)
        Bundle bundle = new Bundle();
        int index = position;
        if ("cartoon".equals(classType)) {
           index = types[position];
        }
        bundle.putString("info", mLabel + "," + index + "," + classType);
        RankListFragment fragment = new RankListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

}
