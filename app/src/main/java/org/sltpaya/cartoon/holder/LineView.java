package org.sltpaya.cartoon.holder;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/7/23
 */
public abstract class LineView {

    public LinearLayout rootView;//该行的根View
    public List<Item> items = new ArrayList<>();//存储该行中所有的Item对象
    private Context context;

    public LineView(Context context) {
        this.context = context;
        rootView = new LinearLayout(context);

        int padding = dpToPx(10);
        rootView.setPadding(padding, 0, padding, 0);
        LinearLayout.LayoutParams l
                = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        rootView.setLayoutParams(l);
    }

    public void addItem(int size) {
        int spaceSize = size - 1;
        for (int i = 0; i < size; i++) {
            addItem();
            if (spaceSize > 0) {
                addSpace();
                spaceSize--;
            } else {
                break;
            }
        }
    }

    /**
     * 添加Item之间的间隔
     */
    public final void addSpace() {
        LinearLayout.LayoutParams l
                = new LinearLayout.LayoutParams(dpToPx(10), 0);
        Space space = new Space(context);
        space.setLayoutParams(l);
        rootView.addView(space);
    }

    int dpToPx(int dp) {
        DisplayMetrics m = context.getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, m);
    }

    /**
     * 调用该方法向行内添加Item对象
     */
    public final void addItem() {
        Item item = newItem();
        if (item != null) {
            rootView.addView(item.parent);
            items.add(item);
        }
    }

    public abstract Item newItem();

    /**
     * 行内的Item对象
     */
    public class Item {

        public View parent;
        public ImageView thumbImage;//作品图片
        public ImageView flagImage;//作品的更新flag
        public TextView title;//作品的标题
        public TextView author;//作品的作者或者详情

        public Item(View parent, ImageView thumbImage, ImageView flagImage,
                    TextView title, TextView author) {
            this.parent = parent;
            this.thumbImage = thumbImage;
            this.flagImage = flagImage;
            this.title = title;
            this.author = author;
        }
    }


}
