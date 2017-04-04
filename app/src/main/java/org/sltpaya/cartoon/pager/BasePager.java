package org.sltpaya.cartoon.pager;

import android.view.ViewGroup;
import org.sltpaya.cartoon.net.NetState;

/**
 * Author: SLTPAYA
 * Date: 2017/4/3
 */
public class BasePager implements NetState {

    protected ViewGroup mViewGroup;

    public BasePager(ViewGroup viewGroup) {
        mViewGroup = viewGroup;
    }

    /**
     * 成功页面
     */
    @Override
    public void onSuccessPager() {

    }

    /**
     * 失败页面，相同
     */
    @Override
    public void onFailedPager() {

    }

    /**
     * 加载中页面，相同
     */
    @Override
    public void onLoadingPager() {

    }

}
