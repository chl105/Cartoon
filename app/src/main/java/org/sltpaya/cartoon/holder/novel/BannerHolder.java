package org.sltpaya.cartoon.holder.novel;

import android.view.View;
import org.sltpaya.cartoon.net.cache.NovelCache;
import org.sltpaya.cartoon.net.entry.BannerEntry;

/**
 * Author: SLTPAYA
 * Date: 2017/2/27
 */
public class BannerHolder extends org.sltpaya.cartoon.holder.BannerHolder {

    public BannerHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void updateView() {
        NovelCache cache = NovelCache.getInstance();
        if (cache.getData() != null) {
            BannerEntry entry = (BannerEntry) cache.getData().get(0);
            setBannerData(entry);
            createDotView();
        }
    }

}
