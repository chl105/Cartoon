package org.sltpaya.cartoon.holder.detail;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.adapter.DataUtils;
import org.sltpaya.cartoon.holder.BaseHolder;
import org.sltpaya.cartoon.net.entry.detail.AuthorEntry;
import org.sltpaya.tool.Toast;
import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/3/7
 */
public class DetailHolder extends BaseHolder {

    private TextView mCommentCount;
    private TextView mUserContent;
    private TextView mReplyUserName;
    private View replySet;
    private TextView mComment;
    private TextView mUpdateTime;
    private TextView mReport;
    private ImageView mUserLevel;
    private TextView mUserName;
    private ImageView mUserHead;

    public DetailHolder(View itemView) {
        super(itemView);
        initViews();
    }

    private void initViews() {
        //本人评论（用户头像，用户名）
        mUserHead = (ImageView) itemView.findViewById(R.id.user_head_img);
        mUserName = (TextView) itemView.findViewById(R.id.user_name);
        mUserLevel = (ImageView) itemView.findViewById(R.id.user_level);
        mReport = (TextView) itemView.findViewById(R.id.report);
        mUpdateTime = (TextView) itemView.findViewById(R.id.comment_update_time);
        mComment = (TextView) itemView.findViewById(R.id.author_comment);
        //引用他人评论（用户名、评论内容）
        replySet = itemView.findViewById(R.id.reply_set);
        mReplyUserName = (TextView) itemView.findViewById(R.id.reply_user_name);
        mUserContent = (TextView) itemView.findViewById(R.id.reply_user_content);
        mCommentCount = (TextView) itemView.findViewById(R.id.reply_comment_count);
    }

    public void updateView(AuthorEntry entry) {
        List<AuthorEntry.Datum> list = entry.getDataList();
        AuthorEntry.Datum datum = list.get(getAdapterPosition());
        String headImgUrl = datum.getHead();
        String content = datum.getContent();
        String username = datum.getUsername();
        String updateTime = datum.getCreatAt();//更新时间
        String levelId = datum.getUserLvTitle().getExpLvId();
        int level = DataUtils.parseInt(levelId, 0);
        setUserLevel(mUserLevel, level);
        //引用他人的评论，如果没有就隐藏
        AuthorEntry.ReplyData replyData = datum.getReplyData();
        if (replyData == null) {
            replySet.setVisibility(View.GONE);
        }else {
            replySet.setVisibility(View.VISIBLE);
            String replyName = replyData.getUsername();
            String replyContent = replyData.getContent();
            String replyCount = replyData.getReplyCount();
            mReplyUserName.setText(replyName);
            mUserContent.setText(replyContent);
            mCommentCount.setText(replyCount);
            //如果数量在1之下，就隐藏，异常捕捉，防止解析错误
            int count;
            try {
                count = Integer.parseInt(replyCount);
            } catch (NumberFormatException e) {
                count = 0;
            }
            if (count < 2) {
                mCommentCount.setVisibility(View.GONE);
            }else {
                mCommentCount.setVisibility(View.VISIBLE);
            }
        }

        mReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(itemView.getContext(),"举报该评论！",Toast.LENGTH_LONG).show();
            }
        });
        mComment.setText(content);
        mUserName.setText(username);
        mUpdateTime.setText(updateTime);
        Picasso.with(itemView.getContext()).load(headImgUrl).into(mUserHead);

    }

}
