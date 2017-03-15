package org.sltpaya.cartoon.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.fragment.detail.AuthorFragment;
import org.sltpaya.cartoon.fragment.detail.CommentFragment;
import org.sltpaya.cartoon.holder.detail.SameBookHolder;
import org.sltpaya.cartoon.listener.AdapterItemListener;
import org.sltpaya.cartoon.net.cache.BookDetailCache;
import org.sltpaya.cartoon.net.cache.NetCache;
import org.sltpaya.cartoon.net.entry.Entry;
import org.sltpaya.cartoon.net.entry.detail.BookDetailAdEntry;
import org.sltpaya.cartoon.net.entry.detail.BookDetailEntry;
import org.sltpaya.cartoon.net.entry.detail.DetailSameEntry;
import org.sltpaya.tool.Toast;

import static android.media.CamcorderProfile.get;

/**
 * Author: SLTPAYA
 * Date: 2017/3/4
 */
public class BookNewDetailsActivity extends FragmentActivity implements NetCache.DataSuccessful {

    private TextView mStartRead;
    private TextView mUpdate;
    private TextView mFans;
    private TextView mAuthor;
    private TextView mBookName;
    private TextView mDes;
    private TextView mGrade;

    private ImageView mBookImg;
    private ImageView mAdImage;

    private View mBack;
    private View buttonCollect;
    private View buttonShared;
    private View buttonMoney;

    private String mParamsBookid;

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
        mBookImg = (ImageView) findViewById(R.id.book_img);
        mBookName = (TextView) findViewById(R.id.book_name);
        mAuthor = (TextView) findViewById(R.id.book_author);
        mFans = (TextView) findViewById(R.id.book_fans);
        mUpdate = (TextView) findViewById(R.id.book_update_info);
        mStartRead = (TextView) findViewById(R.id.book_start_read);
        mDes = (TextView) findViewById(R.id.book_des);
        mBack = findViewById(R.id.back_activity);
        buttonCollect = findViewById(R.id.book_add_collect);
        buttonShared = findViewById(R.id.book_shared);
        buttonMoney = findViewById(R.id.book_money);
        mGrade = (TextView) findViewById(R.id.book_grade);
        mAdImage = (ImageView) findViewById(R.id.book_ad);
        initIndicator();
    }

    /**
     * 作者专区和评论
     */
    private void initIndicator() {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.indicator);
        tabLayout.addTab(tabLayout.newTab().setText("作者专区"), true);
        tabLayout.addTab(tabLayout.newTab().setText("评论"), false);

        final AuthorFragment authorFragment = new AuthorFragment();
        final CommentFragment commentFragment = new CommentFragment();

        //添加Fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.indicator_container,authorFragment);
        transaction.add(R.id.indicator_container,commentFragment);
        transaction.commit();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                if (position == 0) {
                    System.out.println("切换到了AuthorFragment");
                    transaction.hide(commentFragment);
                    transaction.show(authorFragment);
                }else if (position == 1){
                    System.out.println("切换到了CommentFragment!");
                    transaction.hide(authorFragment);
                    transaction.show(commentFragment);
                }
                transaction.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
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
                    System.out.println("进入条漫Activity"+bookId);
                    entryStripManDetail(bookId);
                }else {
                    System.out.println("进入漫画详情页了！"+bookId);
                    entryBookNewDetails(bookId);
                }
            }
        });
    }

    /**
     * 处理小说信息
     *
     * @param entry BookDetailEntry
     */
    private void handleBookData(BookDetailEntry entry) {
        BookDetailEntry.Data data = entry.getData();
        String imgUrl = data.getThumb();
        String title = data.getTitle();
        String author = "作者：" + data.getAuthor();
        String description = data.getDescription();
        String score = data.getAverageScore();
        float book_score = Float.parseFloat(score);
        book_score = ((int) ((book_score + 0.05F) * 10)) / 10.0f;

        mAuthor.setText(author);
        mBookName.setText(title);
        mDes.setText(description);
        mGrade.setText(String.valueOf(book_score));

        Picasso.with(this).load(imgUrl).into(mBookImg);
    }


    private void setListenerEvent() {
        /*
         * 小说详情页所有的按钮点击事件监听器
         */
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
