package org.sltpaya.cartoon.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.sltpaya.cartoon.ActivityItem;
import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.adapter.pager.RankItemAdapter;

/**
 * NovelRankActivity，是Novel模块中排行Activity
 */
public class NovelRankActivity extends AppCompatActivity {

    private ActivityItem mActivityItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novel_rank);
        mActivityItem = getIntent().getExtras().getParcelable("item");
        inflateBar();
        initViews();
    }

    private void inflateBar() {
        TextView mTitle = (TextView) findViewById(R.id.activity_title);
        mTitle.setText(mActivityItem.getActivityTitle());
        View mBackButton = findViewById(R.id.activity_back);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initViews() {
        String[] titles = {"周榜", "月榜", "总榜"};
        TabLayout mTabLayout = (TabLayout) findViewById(R.id.activity_tabLayout);
        ViewPager mPager = (ViewPager) findViewById(R.id.activity_viewpager);

        mPager.setAdapter(new RankItemAdapter
                (getSupportFragmentManager(), titles, mActivityItem, "novel"));

        mTabLayout.setupWithViewPager(mPager);
    }


}
