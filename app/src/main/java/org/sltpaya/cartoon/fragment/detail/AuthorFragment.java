package org.sltpaya.cartoon.fragment.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.adapter.detail.DetailAdapter;

/**
 * Author: SLTPAYA
 * Date: 2017/3/7
 */
public class AuthorFragment extends Fragment {

    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mRecyclerView = (RecyclerView) inflater.inflate(R.layout.recycler_layout, container, false);
        setRecyclerView();
        return mRecyclerView;
    }

    private void setRecyclerView() {
        mRecyclerView.setAdapter(new DetailAdapter(null));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

}
