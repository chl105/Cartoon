package org.sltpaya.cartoon.holder.more;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.adapter.DataUtils;
import org.sltpaya.cartoon.holder.BaseHolder;
import org.sltpaya.cartoon.net.entry.more.CartoonMoreEntry;
import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/3/9
 */
public class CartoonMoreHolder extends BaseHolder {

    private TextView mBookUpdateTime;
    private TextView mBookDes;
    private TextView mChapterName;
    private TextView mBookAuthor;
    private TextView mBookTitle;
    private ImageView mBookFlag;
    private ImageView mBookImg;

    public CartoonMoreHolder(View itemView) {
        super(itemView);
        initViews();
    }

    private void initViews() {
        mBookImg = (ImageView) itemView.findViewById(R.id.book_img);
        mBookFlag = (ImageView) itemView.findViewById(R.id.book_flag);
        mBookTitle = (TextView) itemView.findViewById(R.id.book_title);
        mBookAuthor = (TextView) itemView.findViewById(R.id.book_author);
        mChapterName = (TextView) itemView.findViewById(R.id.book_updateChapter);
        mBookDes = (TextView) itemView.findViewById(R.id.book_des);
        mBookUpdateTime = (TextView) itemView.findViewById(R.id.book_update_time);
    }

    public void updateView(CartoonMoreEntry entry) {
        List<CartoonMoreEntry.Datum> list = entry.getDataList();
        CartoonMoreEntry.Datum datum = list.get(getAdapterPosition());
        String imgUrl = datum.getThumb();
        String title = datum.getTitle();
        String author = datum.getAuthor();
        String chapterName = datum.getUpdateChapterName();
        String description = datum.getDescription();
        String gx = datum.getGxType();
        int gxType = DataUtils.parseInt(gx, 0);
        String updatetime = datum.getUpdatetime();

        Picasso.with(itemView.getContext())
                .load(imgUrl)
                .placeholder(R.drawable.icon_cover_home02)
                .error(R.drawable.icon_cover_home02)
                .into(mBookImg);
        setItemFlag(mBookFlag, gxType);

        mBookTitle.setText(title);
        mBookAuthor.setText(author);
        mChapterName.setText(chapterName);
        mBookDes.setText(description);
        mBookUpdateTime.setText(updatetime);
    }
}
