package org.sltpaya.cartoon.holder.square;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;
import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.net.cache.SquareCache;
import org.sltpaya.cartoon.net.entry.Entry;
import org.sltpaya.cartoon.net.entry.square.FollowerEntry;
import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/3/1
 */
public class FollowerHolder extends VerticalHolder {

    public FollowerHolder(View itemView) {
        super(itemView);
    }

    public void updateView() {
        SquareCache cache = SquareCache.newInstance();
        if (cache.getData() != null) {
            FollowerEntry entry = (FollowerEntry) cache.getData().get(2);
            List<FollowerEntry.Datum> list = entry.getData();
            setTitle();
            for (int i = 0; i < list.size(); i++) {
                handleView(i, views.get(i), list.get(i));
            }
        }
    }

    private void setTitle() {
        groupTitle.setText("人气大神b(￣▽￣)d");
        groupIcon.setImageResource(R.drawable.icon_rank);
    }

    private void handleView(int index, View view, FollowerEntry.Datum datum) {
        RoundedImageView img = (RoundedImageView) view.findViewById(R.id.square_item_img);
        TextView title = (TextView) view.findViewById(R.id.square_item_title);

        img.setBorderColor(getStrokeColor(index));
        String imgUrl = datum.getHead();
        String name = datum.getNickname();
        title.setText(name);

        Picasso.with(itemView.getContext()).load(imgUrl).into(img);
    }

}
