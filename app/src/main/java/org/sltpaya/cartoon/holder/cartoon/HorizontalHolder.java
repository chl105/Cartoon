package org.sltpaya.cartoon.holder.cartoon;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.sltpaya.cartoon.BookState;
import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.adapter.DataUtils;
import org.sltpaya.cartoon.holder.BaseHolder;
import org.sltpaya.cartoon.holder.LineView;
import org.sltpaya.cartoon.listener.AdapterItemListener;
import org.sltpaya.cartoon.net.cache.RecommendCache;
import org.sltpaya.cartoon.net.entry.Entry;
import org.sltpaya.cartoon.net.entry.TypeOneEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/2/21
 */
public class HorizontalHolder extends BaseHolder implements View.OnClickListener {


    private TypeOneEntry mEntry;
    private AdapterItemListener<BookState> listener;
    private TextView moreView;
    private TextView groupTitle;

    private int layoutType;

    private final static int LINE_SIZE = 3;
    private List<LineView> mLines = new ArrayList<>(LINE_SIZE);//存储所有的行对象

    public HorizontalHolder(View itemView) {
        super(itemView);
        initViews();
    }

    private void initViews() {
        initGroupTitle();
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
     * 从外传入点击事件的监听器
     *
     * @param listener {@link AdapterItemListener}
     */
    @Override
    public void setListener(AdapterItemListener<BookState> listener) {
        this.listener = listener;
    }

    /**
     * 初始化最上方的标题栏
     */
    private void initGroupTitle() {
        groupTitle = (TextView) itemView.findViewById(R.id.title_one_title);
        ImageView groupIcon = (ImageView) itemView.findViewById(R.id.title_one_img);
        moreView = (TextView) itemView.findViewById(R.id.title_one_more);
        moreView.setText("更多");

        groupTitle.setText("精挑细选Y(^o^)Y");
        groupIcon.setImageResource(R.drawable.icon_recommend_new);
    }

    @Override
    public void updateView() {
        RecommendCache cache = RecommendCache.getInstance();
        SparseArray<Entry> cacheData = cache.getData();
        if (cacheData != null) {
            /*让根View显示出来*/
            mEntry = (TypeOneEntry) cacheData.get(1);
            setData();
        }
    }

    private void setData() {
        layoutType = mEntry.getData().getType();
        List<TypeOneEntry.Datum> data = mEntry.getData().getData();
        int position = 0;
        for (LineView line : mLines) {
            line.rootView.setVisibility(View.VISIBLE);
            for (LineView.Item item : line.items) {
                System.out.println("循环遍历zi");
                setDataToView(item, data.get(position));
                position++;
            }
        }
    }

    private void setDataToView(LineView.Item item, TypeOneEntry.Datum data) {
        String imgUrl = data.getThumb1();
        String viewType = data.getViewType();
        String bookid = data.getBookid();
        String gxType = data.getGxType();
        int flagType = DataUtils.parseInt(gxType, 0);

        setUpdateType(item.flagImage, flagType);
        item.parent.setOnClickListener(this);//为主布局设置点击事件
        //传入信息；分别是activity打开的类型，书籍的id
        BookState state = new BookState(bookid);
        state.setShowViewType(viewType);

        item.parent.setTag(-1, state);

        //更多Activit布局，设置moreView的监听事件
        //0为标题名，1位类型（more),2为type的id
        moreView.setOnClickListener(this);
        BookState moreState = new BookState(bookid);
        moreState.setShowViewType("more");
        moreState.setTypeId(String.valueOf(layoutType));
        moreState.setGroupTitle(groupTitle.getText() + "");
        moreView.setTag(-1, moreState);

        item.title.setText(data.getTitle());
        item.author.setText(data.getAuthor());
        Picasso.with(itemView.getContext()).load(imgUrl)
                .error(R.drawable.icon_cover_home01)
                .placeholder(R.drawable.icon_cover_home01)
                .config(Bitmap.Config.RGB_565)
                .into(item.thumbImage);
    }

    @Override
    public void onClick(View v) {
        BookState info = (BookState) v.getTag(-1);
        if (listener != null) {
            listener.click(v, info);
        }
    }

    private class HorizontalLine extends LineView {

        private HorizontalLine(Context context) {
            super(context);
        }

        @Override
        public Item newItem() {
            View parent = mInflater.inflate(R.layout.item_horizontal, rootView, false);
            ImageView thumbImage = (ImageView) parent.findViewById(R.id.item_horizontal_img);
            ImageView flagImage = (ImageView) parent.findViewById(R.id.item_horizontal_flag);
            TextView title = (TextView) parent.findViewById(R.id.item_horizontal_title);
            TextView author = (TextView) parent.findViewById(R.id.item_horizontal_author);
            return new Item(parent, thumbImage, flagImage, title, author);
        }

    }

}
