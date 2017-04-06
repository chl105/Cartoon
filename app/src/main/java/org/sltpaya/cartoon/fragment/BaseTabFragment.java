package org.sltpaya.cartoon.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.activity.BookNewDetailsActivity;
import org.sltpaya.cartoon.activity.StripManDetailActivity;

/**
 * Author: SLTPAYA
 * Date: 2017/2/21
 */
public abstract class BaseTabFragment extends Fragment {

    protected View mRootView;
    protected RecyclerView mRecyclerView;
    /**Fragment依附的Activity*/
    protected Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        System.out.println("fragment执行了onAttach");
        mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        System.out.println("fragment执行了onDetach");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.out.println("Fragment View被销毁了！！！");
    }

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

    protected void initViews() {}

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
