package org.sltpaya.cartoon.holder.rank;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.sltpaya.cartoon.ActivityItem;
import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.adapter.DataUtils;
import org.sltpaya.cartoon.holder.BaseHolder;
import org.sltpaya.cartoon.listener.AdapterItemListener;
import org.sltpaya.cartoon.net.entry.rank.RednecksEntry;

/**
 * Author: SLTPAYA
 * Date: 2017/3/13
 */
public class RednecksHolder extends BaseHolder {

    private TextView mUserName;
    private TextView mPayMoney;
    private TextView mRankId;
    private ImageView mHeadImg;
    private ImageView mLevelImg;
    private AdapterItemListener<ActivityItem> mListener;

    public RednecksHolder(View itemView) {
        super(itemView);
        initViews();
    }

    private void initViews() {
        mHeadImg = (ImageView) itemView.findViewById(R.id.user_head_img);
        mLevelImg = (ImageView) itemView.findViewById(R.id.level_img);
        mPayMoney = (TextView) itemView.findViewById(R.id.payMoney);
        mRankId = (TextView) itemView.findViewById(R.id.rank_id);
        mUserName = (TextView) itemView.findViewById(R.id.user_name);
    }

    public void setRankListener(AdapterItemListener<ActivityItem> listener) {
        mListener = listener;
    }

    public void updateView(RednecksEntry.Datum datum) {
        String headUrl = datum.getHead();//头像图片
        String rankId = datum.getId();//排名的名次
        String money = datum.getRankNum();//充值了多少钱
        String userName = datum.getNickname();//用户名
        String levelString = datum.getUserLvTitle().getExpLvId();//等级为多少
        int levelNum = DataUtils.parseInt(levelString, 0);//将等级转为数字


        Picasso.with(itemView.getContext()).load(headUrl)
                .error(R.drawable.icon_people)
                .placeholder(R.drawable.icon_people)
                .into(mHeadImg);
        setUserLevel(mLevelImg, levelNum);
        mRankId.getPaint().setFakeBoldText(true);
        mRankId.setText(rankId);
        mUserName.setText(userName);
        mPayMoney.setText("累计打赏："+money+"虫币");
    }

}
