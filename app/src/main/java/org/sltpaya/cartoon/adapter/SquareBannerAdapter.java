package org.sltpaya.cartoon.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.net.entry.square.BannerEntry;

import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/3/3
 */
public class SquareBannerAdapter extends PagerAdapter {

    private List<BannerEntry.Datum> list;

    public SquareBannerAdapter(List<BannerEntry.Datum> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position %= list.size();

        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        View item = inflater.inflate(R.layout.banner_content, container, false);
        ImageView img = (ImageView) item.findViewById(R.id.banner_img);

        String imgUrl = list.get(position).getThumb();
        Picasso.with(container.getContext()).load(imgUrl)
                .error(R.drawable.icon_cover_home03).into(img);

        container.addView(item);
        return item;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }

}
