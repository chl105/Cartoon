package org.sltpaya.cartoon.state;

import android.view.View;

import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.holder.novel.HorizontalTwoHolder;

/**
 * Author: SLTPAYA
 * Date: 2017/2/26
 */
public class HorizontalTwoState extends BaseState {

    @Override
    public void work(HolderManger manger) {
        super.work(manger);
        if (mType == HORIZONTAL_FOUR_TYPE) {
            View infalte = infalte(R.layout.novel_group_two);
            HorizontalTwoHolder holder = new HorizontalTwoHolder(infalte);
            manger.setHolder(holder);
            return;
        }
        new AdState().work(manger);
    }

}
