package org.sltpaya.cartoon.holder.novel;

import android.view.View;

import org.sltpaya.cartoon.R;
import org.sltpaya.tool.Toast;

/**
 * Author: SLTPAYA
 * Date: 2017/2/27
 */
public class HorizontalTwoHolder extends HorizontalOneHolder {

    public HorizontalTwoHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected int getLineSize() {
        return 2;
    }

    @Override
    protected boolean isShowDes() {
        return false;
    }

    @Override
    protected void setGroup() {
        String[] titles = {
                "轻小说(￣３￣)a",
                "宅文o～(＿△＿o～)",
                "女频≧▽≦y",
                "合作区(☆▽☆)y"
        };
        int[] redId = {
                R.drawable.icon_zhuanqu1,
                R.drawable.icon_zhuanqu2,
                R.drawable.icon_zhuanqu3,
                R.drawable.icon_zhuanqu4
        };
        int[] indexs = {7, 9, 11, 12};
        int now = 0;
        for (int i = 0; i < indexs.length; i++) {
            if (indexs[i] == getAdapterPosition()) {
                now = i;
            }
        }
        groupTitle.setText(titles[now]);
        groupIcon.setImageResource(redId[now]);
        groupMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(itemView.getContext(), "将要加载更多数据啦啦啦....", Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

}
