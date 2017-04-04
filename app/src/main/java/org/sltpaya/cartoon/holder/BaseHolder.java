package org.sltpaya.cartoon.holder;

import android.support.annotation.IntDef;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;

import org.sltpaya.cartoon.BookState;
import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.listener.AdapterItemListener;
import org.sltpaya.tool.Toast;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

import static android.R.attr.level;
import static android.R.attr.visibility;

/**
 * Author: SLTPAYA
 * Date: 2017/2/21
 */
public abstract class BaseHolder extends RecyclerView.ViewHolder {

    public BaseHolder(View itemView) {
        super(itemView);
    }

    /**
     * updateView方法用于更新视图
     * 只有true才会设置数据
     */
    public void updateView() {}

    /**
     * 从外传入点击事件的监听器
     * @param listener {@link AdapterItemListener}
     */
    public void setListener(AdapterItemListener<BookState> listener) {}

    /**
     * 设置集合中View的显示属性
     * @param views View
     * @param visibility {@link View}
     */
    public void setVisibility(ArrayList<View> views, int visibility) {
        for (View view : views) {
            view.setVisibility(visibility);
        }
    }


    /**
     * 为作品设置更新类型
     * @param view 设置的ImgeView
     * @param typeId 更新类型id，由服务器提供
     */
    protected void setUpdateType(ImageView view, int typeId) {
        //策略：凡是低于每周两更(2)，每周更新(1)的全部不显示设置
        int[] resIds = {
                R.drawable.icon_tab2_6,
                R.drawable.icon_tab2_2,
                R.drawable.icon_tab2_3,
                R.drawable.icon_tab2_4,
                R.drawable.icon_tab2_5,
                R.drawable.icon_tab2_1,
                R.drawable.icon_tab2_8,
                R.drawable.icon_tab2_9,
                R.drawable.icon_tab2_7,
                R.drawable.icon_tab2_10
        };
        if (typeId > 0 && typeId <= resIds.length) {
            if (typeId != 2 && typeId != 1) {
                view.setImageResource(resIds[typeId - 1]);
            }
        }
    }

    protected void setItemFlag(ImageView view, int flagType) {
        int[] resIds = {
                R.drawable.icon_lianzaizhong,
                R.drawable.icon_tab_6,
                R.drawable.icon_tab_2,
                R.drawable.icon_tab_3,
                R.drawable.icon_tab_4,
                R.drawable.icon_tab_5,
                R.drawable.icon_tab_1,
                R.drawable.icon_tab_8,
                R.drawable.icon_tab_9,
                R.drawable.icon_tab_7,
                R.drawable.icon_tab_10
        };
        if (flagType >= 0 && flagType <= resIds.length) {
            view.setImageResource(resIds[flagType]);
        }
    }

    public void setUserLevel(ImageView mUserLevel, int level) {
        int[] levels = {
                R.drawable.level_one,
                R.drawable.level_two,
                R.drawable.level_three,
                R.drawable.level_four,
                R.drawable.level_five,
                R.drawable.level_six,
                R.drawable.level_seven,
                R.drawable.level_eight,
                R.drawable.level_nine,
                R.drawable.level_twn,
                R.drawable.level_eleven,
                R.drawable.level_twelve,
                R.drawable.level_thirteen,
                R.drawable.level_fourteen,
                R.drawable.level_fifteen,
                R.drawable.level_sixteen,
                R.drawable.level_seventeen,
                R.drawable.level_eighteen,
                R.drawable.level_nineteen,
                R.drawable.level_twenty
        };
        mUserLevel.setImageResource(levels[level]);
    }

}
