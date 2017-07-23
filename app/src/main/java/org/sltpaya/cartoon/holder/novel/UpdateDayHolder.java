package org.sltpaya.cartoon.holder.novel;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.holder.BaseHolder;
import org.sltpaya.cartoon.net.entry.UpdateDayEntry;
import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/2/25
 */
public class UpdateDayHolder extends BaseHolder {

    private TextView mTitle;
    private TextView mCount;
    private TextView mUpdate;
    private TextView mDes;
    private ImageView mImg;

    public UpdateDayHolder(View itemView) {
        super(itemView);
        initViews();
    }

    private void initViews() {
        mImg = (ImageView) itemView.findViewById(R.id.item_day_img);
        mTitle = (TextView) itemView.findViewById(R.id.item_day_title);
        mCount = (TextView) itemView.findViewById(R.id.item_day_count);
        mUpdate = (TextView) itemView.findViewById(R.id.item_day_updateTitle);
        mDes = (TextView) itemView.findViewById(R.id.item_day_des);
    }


    public void updateView(UpdateDayEntry entry) {
        List<UpdateDayEntry.Conrhtml> list = entry.getConrhtml();
        if (list != null && list.size() != 0) {
            UpdateDayEntry.Conrhtml data = list.get(getAdapterPosition());
            setData(data);
        }
    }

    private void setData(UpdateDayEntry.Conrhtml data) {
        String des = data.getCDescription();
        des = des.replaceAll(" +", "").replace("\n", "").replaceAll("&\\w+;", "");
        String title = data.getTitle();
        String chapterName = data.getUpdateChapterName();
        String views = data.getViews();
        String imgUrl = data.getThumb();
        System.out.println("详情内容："+des+"imgUrl"+imgUrl);

        mTitle.getPaint().setFakeBoldText(true);
        mTitle.setText(title);
        mUpdate.setText(chapterName);
        mDes.setText(des);
        mCount.getPaint().setFakeBoldText(true);
        mCount.setText(views);

        if (imgUrl != null && !imgUrl.isEmpty()) {
            Picasso.with(itemView.getContext()).load(imgUrl)
                    .placeholder(R.drawable.icon_cover_home03)
                    .error(R.drawable.icon_cover_home03)
                    .config(Bitmap.Config.RGB_565)
                    .into(mImg);
        }
    }

}
