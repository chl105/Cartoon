package org.sltpaya.cartoon.state;

import android.view.View;

import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.holder.novel.VerticalHolder;

/**
 * Author: SLTPAYA
 * Date: 2017/2/27
 */
public class VerticalState extends BaseState {

    @Override
    public void work(HolderManger manger) {
        super.work(manger);
        if (mType == VERTICAL_TYPE) {
            View infalte = infalte(R.layout.novel_group_three);
            VerticalHolder holder = new VerticalHolder(infalte);
            manger.setHolder(holder);
            return;
        }
    }

}
