package org.sltpaya.cartoon.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.sltpaya.cartoon.ActivityItem;
import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.listener.AdapterItemListener;
import org.sltpaya.cartoon.net.entry.ClassifyEntry;

import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/2/22
 */
public class ClassifyHolder extends BaseHolder implements View.OnClickListener{

    private TextView mTitle;
    private ImageView mImg;
    private AdapterItemListener<ActivityItem> listener;

    public ClassifyHolder(View itemView) {
        super(itemView);
        initViews();
    }

    public void setClassifyListener(AdapterItemListener<ActivityItem> listener) {
        this.listener = listener;
    }

    private void initViews() {
        mImg = (ImageView) itemView.findViewById(R.id.item_claaify_img);
        mTitle = (TextView) itemView.findViewById(R.id.item_claaify_title);
    }

    public void updateView(ClassifyEntry entry, String classType) {
        String host = entry.getData().getSrcHost();

        List<ClassifyEntry.Datum> desData = entry.getDataList();
        ClassifyEntry.Datum datum = desData.get(getAdapterPosition());
        String picUrl = host + datum.getPicUrl();
        String labelname = datum.getLabelname();
        String labelId = datum.getLabelId();
        mTitle.setText(labelname);
        Picasso.with(itemView.getContext()).load(picUrl).into(mImg);

        itemView.setOnClickListener(this);
        ActivityItem item = new ActivityItem(labelname, labelId);
        item.setType(classType);//传递给上级，该数据是从哪里返回的，cartoon或者novel
        itemView.setTag(-1, item);
    }


    @Override
    public void onClick(View v) {
        ActivityItem item = (ActivityItem) v.getTag(-1);
        if (listener != null) {
            listener.click(v, item);
        }
    }

}
