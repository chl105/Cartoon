package org.sltpaya.cartoon.holder.cartoon;

import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import org.sltpaya.cartoon.BookState;
import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.adapter.DataUtils;
import org.sltpaya.cartoon.holder.BaseHolder;
import org.sltpaya.cartoon.listener.AdapterItemListener;
import org.sltpaya.cartoon.net.cache.RecommendCache;
import org.sltpaya.cartoon.net.entry.Entry;
import org.sltpaya.cartoon.net.entry.TypeTwoEntry;
import org.sltpaya.tool.Toast;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/2/21
 */
public class VerticalHolder extends BaseHolder implements View.OnClickListener{

    private ArrayList<View> mItems;
    /**根布局标签，通过控制其显示和隐藏*/
    private ArrayList<View> mRootViews;
    private TextView mGroupTitle;
    private ImageView mGroupIcon;
    private TypeTwoEntry mEntry;
    private AdapterItemListener<BookState> listener;
    private TextView moreView;
    private int layoutType;

    public VerticalHolder(View itemView) {
        super(itemView);
        initViews();
    }

    private void initViews() {
        mItems = new ArrayList<>();
        mRootViews = new ArrayList<>(2);
        initGroupTitle();
        int[] parentsId = {
                R.id.vertical_1,
                R.id.vertical_2
        };
        for (int id : parentsId) {
            View view = itemView.findViewById(id);
            view.setVisibility(View.GONE);
            mRootViews.add(view);
            inflateGroup(view);
        }
    }

    @Override
    public void setListener(AdapterItemListener<BookState> listener) {
        this.listener = listener;
    }

    /**
     * 初始化最上方的标题栏
     */
    private void initGroupTitle() {
        mGroupTitle = (TextView) itemView.findViewById(R.id.title_one_title);
        mGroupIcon = (ImageView) itemView.findViewById(R.id.title_one_img);
    }

    private void inflateGroup(View view) {
        int[] parentsId = {
                R.id.item_vertical_1,
                R.id.item_vertical_2,
                R.id.item_vertical_3
        };
        for (int id : parentsId) {
            View child = view.findViewById(id);
            mItems.add(child);
        }
    }


    @Override
    public void updateView() {
        setGroupTop();
        RecommendCache cache = RecommendCache.newInstance();
        SparseArray<Entry> cacheData = cache.getData();
        if (cacheData != null) {
            /*让根View显示出来*/
            setVisibility(mRootViews, View.VISIBLE);
            getEntry(cacheData);
            setData();
        }
    }

    private void getEntry(SparseArray<Entry> cacheData) {
        int[] types = {20, 18, 21, 28, 23, 27};
        int index = (int) ((getAdapterPosition() / 2.0 - 2) + 0.5F);
        mEntry = (TypeTwoEntry) cacheData.get(types[index]);
    }

    private void setData() {
        layoutType = mEntry.getData().getType();
        List<TypeTwoEntry.Datum> data = mEntry.getData().getData();
        for (int i = 0; i < mItems.size(); i++) {
            handleView(mItems.get(i), data.get(i));
        }
    }

    private void handleView(View parent, TypeTwoEntry.Datum data) {
        ImageView img = (ImageView) parent.findViewById(R.id.item_vertical_img);
        ImageView flagImg = (ImageView) parent.findViewById(R.id.item_vertical_flag);
        TextView title = (TextView) parent.findViewById(R.id.item_vertical_title);
        TextView author = (TextView) parent.findViewById(R.id.item_vertical_author);

        String imgUrl = data.getThumb();
        String bookid = data.getBookid();
        String viewType = data.getViewType();
        String gxType = data.getGxType();
        int flagType = DataUtils.parseInt(gxType, 0);

        setUpdateType(flagImg, flagType);
        parent.setOnClickListener(this);//为主布局设置点击事件
        //传入信息；分别是activity打开的类型，书籍的id
        BookState state = new BookState(bookid);
        state.setShowViewType(viewType);

        parent.setTag(-1, state);

        //更多Activity布局，设置moreView的监听事件
        //0为标题名，1位类型（more),2为type的id
        moreView.setOnClickListener(this);
        BookState moreState = new BookState(bookid);
        moreState.setShowViewType("more");
        moreState.setTypeId(String.valueOf(layoutType));
        moreState.setGroupTitle(mGroupTitle.getText()+"");
        moreView.setTag(-1, moreState);

        title.setText(data.getTitle());
        author.setText(data.getAuthor());
        Picasso.with(itemView.getContext()).load(imgUrl)
                .placeholder(R.drawable.icon_cover_home02)
                .error(R.drawable.icon_cover_home02)
                .into(img);
    }

    private void setGroupTop() {
        String[] titles = {
                "新作推荐(╭￣3￣)╭",
                "刚刚更新O(∩_∩)O~~",
                "爽快向٩(๑´0`๑)۶",
                "女生向（≧▼≦❀）",
                "日漫(๑•ㅂ•)و✧duang！",
                "韩漫(o゜▽゜)o☆[BINGO!]"
        };
        int[] redId = {
                R.drawable.icon_pop_new,
                R.drawable.icon_new_update,
                R.drawable.icon_zhuanqu,
                R.drawable.icon_cooperation,
                R.drawable.icon_women,
                R.drawable.icon_zhuanti5
        };
        int index = (int) ((getAdapterPosition() / 2.0 - 2) + 0.5F);

        mGroupTitle.setText(titles[index]);
        mGroupIcon.setImageResource(redId[index]);

        moreView = (TextView) itemView.findViewById(R.id.title_one_more);
        moreView.setText("更多");
    }

    @Override
    public void onClick(View v) {
        BookState info = (BookState) v.getTag(-1);
        if (listener != null) {
            listener.click(v, info);
        }
    }
}
