package org.sltpaya.cartoon.holder;

import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.adapter.BannerAdapter;
import org.sltpaya.cartoon.net.cache.RecommendCache;
import org.sltpaya.cartoon.net.entry.BannerEntry;
import org.sltpaya.cartoon.net.entry.Entry;

import java.util.List;

import static org.sltpaya.tool.Utils.runOnUiThread;

/**
 * Author: SLTPAYA
 * Date: 2017/2/21
 */
public class BannerHolder extends BaseHolder {

    private View selectedDot;
    private ViewGroup dotSet;
    protected ViewPager mBanner;
    private Thread mTimingThread;

    private int dotWidth;
    private int dotRightMargin;
    protected int pagerCount;

    private boolean isScrolling = false;
    private boolean allowScroll = true;

    public BannerHolder(View itemView) {
        super(itemView);
        initViews();
    }

    private void initViews() {
        mBanner = (ViewPager) itemView.findViewById(R.id.fragment_pager);
        selectedDot = itemView.findViewById(R.id.banner_select_dot);
        dotSet = (ViewGroup) itemView.findViewById(R.id.dot_set);
        selectedDot.setVisibility(View.GONE);
        bindBannerEvent();
    }

    @Override
    public void updateView() {
        RecommendCache cache = RecommendCache.newInstance();
        SparseArray<Entry> data = cache.getData();
        BannerEntry entry = (BannerEntry) data.get(0);
        setBannerData(entry);
        createDotView();
    }

    public void stopScroll() {
        if (mTimingThread != null) {
            allowScroll = false;
        }
    }

    /**
     * 创建Banner下方所有固定小白点
     */
    protected void createDotView() {
        dotSet.removeAllViews();//防止多次调用，添加多余的白点
        for (int i = 0; i < pagerCount; i++) {
            View normalDot = LayoutInflater.from(itemView.getContext())
                    .inflate(R.layout.dot_layout, dotSet, false);
            dotSet.addView(normalDot);
            LinearLayout.LayoutParams params
                    = (LinearLayout.LayoutParams) normalDot.getLayoutParams();
            dotWidth = params.width;
            dotRightMargin = params.rightMargin;
        }
        selectedDot.setVisibility(View.VISIBLE);
    }


    /**
     * <p>获取到Entry后设置Banner数据，为其设置适配器</p>
     * <p>获取Banner需要展示的总数量
     * 设置StartIndex</p>
     */
    protected void setBannerData(BannerEntry entry) {
        if (entry.getData() != null) {
            pagerCount = entry.getData().size();
            int pagerStartIndex = 10000 * pagerCount;
            List<BannerEntry.Datum> list = entry.getData();
            mBanner.setAdapter(new BannerAdapter(list));
            mBanner.setCurrentItem(pagerStartIndex, false);
            timingRoll();
        }
    }

    /**
     * 轮播图自动滑动
     */
    protected void timingRoll() {
        mTimingThread = new Thread() {
            @Override
            public void run() {
                while (!isScrolling && allowScroll) {
                    SystemClock.sleep(5000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            int index = mBanner.getCurrentItem() + 1;
                            System.out.println("更改了一次！" + index);
                            mBanner.setCurrentItem(index);
                        }
                    });

                }
            }
        };
        mTimingThread.start();
    }

    /**
     * 为Banner绑定事件监听，在其中处理选中指示点的移动
     * <p>原理为根据{@link ViewPager}滑动的百分值，动态设置选中点的Margin值</p>
     *
     * @see #mBanner
     */
    private void bindBannerEvent() {
        mBanner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
                RelativeLayout.LayoutParams params
                        = (RelativeLayout.LayoutParams) selectedDot.getLayoutParams();
                position %= pagerCount;
                int max = (dotWidth + dotRightMargin) * (pagerCount - 1);
                int tmp = (int) ((dotWidth + dotRightMargin) * (position + positionOffset) + 0.5);
                if (tmp > max) tmp = 0;
                params.leftMargin = tmp;
                selectedDot.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_DRAGGING) {
                    isScrolling = true;
                } else if (state == ViewPager.SCROLL_STATE_SETTLING) {
                    isScrolling = false;
                }
            }
        });
    }

}
