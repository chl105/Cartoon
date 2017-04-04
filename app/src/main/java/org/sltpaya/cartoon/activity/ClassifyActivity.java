package org.sltpaya.cartoon.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.sltpaya.cartoon.ActivityItem;
import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.adapter.pager.ClassifyPagerAdapter;

public class ClassifyActivity extends BaseActivity {

    private ActivityItem mRankItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify);
        mRankItem = getIntent().getExtras().getParcelable("item");
        if (mRankItem != null) {
            initBar();
            initViews();
        }
    }

    private void initBar() {
        TextView mTitle = (TextView) findViewById(R.id.activity_title);
        mTitle.setText(mRankItem.getActivityTitle());
        View mBackButton = findViewById(R.id.activity_back);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initViews() {
        String[] titles = {"人气", "更新", "完结"};
        TabLayout mTabLayout = (TabLayout) findViewById(R.id.activity_tabLayout);
        ViewPager mPager = (ViewPager) findViewById(R.id.activity_viewpager);

        mPager.setAdapter(
                new ClassifyPagerAdapter(getSupportFragmentManager(), titles, mRankItem));

        mTabLayout.setupWithViewPager(mPager);
    }

}
