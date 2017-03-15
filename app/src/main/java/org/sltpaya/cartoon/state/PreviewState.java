package org.sltpaya.cartoon.state;

import android.view.View;

import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.holder.novel.PreviewHolder;

/**
 * Author: SLTPAYA
 * Date: 2017/2/26
 */
public class PreviewState extends BaseState {

    @Override
    public void work(HolderManger manger) {
        super.work(manger);
        if (mType == PREVIEW_TYPE) {
            View inflate= infalte(R.layout.preview_layout);
            PreviewHolder holder = new PreviewHolder(inflate);
            manger.setHolder(holder);
            return;
        }
        new HorizontalOneState().work(manger);
    }

}
