package org.sltpaya.cartoon.holder.square;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;
import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.net.cache.SquareCache;
import org.sltpaya.cartoon.net.entry.square.HotEntry;
import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/3/1
 */
public class HotHolder extends VerticalHolder {

    private String cdnHost;

    public HotHolder(View itemView) {
        super(itemView);
    }

    public void updateView() {
        SquareCache cache = SquareCache.newInstance();
        if (cache.getData() != null) {
            HotEntry entry = (HotEntry) cache.getData().get(1);
            cdnHost = entry.getData().getCdn();
            List<HotEntry.Datum> list = entry.getData().getData();
            setTitle();
            for (int i = 0; i < list.size(); i++) {
                handleView(i, views.get(i), list.get(i));
            }
        }
    }

    private void setTitle() {
        groupTitle.setText("热点圈子(*^__^*)");
        groupIcon.setImageResource(R.drawable.icon_square_hot);
    }

    private void handleView(int index, View view, HotEntry.Datum datum) {
        RoundedImageView img = (RoundedImageView) view.findViewById(R.id.square_item_img);
        TextView title = (TextView) view.findViewById(R.id.square_item_title);

        img.setBorderColor(getStrokeColor(index));
        String imgUrl = datum.getThumb();
        String name = datum.getTitle();
        title.setText(name);

        Picasso.with(itemView.getContext()).load(cdnHost + imgUrl).into(img);
    }

}
