package org.sltpaya.cartoon.state;

import android.view.View;

import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.holder.novel.HorizontalOneHolder;

/**
 * Author: SLTPAYA
 * Date: 2017/2/26
 */
public class HorizontalOneState extends BaseState {

    @Override
    public void work(HolderManger manger) {
        super.work(manger);
        if (mType == HORIZONTAL_SIX_TYPE) {
            View infalte = infalte(R.layout.novel_group_one);
            HorizontalOneHolder holder = new HorizontalOneHolder(infalte);
            manger.setHolder(holder);
            return;
        }
        new HorizontalTwoState().work(manger);
    }

}
