package org.sltpaya.cartoon.adapter.classify;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.sltpaya.cartoon.BookState;
import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.holder.classify.ClassifyItemHolder;
import org.sltpaya.cartoon.listener.AdapterItemListener;
import org.sltpaya.cartoon.net.entry.classify.ClassifyItemEntry;
import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/3/9
 */
public class ClassifyPagerItemAdapter extends RecyclerView.Adapter<ClassifyItemHolder> {

    private List<ClassifyItemEntry.Datum> list;
    private String classType;//数据是来自于哪个模块（Cartoon/Novel)
    private AdapterItemListener<BookState> listener;

    public void addListener(AdapterItemListener<BookState> listener) {
        this.listener = listener;
    }

    public void notifyDataChanged(ClassifyItemEntry entry, String classType) {
        list = entry.getDataList();
        this.classType = classType;
        notifyDataSetChanged();
    }

    @Override
    public ClassifyItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        int layoutId = R.layout.classify_novel_list_item;
        if ("cartoon".equals(classType)) {
            layoutId = R.layout.classify_cartoon_list_item;
        }
        View inflate = inflater.inflate(layoutId, parent, false);
        ClassifyItemHolder holder = new ClassifyItemHolder(inflate);
        holder.setListener(listener);
        return holder;
    }

    @Override
    public void onBindViewHolder(ClassifyItemHolder holder, int position) {
        holder.updateView(list.get(position), classType);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

}
