package org.sltpaya.cartoon.state;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Author: SLTPAYA
 * Date: 2017/2/26
 */
public class HolderManger {

    private LayoutInflater inflater;
    private ViewGroup parent;
    private RecyclerView.ViewHolder holder;
    private int type;

    public HolderManger(LayoutInflater inflater, ViewGroup parent, int type) {
        this.inflater = inflater;
        this.parent = parent;
        this.type = type;
    }

    public LayoutInflater getInflater() {
        return inflater;
    }

    public void setInflater(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public RecyclerView.ViewHolder getHolder() {
        return holder;
    }

    public void setHolder(RecyclerView.ViewHolder holder) {
        this.holder = holder;
    }

    public ViewGroup getParent() {
        return parent;
    }

    public void setParent(ViewGroup parent) {
        this.parent = parent;
    }

    public void work() {
        new BannerState().work(this);
    }

}
