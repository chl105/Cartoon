package org.sltpaya.cartoon.holder.collect;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.holder.BaseHolder;
import org.sltpaya.cartoon.net.entry.collect.CartoonEntry;
import org.sltpaya.cartoon.view.FlagView;
import org.sltpaya.tool.Toast;

import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/3/1
 */
public class CartoonHolder extends BaseHolder {

    private ImageView mImg;
    private TextView mTitle;
    private TextView mAuthor;
    private TextView mViewCount;
    private View mCollect;
    private LinearLayout mSet;

    private int mTotalWidth;
    private int mContainerWidth;

    public CartoonHolder(View itemView) {
        super(itemView);
        initViews();
    }

    private void initViews() {
        mImg = (ImageView) itemView.findViewById(R.id.collect_item_img);
        mTitle = (TextView) itemView.findViewById(R.id.collect_item_title);
        mAuthor = (TextView) itemView.findViewById(R.id.collect_item_author);
        mViewCount = (TextView) itemView.findViewById(R.id.collect_item_count);
        mCollect = itemView.findViewById(R.id.collect_item_collect);
        mSet = (LinearLayout) itemView.findViewById(R.id.collect_item_set);
        mTotalWidth = 0;
        mContainerWidth = mSet.getWidth();
    }

    public void updateView(CartoonEntry entry) {
        List<CartoonEntry.Datum> data = entry.getData().getData();
        CartoonEntry.Datum datum = data.get(getAdapterPosition());
        handleView(datum);
    }

    private void handleView(CartoonEntry.Datum datum) {
        String author = datum.getAuthor();
        String title = datum.getTitle();
        String thumb = datum.getThumb();
        String count = datum.getViews();
        long counts = Long.parseLong(count);
        List<CartoonEntry.Labeltwo> list = datum.getLabeltwo();

        int labelCount = list.size() >= 3 ? 3 : list.size();
        for (int i = 0; i < labelCount; i++) {
            String name = list.get(i).getLabelname();
            FlagView view = new FlagView(itemView.getContext());
            view.setText(name);
            view.setTextSize(12);
            view.setGravity(Gravity.CENTER);
            view.setTextColor(Color.parseColor("#757575"));
            mSet.addView(view);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
            params.rightMargin = 10;
            view.setLayoutParams(params);
        }
        //遍历所有的内容view，获取总宽度
        for (int i = 0; i < mSet.getChildCount(); i++) {
            View view = mSet.getChildAt(i);
            mTotalWidth += view.getMeasuredWidth();
            if (mTotalWidth > mContainerWidth) {
                mSet.removeView(view);
            }
        }

        mTitle.setText(title);
        mViewCount.setText(formatViewCount(counts));
        mAuthor.setText(author);

        Picasso.with(itemView.getContext()).load(thumb)
                .placeholder(R.drawable.icon_cover_home02)
                .error(R.drawable.icon_cover_home02)
                .into(mImg);

        //收藏按钮，点击事件
        mCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(itemView.getContext(), "收藏这项！", Toast.LENGTH_LONG).show();
            }
        });

    }

    /**
     * 格式化观看人数
     */
    private String formatViewCount(long count) {
        int length = 0;
        long tmp = count;
        while (tmp > 0) {
            tmp = tmp / 10;
            length++;
        }
        if (length > 8) {
            double oFloat = ((count / 10000000) / 10.0);
            System.out.println(oFloat);
            return oFloat + "亿人气";
        } else if (length > 4) {
            double oFloat = ((count / 1000) / 10.0);
            return oFloat + "万人气";
        } else if (length > 3) {
            double oFloat = ((count / 100) / 10.0);
            return oFloat + "千人气";
        }
        return count + "人气";
    }

}
