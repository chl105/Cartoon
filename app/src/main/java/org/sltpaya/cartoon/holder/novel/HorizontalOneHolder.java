package org.sltpaya.cartoon.holder.novel;

import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.adapter.DataUtils;
import org.sltpaya.cartoon.holder.BaseHolder;
import org.sltpaya.cartoon.net.cache.NovelCache;
import org.sltpaya.cartoon.net.entry.Entry;
import org.sltpaya.cartoon.net.entry.novel.HorizontalOneEntry;
import org.sltpaya.tool.Toast;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * Author: SLTPAYA
 * Date: 2017/2/27
 */
public class HorizontalOneHolder extends BaseHolder {

    ImageView groupIcon;
    TextView groupTitle;
    TextView groupMore;

    private ArrayList<View> mItems;
    /**根布局标签，通过控制其显示和隐藏*/
    ArrayList<View> mRootViews;
    private HorizontalOneEntry mEntry;

    public HorizontalOneHolder(View itemView) {
        super(itemView);
        mItems = new ArrayList<>();
        initViews();
        initGroupTitle();
    }

    protected void initViews() {
        mRootViews = new ArrayList<>(3);
        int[] parentIds = {
                R.id.novel_group_1,
                R.id.novel_group_2,
                R.id.novel_group_3
        };
        for (int id : parentIds) {
            View view = itemView.findViewById(id);
            view.setVisibility(View.GONE);
            mRootViews.add(view);
            findGroupItem(view);
        }
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

    protected void findGroupItem(View view) {
        int[] ids = {
                R.id.novel_1,
                R.id.novel_2
        };
        for (int id : ids) {
            View inflate = view.findViewById(id);
            mItems.add(inflate);
        }
    }

    @Override
    public void updateView() {
        NovelCache cache = NovelCache.newInstance();
        if (cache.getData() != null) {
            getEntry(cache.getData());
            List<HorizontalOneEntry.Datum> data = mEntry.getData().getData();
            /*让根View显示出来*/
            setVisibility(mRootViews, View.VISIBLE);
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
        for (int i = 0; i < mItems.size(); i++) {
            handleView(mItems.get(i), data.get(i));
        }
    }

    private void handleView(View view, HorizontalOneEntry.Datum datum) {
        ImageView img = (ImageView) view.findViewById(R.id.novel_item_img);
        ImageView flagImg = (ImageView) view.findViewById(R.id.novel_item_flag);
        TextView title = (TextView) view.findViewById(R.id.novel_item_title);
        TextView des = (TextView) view.findViewById(R.id.novel_item_des);

        String description = datum.getDescription();
        String imgUrl = datum.getThumb();
        String name = datum.getTitle();
        String gxType = datum.getGxType();
        int flagType = DataUtils.parseInt(gxType, 0);

        setUpdateType(flagImg, flagType);
        title.setText(name);
        des.setText(description);

        Picasso.with(itemView.getContext()).load(imgUrl)
                .placeholder(R.drawable.icon_cover_home01)
                .error(R.drawable.icon_cover_home01)
                .into(img);
    }

}
