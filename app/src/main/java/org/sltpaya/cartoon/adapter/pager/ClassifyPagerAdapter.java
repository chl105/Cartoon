package org.sltpaya.cartoon.adapter.pager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.sltpaya.cartoon.ActivityItem;
import org.sltpaya.cartoon.fragment.classify.ClassifyPagerFragment;

/**
 * Author: SLTPAYA
 * Date: 2017/3/9
 */
public class ClassifyPagerAdapter extends FragmentPagerAdapter {

    private String[] mTitles;
    private String mTag;//这是传给Fragment的属于该分类条目的某个tag
    private String classType;//数据来自哪个模块（cartoon/novel)

    public ClassifyPagerAdapter(FragmentManager fm, String[] titles, ActivityItem item) {
        super(fm);
        mTitles = titles;
        mTag = item.getCallFeature();
        classType = item.getType();
    }

    @Override
    public Fragment getItem(int position) {
        ClassifyPagerFragment fragment = new ClassifyPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putString("info", mTag + ","+position+"," + classType);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

}
