package org.sltpaya.cartoon.holder.square;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.holder.BaseHolder;
import org.sltpaya.cartoon.net.cache.SquareCache;
import org.sltpaya.cartoon.net.entry.square.NormalEntry;
import org.sltpaya.tool.Utils;

import java.util.List;

import static android.R.attr.host;

/**
 * Author: SLTPAYA
 * Date: 2017/3/3
 */
public class NormalHolder extends BaseHolder {

    private static String host_2 = "http://cdns2.517w.com/";
    private static String host_3 = "http://cdns3.517w.com/";
    private TextView mHeart;
    private TextView mMessage;
    private TextView mShared;
    private TextView mAuthor;
    private TextView mContent;
    private TextView mTitle;
    private ImageView mImg;
    private ImageView mHead;

    public NormalHolder(View itemView) {
        super(itemView);
        initViews();
    }

    private void initViews() {
        mHead = (ImageView) itemView.findViewById(R.id.square_normal_head);
        mImg = (ImageView) itemView.findViewById(R.id.square_normal_img);
        mTitle = (TextView) itemView.findViewById(R.id.square_normal_title);
        mContent = (TextView) itemView.findViewById(R.id.square_normal_content);
        mAuthor = (TextView) itemView.findViewById(R.id.square_normal_author);
        mShared = (TextView) itemView.findViewById(R.id.square_normal_shared);
        mMessage = (TextView) itemView.findViewById(R.id.square_normal_message);
        mHeart = (TextView) itemView.findViewById(R.id.square_normal_heart);
    }

    @Override
    public void updateView() {
        SquareCache cache = SquareCache.newInstance();
        if (cache.getData() != null) {
            NormalEntry entry = (NormalEntry) cache.getData().get(3);
            List<NormalEntry.Datum> list = entry.getData().getData();
            NormalEntry.Datum datum = list.get(getAdapterPosition() - 3);//减去前三项的头
            handleView(datum);
        }
    }


    private void handleView(NormalEntry.Datum datum) {
        String name = datum.getTitle();
        String author = datum.getAuthor();
        String headUrl = datum.getHead();
        String imgUrl = datum.getThumb();
        String shareCount = datum.getShareCount();
        String commentNum = datum.getCommentNum();
        String heart = datum.getPraise();
        String des = datum.getContent();

        mTitle.setText("来自：" + name);
        mContent.setText(des);
        mAuthor.setText(author);
        mShared.setText(shareCount);
        mMessage.setText(commentNum);
        mHeart.setText(heart);
        String host;
        if (datum.getSrcServerId().equals("2016")) {
            host = host_3;
        }else {
            host = host_2;
        }
        Picasso.with(itemView.getContext()).load(headUrl)
                .placeholder(R.drawable.icon_cover_home02)
                .into(mHead);
        Picasso.with(itemView.getContext()).load(host + imgUrl)
                .placeholder(R.drawable.icon_cover_home02)
                .error(R.drawable.icon_cover_home02)
                .into(mImg);

    }

}
