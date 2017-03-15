package org.sltpaya.cartoon.holder.rank;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.sltpaya.cartoon.BookState;
import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.adapter.DataUtils;
import org.sltpaya.cartoon.holder.BaseHolder;
import org.sltpaya.cartoon.listener.AdapterItemListener;
import org.sltpaya.cartoon.net.entry.rank.RankItemEntry;

import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/3/8
 */
public class RankItemHolder extends BaseHolder implements View.OnClickListener{

    private TextView mRank;
    private TextView mViewCount;
    private TextView mBookAuthor;
    private TextView mBookTitle;
    private ImageView mRankBookFlag;
    private ImageView mRankBookImg;
    private AdapterItemListener<BookState> listener;

    public RankItemHolder(View itemView) {
        super(itemView);
        initViews();
    }

    @Override
    public void setListener(AdapterItemListener<BookState> listener) {
        this.listener = listener;
    }

    private void initViews() {
        mRankBookImg = (ImageView) itemView.findViewById(R.id.rank_book_img);
        mRankBookFlag = (ImageView) itemView.findViewById(R.id.rank_book_flag);
        mBookTitle = (TextView) itemView.findViewById(R.id.rank_book_title);
        mBookAuthor = (TextView) itemView.findViewById(R.id.rank_book_author);
        mViewCount = (TextView) itemView.findViewById(R.id.rank_book_viewcount);
        mRank = (TextView) itemView.findViewById(R.id.rank_book_rank);
    }

    public void updateView(RankItemEntry entry, String classType) {
        int position = getAdapterPosition();
        List<RankItemEntry.Datum> list = entry.getDataList();
        RankItemEntry.Datum datum = list.get(position);
        String bookid = datum.getBookid();
        String viewType = datum.getViewType();
        String author = datum.getAuthor();
        String imgUrl = datum.getThumb();
        String title = datum.getTitle();
        String views = datum.getViews();
//        String rankId = datum.getRankId();
        String type = datum.getGxType();
        int flagType = DataUtils.parseInt(type, 0);

        mBookTitle.setText(title);
        mBookAuthor.setText(author);
        mViewCount.setText(views);
        int color = Color.parseColor("#cdcdcd");
        if (position == 0) {
            color = Color.parseColor("#fa5451");
        }
        mRank.getPaint().setFakeBoldText(true);
        mRank.setTextColor(color);
        mRank.setText(String.valueOf(position + 1));
        setItemFlag(mRankBookFlag, flagType);

        Picasso.with(itemView.getContext()).load(imgUrl).into(mRankBookImg);

        //设置点击事件：
        itemView.setOnClickListener(this);
        BookState state = new BookState(bookid);
        state.setClassType(classType);
        state.setShowViewType(viewType);
        itemView.setTag(-1, state);
    }

    @Override
    public void onClick(View v) {
        BookState state = (BookState) v.getTag(-1);
        if (listener != null) {
            listener.click(v, state);
        }
    }

}
