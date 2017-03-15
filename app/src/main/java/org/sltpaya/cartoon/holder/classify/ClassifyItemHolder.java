package org.sltpaya.cartoon.holder.classify;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import org.sltpaya.cartoon.BookState;
import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.adapter.DataUtils;
import org.sltpaya.cartoon.holder.BaseHolder;
import org.sltpaya.cartoon.listener.AdapterItemListener;
import org.sltpaya.cartoon.net.entry.classify.ClassifyItemEntry;

/**
 * Author: SLTPAYA
 * Date: 2017/3/9
 */
public class ClassifyItemHolder extends BaseHolder implements View.OnClickListener{

    private TextView mBookDes;
    private TextView mViewCount;
    private TextView mBookAuthor;
    private TextView mBookTitle;
    private ImageView mBookFlag;
    private ImageView mBookImg;
    private AdapterItemListener<BookState> listener;

    public ClassifyItemHolder(View itemView) {
        super(itemView);
        initViews();
    }

    @Override
    public void setListener(AdapterItemListener<BookState> listener) {
        this.listener = listener;
    }

    private void initViews() {
        mBookImg = (ImageView) itemView.findViewById(R.id.book_img);
        mBookFlag = (ImageView) itemView.findViewById(R.id.book_flag);
        mBookTitle = (TextView) itemView.findViewById(R.id.book_title);
        mBookAuthor = (TextView) itemView.findViewById(R.id.book_author);
        mViewCount = (TextView) itemView.findViewById(R.id.book_viewcount);
        mBookDes = (TextView) itemView.findViewById(R.id.book_des);
    }

    public void updateView(ClassifyItemEntry.Datum datum, String classType) {
        String viewType = datum.getViewType();
        String bookid = datum.getBookid();
        String author = datum.getAuthor();
        String title = datum.getTitle();
        String imgUrl = datum.getThumb();
        String viewCount = datum.getViews();
        String gx = datum.getGxType();
        String des = datum.getDescription();
        int gxType = DataUtils.parseInt(gx, 0);

        mBookTitle.setText(title);
        mBookAuthor.setText(author);
        Picasso.with(itemView.getContext()).load(imgUrl).into(mBookImg);
        mViewCount.setText(viewCount);
        mBookDes.setText(des);
        setItemFlag(mBookFlag, gxType);

        //设置点击事件：
        itemView.setOnClickListener(this);
        BookState state = new BookState(bookid);
        state.setClassType(classType);
        if ("cartoon".equals(classType) && viewType != null) {
            state.setShowViewType(viewType);
        }
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
