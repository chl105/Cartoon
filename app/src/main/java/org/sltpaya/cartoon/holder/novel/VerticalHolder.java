package org.sltpaya.cartoon.holder.novel;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.holder.BaseHolder;
import org.sltpaya.cartoon.net.cache.NovelCache;
import org.sltpaya.cartoon.net.entry.Entry;
import org.sltpaya.cartoon.net.entry.novel.UpdateEntry;
import org.sltpaya.tool.Toast;
import org.sltpaya.tool.Utils;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.handle;

/**
 * Author: SLTPAYA
 * Date: 2017/2/27
 */
public class VerticalHolder extends BaseHolder {

    private ArrayList<View> mViews;

    public VerticalHolder(View itemView) {
        super(itemView);
        initViews();
    }

    private void initViews() {
        initGroupTitle();
        mViews = new ArrayList<>();
        View head = itemView.findViewById(R.id.novel_group_head);
        mViews.add(0, head);
        int[] ids = {
                R.id.novel_group_1,
                R.id.novel_group_2,
                R.id.novel_group_3,
                R.id.novel_group_4,
                R.id.novel_group_5
        };
        for (int id : ids) {
            View view = itemView.findViewById(id);
            mViews.add(view);
        }
    }

    /**
     * 初始化最上方的标题栏
     */
    private void initGroupTitle() {
        TextView groupTitle = (TextView) itemView.findViewById(R.id.title_one_title);
        ImageView groupIcon = (ImageView) itemView.findViewById(R.id.title_one_img);
        View view = itemView.findViewById(R.id.title_one_more);

        groupTitle.setText("刚刚更新O(∩_∩)O~~");
        groupIcon.setImageResource(R.drawable.icon_new_update);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(itemView.getContext(),"将要加载更多数据啦啦啦....",Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    @Override
    public void updateView() {
        NovelCache cache = NovelCache.newInstance();
        if (cache.getData() != null) {
            UpdateEntry entry = (UpdateEntry) cache.getData().get(18);
            List<UpdateEntry.Datum> data = entry.getData().getData();
            setData(data);
        }
    }

    private void setData(List<UpdateEntry.Datum> data) {
        for (int i = 0; i < data.size(); i++) {
            if (i == 0) {
                handleHeadView(mViews.get(i), data.get(i));
            }else {
                handleView(mViews.get(i), data.get(i));
            }
        }
    }

    private void handleView(View view, UpdateEntry.Datum datum) {
        TextView title = (TextView) view.findViewById(R.id.novel_item_title);
        TextView flag = (TextView) view.findViewById(R.id.novel_item_flag);
        TextView des = (TextView) view.findViewById(R.id.novel_item_des);

        String name= datum.getTitle();
        String description = datum.getDescription();

        title.setText(name);
        flag.setText("女频");
        des.setText(description);
    }

    private void handleHeadView(View view, UpdateEntry.Datum datum) {
        ImageView img = (ImageView) view.findViewById(R.id.novel_item_img);
        TextView title = (TextView) view.findViewById(R.id.novel_item_title);
        TextView viewCount = (TextView) view.findViewById(R.id.novel_item_count);
        TextView flag = (TextView) view.findViewById(R.id.novel_item_flag);
        TextView des = (TextView) view.findViewById(R.id.novel_item_des);

        String imgUrl = datum.getThumb();
        String name= datum.getTitle();
        String description = datum.getDescription();
        String views = datum.getViews();
        String label = datum.getShowLabel();

        //对字符串进行格式处理
        label = label.replaceAll("\\[+", "").replaceAll("\\]+", "").replaceAll("\\d,", "")
                .replace("\"","");

        title.setText(name);
        viewCount.setText(String.format("%s%s", views, Utils.getString(R.string.follower_count)));
        flag.setText(label);
        des.setText(description);

        Picasso.with(itemView.getContext()).load(imgUrl)
                .placeholder(R.drawable.icon_cover_home01)
                .error(R.drawable.icon_cover_home01)
                .into(img);
    }

}
