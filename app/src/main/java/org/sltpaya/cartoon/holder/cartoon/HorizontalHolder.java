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
import org.sltpaya.cartoon.net.entry.TypeOneEntry;
import org.sltpaya.tool.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static android.os.Build.VERSION_CODES.M;

/**
 * Author: SLTPAYA
 * Date: 2017/2/21
 */
public class HorizontalHolder extends BaseHolder implements View.OnClickListener{

    private ArrayList<View> mItems;
    private TypeOneEntry mEntry;
    private AdapterItemListener<BookState> listener;
    private View moreView;
    private int layoutType;
    private TextView groupTitle;

    public HorizontalHolder(View itemView) {
        super(itemView);
        initViews();
    }

    private void initViews() {
        mItems = new ArrayList<>();
        initGroupTitle();
        int[] parents = {
                R.id.horizontal_1,
                R.id.horizontal_2,
                R.id.horizontal_3
        };
        for (int id : parents) {
            View view = itemView.findViewById(id);
            inflateGroup(view);
        }
    }

    /**
     * 从外传入点击事件的监听器
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
        moreView = itemView.findViewById(R.id.title_one_more);

        groupTitle.setText("精挑细选Y(^o^)Y");
        groupIcon.setImageResource(R.drawable.icon_recommend_new);
    }

    private void inflateGroup(View view) {
        int[] parents = {
                R.id.item_horizontal_1,
                R.id.item_horizontal_2
        };
        for (int id : parents) {
            View child = view.findViewById(id);
            mItems.add(child);
        }
    }

    @Override
    public void updateView() {
        RecommendCache cache = RecommendCache.newInstance();
        SparseArray<Entry> cacheData = cache.getData();
        if (cacheData != null) {
            mEntry = (TypeOneEntry) cacheData.get(1);
            setData();
        }
    }

    private void setData() {
        layoutType = mEntry.getData().getType();
        List<TypeOneEntry.Datum> data = mEntry.getData().getData();
        for (int i = 0; i < mItems.size(); i++) {
            handleView(mItems.get(i), data.get(i));
        }
    }

    private void handleView(View parent, TypeOneEntry.Datum data) {
        System.out.println("正在设置数据......");
        ImageView img = (ImageView) parent.findViewById(R.id.item_horizontal_img);
        ImageView flagImg = (ImageView) parent.findViewById(R.id.item_horizontal_flag);
        TextView title = (TextView) parent.findViewById(R.id.item_horizontal_title);
        TextView author = (TextView) parent.findViewById(R.id.item_horizontal_author);
        String imgUrl = data.getThumb1();
        String viewType = data.getViewType();
        String bookid = data.getBookid();
        String gxType = data.getGxType();
        int flagType = DataUtils.parseInt(gxType, 0);

        setUpdateType(flagImg, flagType);
        parent.setOnClickListener(this);//为主布局设置点击事件
        //传入信息；分别是activity打开的类型，书籍的id
        BookState state = new BookState(bookid);
        state.setShowViewType(viewType);

        parent.setTag(-1, state);

        //更多Activit布局，设置moreView的监听事件
        //0为标题名，1位类型（more),2为type的id
        moreView.setOnClickListener(this);
        BookState moreState = new BookState(bookid);
        moreState.setShowViewType("more");
        moreState.setTypeId(String.valueOf(layoutType));
        moreState.setGroupTitle(groupTitle.getText()+"");
        moreView.setTag(-1, moreState);

        title.setText(data.getTitle());
        author.setText(data.getAuthor());
        Picasso.with(itemView.getContext()).load(imgUrl)
                .error(R.drawable.icon_cover_home01)
                .placeholder(R.drawable.icon_cover_home01)
                .into(img);
    }

    @Override
    public void onClick(View v) {
        BookState info = (BookState) v.getTag(-1);
        if (listener != null) {
            listener.click(v, info);
        }
    }

}
