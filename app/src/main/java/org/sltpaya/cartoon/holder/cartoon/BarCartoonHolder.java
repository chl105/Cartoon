package org.sltpaya.cartoon.holder.cartoon;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.holder.BaseHolder;
import org.sltpaya.cartoon.net.entry.BarCartoonEntry;
import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/2/22
 */
public class BarCartoonHolder extends BaseHolder {

    private TextView mAuthor;
    private TextView mCommentCount;
    private TextView mTitle;
    private TextView mType;
    private TextView mUpdateName;
    private ImageView mImg;

    public BarCartoonHolder(View itemView) {
        super(itemView);
        initViews();
    }

    private void initViews() {
        mAuthor = (TextView) itemView.findViewById(R.id.item_bar_author);
        mCommentCount = (TextView) itemView.findViewById(R.id.item_bar_comment);
        mTitle = (TextView) itemView.findViewById(R.id.item_bar_title);
        mType = (TextView) itemView.findViewById(R.id.item_bar_type);
        mUpdateName = (TextView) itemView.findViewById(R.id.item_bar_update);
        mImg = (ImageView) itemView.findViewById(R.id.item_bar_img);
    }

    public void updateView(BarCartoonEntry entry) {
        String host = entry.getData().getCdn();
        BarCartoonEntry.Datum datum = entry.getDataList().get(getAdapterPosition());
        setData(datum, host);
    }

    private void setData(BarCartoonEntry.Datum datum, String host) {
        String author = datum.getAuthor();
        String updateChapterName = datum.getUpdateChapterName();
        String title = datum.getTitle();
        String commentNum = datum.getCommentNum();
        String typeName = datum.getClassLabel().getClassName();
        String imgUrl = host + datum.getThumbRank();

        mAuthor.setText(author);
        mUpdateName.setText(updateChapterName);
        mTitle.setText(title);
        mCommentCount.setText(commentNum);
        mType.setText(typeName);
        Picasso.with(itemView.getContext()).load(imgUrl)
                .placeholder(R.drawable.icon_cover_home03)
                .error(R.drawable.icon_cover_home03)
                .into(mImg);
    }
}
