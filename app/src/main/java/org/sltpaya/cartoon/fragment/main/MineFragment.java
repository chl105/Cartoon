package org.sltpaya.cartoon.fragment.main;

import android.view.View;
import android.view.ViewGroup;

import org.sltpaya.cartoon.R;
import org.sltpaya.tool.Utils;

/**
 * Author: SLTPAYA
 * Date: 2017/2/28
 */
public class MineFragment extends BaseFragment {

    @Override
    protected void inflateAppBar(ViewGroup appbarContainer) {
        View inflate = getLayoutInflater().inflate(R.layout.mine_bar, appbarContainer, true);
        ViewGroup.LayoutParams params = inflate.getLayoutParams();
        params.height = Utils.dpToPx(215);
        inflate.setLayoutParams(params);
    }

    @Override
    protected void inflateContent(ViewGroup contentContainer) {
        getLayoutInflater().inflate(R.layout.mine_content,  contentContainer, true);
    }

}
