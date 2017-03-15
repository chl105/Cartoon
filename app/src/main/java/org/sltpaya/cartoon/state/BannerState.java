package org.sltpaya.cartoon.state;

import android.view.View;

import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.holder.novel.BannerHolder;

/**
 * Author: SLTPAYA
 * Date: 2017/2/26
 */
public class BannerState extends BaseState {

    @Override
    public void work(HolderManger manger) {
        super.work(manger);
        if (mType == BANNER_TYPE) {
            View infalte = infalte(R.layout.banner_layout);
            BannerHolder holder = new BannerHolder(infalte);
            manger.setHolder(holder);
            return;
        }
        new PreviewState().work(manger);
    }

}
