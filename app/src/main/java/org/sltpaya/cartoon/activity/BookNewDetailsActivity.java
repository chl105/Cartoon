package org.sltpaya.cartoon.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.adapter.DataUtils;
import org.sltpaya.cartoon.holder.detail.SameBookHolder;
import org.sltpaya.cartoon.listener.AdapterItemListener;
import org.sltpaya.cartoon.net.cache.BookDetailCache;
import org.sltpaya.cartoon.net.cache.NetCache;
import org.sltpaya.cartoon.net.entry.Entry;
import org.sltpaya.cartoon.net.entry.detail.BookDetailAdEntry;
import org.sltpaya.cartoon.net.entry.detail.BookDetailEntry;
import org.sltpaya.cartoon.net.entry.detail.DetailSameEntry;
import org.sltpaya.tool.Toast;

/**
 * Author: SLTPAYA
 * Date: 2017/3/4
 */
public class BookNewDetailsActivity extends BaseActivity implements NetCache.DataSuccessful {

    private TextView mStartRead;
    private TextView mUpdate;
    private TextView mFans;
    private TextView mAuthor;
    private TextView mBookName;
    private TextView mBookDes;
    private TextView mBookScore;

    private ImageView mBookImg;
    private ImageView mAdImage;

    private View mBack;
    private View buttonCollect;
    private View buttonShared;
    private View buttonMoney;

    private String mParamsBookid;
    /**
     * 作品简介显示或隐藏
     */
    private boolean isShowText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_new_detail);
        Bundle extras = getIntent().getExtras();
        mParamsBookid = extras.getString("bookid", "no");
        initViews();
        processNetEvent();
        setListenerEvent();
    }

    private void initViews() {
        //作品信息（作品名，作者，更新时间）
        mBookImg = (ImageView) findViewById(R.id.book_img);
        mBookName = (TextView) findViewById(R.id.book_name);
        mAuthor = (TextView) findViewById(R.id.book_author);
        mFans = (TextView) findViewById(R.id.book_fans);
        mUpdate = (TextView) findViewById(R.id.book_update_info);
        mBookScore = (TextView) findViewById(R.id.book_grade);
        mBookDes = (TextView) findViewById(R.id.book_des);
        mBack = findViewById(R.id.back_activity);
        mStartRead = (TextView) findViewById(R.id.book_start_read);
        //收藏，分享，打赏按钮
        buttonCollect = findViewById(R.id.book_add_collect);
        buttonShared = findViewById(R.id.book_shared);
        buttonMoney = findViewById(R.id.book_money);
        //广告图
        mAdImage = (ImageView) findViewById(R.id.book_ad);

        initIndicator();
        //作品简介多余部分的隐藏
        handleExpandText();
    }

    private void handleExpandText() {
        //多余文字是否隐藏
        View mExpandText = findViewById(R.id.expand_text);
        isShowText = false;
        mExpandText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isShowText = !isShowText;
                if (mBookDes.getLineCount() > 2) {
                    if (isShowText) {
                        ((ImageView) v).setImageResource(R.drawable.icon_collapse);
                    } else {
                        ((ImageView) v).setImageResource(R.drawable.icon_expand);
                    }
                } else {
                    ((ImageView) v).setImageResource(0);
                }
                showExpandText(mBookDes, isShowText);
            }
        });
    }

    /**
     * 展开小说简介
     */
    private void showExpandText(TextView view, boolean isShow) {
        int lineCount = view.getLineCount();
        if (isShow) {
            view.setSingleLine(false);
            view.setEllipsize(null);
        } else if (lineCount > 2) {
            view.setLines(2);
            view.setEllipsize(TextUtils.TruncateAt.END);
        }
    }


    /**
     * 作者专区和评论
     */
    private void initIndicator() {
//        TabLayout tabLayout = (TabLayout) findViewById(R.id.indicator);
//        tabLayout.addTab(tabLayout.newTab().setText("作者专区"), true);
//        tabLayout.addTab(tabLayout.newTab().setText("评论"), false);
//
//        final AuthorFragment authorFragment = new AuthorFragment();
//        final CommentFragment commentFragment = new CommentFragment();
//
//        //添加Fragment
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.add(R.id.indicator_container, authorFragment);
//        transaction.add(R.id.indicator_container, commentFragment);
//        transaction.commit();
//
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                int position = tab.getPosition();
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                if (position == 0) {
//                    System.out.println("切换到了AuthorFragment");
//                    transaction.hide(commentFragment);
//                    transaction.show(authorFragment);
//                } else if (position == 1) {
//                    System.out.println("切换到了CommentFragment!");
//                    transaction.hide(authorFragment);
//                    transaction.show(commentFragment);
//                }
//                transaction.commit();
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//            }
//        });
    }

    /**
     * 处理小说信息
     *
     * @param entry BookDetailEntry
     */
    private void handleBookData(BookDetailEntry entry) {
        BookDetailEntry.Data data = entry.getData();

        String imgUrl = data.getThumb();
        String strTitle = data.getTitle();
        String strAuthor = "作者：" + data.getAuthor();
        String strDes = data.getDescription();
        String strCore = data.getAverageScore();
        String strGxType = data.getGxType();
        String strUpdateTime = data.getUpdatetime();
        String strHit = data.getHitNum();//点击数量
        String strViews = data.getViews();//人气数量

        int gxType = DataUtils.parseInt(strGxType, 0);
        float book_score = DataUtils.parseFloat(strCore, 0.0F);
        book_score = ((int) ((book_score + 0.05F) * 10)) / 10.0f;
        long updateTime = DataUtils.parseLong(strUpdateTime, System.currentTimeMillis()) * 1000;

        String hitNum = DataUtils.parseDataNum(strHit, 0);
        String bookViews = DataUtils.parseDataNum(strViews, 0);
        String strUpdate = DataUtils.getBookFrequency(gxType) + "  " +
                DataUtils.getUpdateFormatDate(updateTime);
        String strFans = "点击：" + hitNum + "   人气：" + bookViews;

        TextPaint paint = mAuthor.getPaint();
        paint.setFlags(Paint.UNDERLINE_TEXT_FLAG);//下划线
        paint.setAntiAlias(true);
        mAuthor.setText(strAuthor);//作者名是可以点击的，带有下划线，可点击的超链接

        mBookName.setText(strTitle);//作品名
        mBookDes.setText(strDes);//作品简介，只显示两行，超过的行自动隐藏
        mBookScore.setText(String.valueOf(book_score));//作品分数
        mUpdate.setText(strUpdate);
        mFans.setText(strFans);

        Picasso.with(this).load(imgUrl).into(mBookImg);
    }

    /**
     * 请求网络数据
     */
    private void processNetEvent() {
        BookDetailCache cache = BookDetailCache.newInstance(mParamsBookid);
        cache.setDataListener(this);
        cache.requestNet();
    }

    @Override
    public void onResponse(SparseArray<Entry> data) {
        BookDetailEntry entry = (BookDetailEntry) data.get(1);
        DetailSameEntry sameEntry = (DetailSameEntry) data.get(2);
        BookDetailAdEntry adEntry = (BookDetailAdEntry) data.get(3);
        handleBookData(entry);
        handleSameBook(sameEntry);
        handleDetailAd(adEntry);
    }

    /**
     * 处理详情页广告图
     *
     * @param entry BookDetailAdEntry
     */
    private void handleDetailAd(BookDetailAdEntry entry) {
        BookDetailAdEntry.Data data = entry.getData();
        String imgUrl = data.getThumb();

        Picasso.with(this).load(imgUrl)
                .placeholder(R.drawable.icon_cover_home03)
                .error(R.drawable.icon_cover_home03)
                .into(mAdImage);
    }

    /**
     * 处理同类书籍
     *
     * @param entry DetailSameEntry
     */
    private void handleSameBook(DetailSameEntry entry) {
        View view = findViewById(R.id.book_same);
        SameBookHolder holder = new SameBookHolder(view);
        holder.updateView(entry);
        holder.setListener(new AdapterItemListener<SparseArray>() {
            @Override
            public void click(View v, SparseArray info) {
                String viewType = (String) info.get(0);
                String bookId = (String) info.get(1);
                if ("1".equals(viewType)) {
                    System.out.println("进入条漫Activity" + bookId);
                    entryStripManDetail(bookId);
                } else {
                    System.out.println("进入漫画详情页了！" + bookId);
                    entryBookNewDetails(bookId);
                }
            }
        });
    }


    /**
     * 收藏，分享，打赏按钮点击事件，以及本页面所有控件点击事件的处理
     */
    private void setListenerEvent() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                switch (id) {
                    case R.id.back_activity:
                        finish();
                        break;
                    case R.id.book_start_read:
                        Toast.makeText
                                (BookNewDetailsActivity.this, "将要阅读漫画了！", Toast.LENGTH_SHORT)
                                .show();
                        break;
                    case R.id.book_add_collect:
                        Toast.makeText
                                (BookNewDetailsActivity.this, "点击了收藏按钮！", Toast.LENGTH_SHORT)
                                .show();
                        break;
                    case R.id.book_shared:
                        Toast.makeText
                                (BookNewDetailsActivity.this, "点击了分享按钮！", Toast.LENGTH_SHORT)
                                .show();
                        break;
                    case R.id.book_money:
                        Toast.makeText
                                (BookNewDetailsActivity.this, "点击了打赏按钮!", Toast.LENGTH_SHORT)
                                .show();
                        break;
                }
            }
        };
        mStartRead.setOnClickListener(listener);
        mBack.setOnClickListener(listener);
        buttonCollect.setOnClickListener(listener);
        buttonShared.setOnClickListener(listener);
        buttonMoney.setOnClickListener(listener);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            this.finish();
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void entryStripManDetail(String bookid) {
        Intent intent = new Intent(BookNewDetailsActivity.this, StripManDetailActivity.class);
        intent.putExtra("bookid", bookid);
        startActivity(intent);
    }

    protected void entryBookNewDetails(String bookid) {
        Intent intent = new Intent(BookNewDetailsActivity.this, BookNewDetailsActivity.class);
        intent.putExtra("bookid", bookid);
        startActivity(intent);
    }

    @Override
    public void overridePendingTransition(int enterAnim, int exitAnim) {
        super.overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    }

}
