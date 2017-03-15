package org.sltpaya.cartoon.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.sltpaya.cartoon.ActivityItem;
import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.listener.AdapterItemListener;
import org.sltpaya.cartoon.net.entry.RankEntry;

import java.util.List;

import static android.R.attr.label;

/**
 * Author: SLTPAYA
 * Date: 2017/2/22
 */
public class RankHolder extends BaseHolder implements View.OnClickListener {

    private TextView mTitle;
    private TextView mDes;
    private ImageView mImg;
    private AdapterItemListener<ActivityItem> mListener;

    public RankHolder(View itemView) {
        super(itemView);
        initViews();
    }

    public void setRankListener(final AdapterItemListener<ActivityItem> listener) {
        mListener = listener;
    }

    private void initViews() {
        mImg = (ImageView) itemView.findViewById(R.id.item_rank_img);
        mTitle = (TextView) itemView.findViewById(R.id.item_rank_title);
        mDes = (TextView) itemView.findViewById(R.id.item_rank_des);
        itemView.setOnClickListener(this);
    }

    public void updateView(RankEntry entry, String classType) {
        String host = entry.getData().getSrcHost();

        List<RankEntry.Datum> data = entry.getDataList();
        RankEntry.Datum datum = data.get(getAdapterPosition());
        String rankName = datum.getRankName();
        String imgUrl = datum.getPicUrl2() == null ? datum.getPicUrl() : datum.getPicUrl2();

        String itemFeature = datum.getRankId();
        String activityTitle = datum.getRankName();
        setDesText(classType);//设置描述信息

        ActivityItem callItem = new ActivityItem(activityTitle, itemFeature);
        callItem.setType(classType);
        itemView.setTag(-1, callItem);

        mTitle.setText(rankName);
        mTitle.getPaint().setFakeBoldText(true);
        Picasso.with(itemView.getContext()).load(host + imgUrl)
                .placeholder(R.drawable.icon_cover_home01)
                .error(R.drawable.icon_cover_home01)
                .into(mImg);
    }

    @Override
    public void onClick(View v) {
        if (mListener != null) {
            mListener.click(v, (ActivityItem) v.getTag(-1));
        }
    }

    private void setDesText(String classType) {
        int position = getAdapterPosition();
        String[] novels = {
                "全是突破天际的神作~天际好可怜 ┗( T﹏T )┛",
                "大大们请务必收下我的膝盖(。⌒∇⌒)",
                "入坑时间幸福又痛苦的事啊W(￣_￣)W",
                "少女心收割机开始！嗡~嗡~嗡~( ‵▽′)ψ"
        };
        String[] cartoons = {
                "最火最热尽在这里，无数大触等着你(ーー゛)",
                "吾有三百虎鲼，率之可以扫调下。谁人可战？m( =∩王∩= )m",
                "我要用点击量来证明谁才是站在最顶端的王(⊙ ▽ ⊙)",
                "如果不充值，怎么可能变强？！欧皇都是假象！Σ(⊙▽⊙\"a ",
                "装逼如风，常伴吾身！快来学装逼把！(◑▽◐)",
                "你的少女心还在吗？快进来补充补充吧！wow~ ⊙o⊙",
                "愚昧的地球人啊！只有同性才是真爱⁽⁽ଘ( ˊᵕˋ )ଓ⁾⁾*",
                "大触的成神之路！！说不定能捕捉一只野生大触哦(╯▽╰)",
        };
        if ("cartoon".equals(classType)) {
            int index = position > cartoons.length ? cartoons.length : position;
            mDes.setText(cartoons[index]);
        }else {
            int index = position > novels.length ? novels.length : position;
            mDes.setText(novels[index]);
        }
    }

}
