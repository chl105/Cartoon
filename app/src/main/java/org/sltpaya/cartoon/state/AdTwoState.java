package org.sltpaya.cartoon.state;

import android.view.View;
import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.holder.novel.AdTwoHolder;

/**
 * Author: SLTPAYA
 * Date: 2017/2/26
 */
public class AdTwoState extends BaseState {

    @Override
    public void work(HolderManger manger) {
        super.work(manger);
        if (mType == AD_TWO_TYPE) {
            View infalte = infalte(R.layout.ad_two);
            AdTwoHolder holder = new AdTwoHolder(infalte);
            manger.setHolder(holder);
            return;
        }
        new VerticalState().work(manger);
    }

}
