package org.sltpaya.cartoon.holder.detail;

import android.content.Intent;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.activity.BookNewDetailsActivity;
import org.sltpaya.cartoon.activity.StripManDetailActivity;
import org.sltpaya.cartoon.listener.AdapterItemListener;
import org.sltpaya.cartoon.net.entry.TypeTwoEntry;
import org.sltpaya.cartoon.net.entry.detail.DetailSameEntry;
import org.sltpaya.tool.Toast;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/3/6
 */
public class SameBookHolder implements View.OnClickListener {

    private ArrayList<View> mItems;
    private DetailSameEntry mEntry;
    private AdapterItemListener<SparseArray> listener;
    private View itemView;

    public SameBookHolder(View itemView) {
        this.itemView = itemView;
        initViews();
    }

    private void initViews() {
        mItems = new ArrayList<>();
        int[] parents = {
                R.id.vertical_1,
                R.id.vertical_2
        };
        for (int id : parents) {
            View view = itemView.findViewById(id);
            inflateGroup(view);
        }
    }

    public void setListener(AdapterItemListener<SparseArray> listener) {
        this.listener = listener;
    }

    private void inflateGroup(View view) {
        int[] parents = {
                R.id.item_vertical_1,
                R.id.item_vertical_2,
                R.id.item_vertical_3
        };
        for (int id : parents) {
            View child = view.findViewById(id);
            mItems.add(child);
        }
    }

    public void updateView(DetailSameEntry entry) {
        mEntry = entry;
        setData();
    }

    private void setData() {
        List<DetailSameEntry.Datum> data = mEntry.getDataList();
        for (int i = 0; i < mItems.size(); i++) {
            handleView(mItems.get(i), data.get(i));
        }
    }

    private void handleView(View parent, DetailSameEntry.Datum data) {
        ImageView img = (ImageView) parent.findViewById(R.id.item_vertical_img);
        TextView title = (TextView) parent.findViewById(R.id.item_vertical_title);
        TextView author = (TextView) parent.findViewById(R.id.item_vertical_author);
        String imgUrl = data.getThumb();
        String bookid = data.getBookid();
        String viewType = data.getViewType();

        parent.setOnClickListener(this);//为主布局设置点击事件
        //传入信息；分别是activity打开的类型，书籍的id
        SparseArray<String> info = new SparseArray<>(2);
        info.put(0, viewType);
        info.put(1, bookid);
        parent.setTag(-1, info);

        title.setText(data.getTitle());
        author.setText(data.getAuthor());
        Picasso.with(itemView.getContext()).load(imgUrl)
                .placeholder(R.drawable.icon_cover_home02)
                .error(R.drawable.icon_cover_home02)
                .into(img);
    }

    @Override
    public void onClick(View v) {
        SparseArray info = (SparseArray) v.getTag(-1);
        if (listener != null) {
            listener.click(v, info);
        }
    }

}

