package org.sltpaya.cartoon.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import android.widget.TextView;

import org.sltpaya.cartoon.ActivityItem;
import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.adapter.pager.RankItemAdapter;

import java.util.HashMap;

/**
 * Cartoon模块中，排行中分别为：
 * 人气，点击榜，少年榜，少女，耽美
 *
 */
public class CartoonRankActivity extends AppCompatActivity {

    private ActivityItem mRankLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        mRankLabel = getIntent().getExtras().getParcelable("item");
        if (mRankLabel != null) {
            initBar();
            initViews();
        }
    }

    private void initBar() {
        TextView mTitle = (TextView) findViewById(R.id.activity_title);
        mTitle.setText(mRankLabel.getActivityTitle());
        View mBackButton = findViewById(R.id.activity_back);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initViews() {
        String[] titles = {"周榜", "月榜", "日榜"};
        TabLayout mTabLayout = (TabLayout) findViewById(R.id.activity_tabLayout);
        ViewPager mPager = (ViewPager) findViewById(R.id.activity_viewpager);

        mPager.setAdapter(new RankItemAdapter
                (getSupportFragmentManager(), titles, mRankLabel, "cartoon"));

        mTabLayout.setupWithViewPager(mPager);
    }

}
