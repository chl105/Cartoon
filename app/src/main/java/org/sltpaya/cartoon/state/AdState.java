package org.sltpaya.cartoon.state;

import android.view.View;

import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.holder.novel.AdOneHolder;

/**
 * Author: SLTPAYA
 * Date: 2017/2/26
 */
public class AdState extends BaseState {


    @Override
    public void work(HolderManger manger) {
        super.work(manger);
        if (mType == AD_TYPE) {
            View infalte = infalte(R.layout.ad_one);
            AdOneHolder holder = new AdOneHolder(infalte);
            manger.setHolder(holder);
            return;
        }
        new AdTwoState().work(manger);
    }

}
