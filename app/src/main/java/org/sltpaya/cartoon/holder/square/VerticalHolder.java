package org.sltpaya.cartoon.holder.square;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.holder.BaseHolder;
import java.util.ArrayList;

/**
 * Author: SLTPAYA
 * Date: 2017/3/1
 */
public class VerticalHolder extends BaseHolder{

    protected ArrayList<View> views;
    protected TextView groupMore;
    protected ImageView groupIcon;
    protected TextView groupTitle;

    public VerticalHolder(View itemView) {
        super(itemView);
        initViews();
    }


    private void initViews() {
        initGroupTitle();
        views = new ArrayList<>();
        int[] ids = {
                R.id.square_1,
                R.id.square_2,
                R.id.square_3,
                R.id.square_4,
                R.id.square_5
        };
        for (int id : ids) {
            View view = itemView.findViewById(id);
            views.add(view);
        }
    }

    protected int getStrokeColor(int index) {
        String[] colors = {
                "#ff7463",
                "#ff9e4d",
                "#60cdb8",
                "#86d49a",
                "#51bef7"
        };
        return Color.parseColor(colors[index]);
    }

    /**
     * 初始化最上方的标题栏
     */
    private void initGroupTitle() {
        groupTitle = (TextView) itemView.findViewById(R.id.title_one_title);
        groupIcon = (ImageView) itemView.findViewById(R.id.title_one_img);
        groupMore = (TextView) itemView.findViewById(R.id.title_one_more);
        groupMore.setText("更多");
    }

}
