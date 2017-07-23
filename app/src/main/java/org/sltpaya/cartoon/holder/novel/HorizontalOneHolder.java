package org.sltpaya.cartoon.holder.novel;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.adapter.DataUtils;
import org.sltpaya.cartoon.holder.BaseHolder;
import org.sltpaya.cartoon.holder.LineView;
import org.sltpaya.cartoon.net.cache.NovelCache;
import org.sltpaya.cartoon.net.entry.Entry;
import org.sltpaya.cartoon.net.entry.novel.HorizontalOneEntry;
import org.sltpaya.tool.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/2/27
 */
public class HorizontalOneHolder extends BaseHolder {

    private int LINE_SIZE = 3;
    ImageView groupIcon;
    TextView groupTitle;
    TextView groupMore;
    private HorizontalOneEntry mEntry;
    private List<LineView> mLines = new ArrayList<>(LINE_SIZE);//存储所有的行对象

    public HorizontalOneHolder(View itemView) {
        super(itemView);
        LINE_SIZE = getLineSize();
        initViews();
        initGroupTitle();
    }

    protected int getLineSize() {
        return LINE_SIZE;
    }

    protected void initViews() {
        mLines.clear();//清空集合
        for (int i = 0; i < LINE_SIZE; i++) {
            LineView line = new HorizontalLine(itemView.getContext());
            line.addItem(2);
            mLines.add(line);
            line.rootView.setVisibility(View.GONE);
            ((ViewGroup) itemView).addView(line.rootView);
        }
        //添加阴影
        mInflater.inflate(R.layout.bottom_shadow, (ViewGroup) itemView, true);
    }

    /**
     * 初始化最上方的标题栏
     */
    private void initGroupTitle() {
        groupTitle = (TextView) itemView.findViewById(R.id.title_one_title);
        groupIcon = (ImageView) itemView.findViewById(R.id.title_one_img);
        groupMore = (TextView) itemView.findViewById(R.id.title_one_more);
        groupMore.setText("更多");
    }

    @Override
    public void updateView() {
        NovelCache cache = NovelCache.getInstance();
        if (cache.getData() != null) {
            getEntry(cache.getData());
            List<HorizontalOneEntry.Datum> data = mEntry.getData().getData();
            setData(data);
            setGroup();
        }
    }

    /**
     * 设置标题栏等信息
     */
    protected void setGroup() {
        String[] titles = {
                "精挑细选Y(^o^)Y",
                "新作推荐(╭￣3￣)╭"
        };
        int[] redId = {
                R.drawable.icon_recommend_new,
                R.drawable.icon_pop_new
        };
        int[] indexs = {2, 5};
        int now = 0;
        for (int i = 0; i < indexs.length; i++) {
            if (indexs[i] == getAdapterPosition()) {
                now = i;
            }
        }
        groupTitle.setText(titles[now]);
        groupIcon.setImageResource(redId[now]);
        groupMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(itemView.getContext(), "将要加载更多数据啦啦啦....", Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    private void getEntry(SparseArray<Entry> cacheData) {
        int[] types = {1, 20, 21, 22, 23, 27};
        int[] indexs = {2, 5, 7, 9, 11, 12};
        int index = getAdapterPosition();//对应关系
        int type = 1;
        for (int i = 0; i < indexs.length; i++) {
            if (indexs[i] == index) {
                type = types[i];
            }
        }
        mEntry = (HorizontalOneEntry) cacheData.get(type);
    }

    private void setData(List<HorizontalOneEntry.Datum> data) {
        int position = 0;
        for (LineView line : mLines) {
            line.rootView.setVisibility(View.VISIBLE);
            for (LineView.Item item : line.items) {
                if (isShowDes()) {
                    item.author.setVisibility(View.VISIBLE);
                } else {
                    item.author.setVisibility(View.GONE);
                }
                setDataToView(item, data.get(position));
                position++;
            }
        }
    }

    private void setDataToView(LineView.Item item, HorizontalOneEntry.Datum datum) {
        String description = datum.getDescription();
        String imgUrl = datum.getThumb();
        String name = datum.getTitle();
        String gxType = datum.getGxType();
        int flagType = DataUtils.parseInt(gxType, 0);

        setUpdateType(item.flagImage, flagType);
        item.title.setText(name);
        item.author.setText(description);

        Picasso.with(itemView.getContext()).load(imgUrl)
                .placeholder(R.drawable.icon_cover_home01)
                .error(R.drawable.icon_cover_home01)
                .config(Bitmap.Config.RGB_565)
                .into(item.thumbImage);
    }

    protected boolean isShowDes() {
        return true;
    }

    private class HorizontalLine extends LineView {

        private HorizontalLine(Context context) {
            super(context);
        }

        @Override
        public LineView.Item newItem() {
            View parent = mInflater.inflate(R.layout.novel_item_horizontal, rootView, false);
            ImageView thumbImage = (ImageView) parent.findViewById(R.id.novel_item_img);
            ImageView flagImage = (ImageView) parent.findViewById(R.id.novel_item_flag);
            TextView title = (TextView) parent.findViewById(R.id.novel_item_title);
            TextView des = (TextView) parent.findViewById(R.id.novel_item_des);
            return new Item(parent, thumbImage, flagImage, title, des);
        }
    }

}
