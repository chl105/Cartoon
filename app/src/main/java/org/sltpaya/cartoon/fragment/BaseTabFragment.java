package org.sltpaya.cartoon.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.activity.BookNewDetailsActivity;
import org.sltpaya.cartoon.activity.StripManDetailActivity;

import static android.R.attr.data;

/**
 * Author: SLTPAYA
 * Date: 2017/2/21
 */
public abstract class BaseTabFragment extends Fragment {

    protected View mRootView;
    protected RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.recycler_layout, container, false);
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recycler);
        setRecyclerView();
        initViews();
        return mRootView;
    }

    protected abstract void setRecyclerView();

    protected void initViews() {

    }

    protected void entryStripManDetail(String bookid) {
        Intent intent = new Intent(getContext(), StripManDetailActivity.class);
        intent.putExtra("bookid", bookid);
        startActivity(intent);
    }

    protected void entryBookNewDetails(String bookid) {
        Intent intent = new Intent(getContext(), BookNewDetailsActivity.class);
        intent.putExtra("bookid", bookid);
        startActivity(intent);
    }

}
