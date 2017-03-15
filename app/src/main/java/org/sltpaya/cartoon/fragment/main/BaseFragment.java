package org.sltpaya.cartoon.fragment.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.sltpaya.cartoon.R;

/**
 * Author: SLTPAYA
 * Date: 2017/2/20
 */
public abstract class BaseFragment extends Fragment {

    private LayoutInflater mInflater;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mInflater = inflater;
        View inflate = inflater.inflate(R.layout.base_fragment, container, false);
        ViewGroup appBarContainer = (ViewGroup) inflate.findViewById(R.id.fragment_top_container);
        ViewGroup contentContainer = (ViewGroup) inflate.findViewById(R.id.fragment_content_container);
        inflateAppBar(appBarContainer);
        inflateContent(contentContainer);
        return inflate;
    }

    protected abstract void inflateAppBar(ViewGroup appbarContainer);

    protected abstract void inflateContent(ViewGroup contentContainer);

    protected LayoutInflater getLayoutInflater() {
        return mInflater;
    }

}
