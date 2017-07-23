package org.sltpaya.cartoon.holder.cartoon;

import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.holder.BaseHolder;
import org.sltpaya.cartoon.net.cache.RecommendCache;
import org.sltpaya.cartoon.net.entry.Entry;
import org.sltpaya.cartoon.net.entry.PreviewEntry;

import java.util.List;

import static android.R.attr.data;

/**
 * 动漫首页推荐模块--水平滑动预览View
 * Author: SLTPAYA
 * Date: 2017/2/21
 */
public class PreviewHolder extends BaseHolder {

    /*滑动View的滑动容器*/
    private final ViewGroup mScrollContainer;
    private final LayoutInflater mInflater;


    public PreviewHolder(View itemView) {
        super(itemView);
        mScrollContainer = (ViewGroup) itemView
                .findViewById(R.id.cartoon_scroll_head)
                .findViewById(R.id.cartoon_scroll_ll);
        mInflater = LayoutInflater.from(itemView.getContext());
        initGroupTitle();
    }

    /**
     * 初始化最上方的标题栏
     */
    private void initGroupTitle() {
        TextView groupTitle = (TextView) itemView.findViewById(R.id.title_one_title);
        ImageView mGroupIcon = (ImageView) itemView.findViewById(R.id.title_one_img);
        TextView moreBtn = (TextView) itemView.findViewById(R.id.title_one_more);

        mGroupIcon.setImageResource(R.drawable.icon_rank);
        groupTitle.setText("这本漫画真厉害！一周人气O(∩_∩)O");
        moreBtn.setText("更多");
        moreBtn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);

        moreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(itemView.getContext(), "进入更多", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void updateView() {
        //判断逻辑，如果mContainer里面的子View个数不为0，那么不需要再获取数据并且填充
        if (mScrollContainer.getChildCount() != 0) {
            return;
        }
        SparseArray<Entry> data = RecommendCache.getInstance().getData();
        Entry entryRaw = data.get(-1);
        PreviewEntry entry;
        if (entryRaw instanceof PreviewEntry) {
            entry = (PreviewEntry) entryRaw;
            List<PreviewEntry.DataBeanX.DataBean> list = entry.getDatas();
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    handleView(list.get(i));
                }
            }
        }
    }

    private void handleView(PreviewEntry.DataBeanX.DataBean data) {
        View view = getItem();

        ImageView headView = (ImageView) view.findViewById(R.id.author_head_img);
        ImageView flagView = (ImageView) view.findViewById(R.id.cartoon_flag);
        TextView leavel = (TextView) view.findViewById(R.id.level_sort);
        TextView title = (TextView) view.findViewById(R.id.cartoon_title);
        TextView author = (TextView) view.findViewById(R.id.cartoon_author);

        String authorS = data.getAuthor();
        String titleS = data.getTitle();
        String rank = data.getRankId();
        String imgUrl = data.getThumb3();
        //获取分类关键字
        String keyWord = data.getShowLabel().replace("[", "").replace("]", "");
        int start = keyWord.indexOf("\"");
        int end = keyWord.lastIndexOf("\"");
        keyWord = keyWord.substring(start + 1, end);
//        System.out.println("处理的结果如下："+keyWord);

        Picasso.with(itemView.getContext())
                .load(imgUrl)
                .placeholder(R.drawable.icon_cover_home02)
                .error(R.drawable.icon_cover_home02)
                .config(Bitmap.Config.RGB_565)
                .into(headView);

        Picasso.with(itemView.getContext())
                .load(getFlag(keyWord))
                .error(R.drawable.bg_sanwu)
                .into(flagView);

        author.setText(authorS);
        title.setText(titleS);
        leavel.setText(rank);
        mScrollContainer.addView(view);
    }

    private View getItem() {
        return mInflater.inflate(R.layout.cartoon_scroll_item, mScrollContainer, false);
    }

    /**
     * 获取类型标记
     *
     * @param keyWord 关键字
     * @return DrawableRes
     */
    @DrawableRes
    private int getFlag(String keyWord) {
        int[] resId = {
                R.drawable.bg_xuanyi,
                R.drawable.bg_aojiao,
                R.drawable.bg_baihe,
                R.drawable.bg_bendan,
                R.drawable.bg_cos,
                R.drawable.bg_danmei,
                R.drawable.bg_dashu,
                R.drawable.bg_dongman,
                R.drawable.bg_dongzuo,
                R.drawable.bg_dushe,
                R.drawable.bg_dushi,
                R.drawable.bg_fuhei,
                R.drawable.bg_futa,
                R.drawable.bg_gaoxiao,
                R.drawable.bg_gongkou,
                R.drawable.bg_gufen,
                R.drawable.bg_huihua,
                R.drawable.bg_jinji,
                R.drawable.bg_kehuan,
                R.drawable.bg_kongbu,
                R.drawable.bg_lianai,
                R.drawable.bg_lizhi,
                R.drawable.bg_luchi,
                R.drawable.bg_luoli,
                R.drawable.bg_mohuan,
                R.drawable.bg_nvwang,
                R.drawable.bg_rexue,
                R.drawable.bg_ruanmei,
                R.drawable.bg_sanwu,
                R.drawable.bg_shaonian,
                R.drawable.bg_shaonv,
                R.drawable.bg_shenhuo,
                R.drawable.bg_shouban,
                R.drawable.bg_tianrandai,
                R.drawable.bg_tongren,
                R.drawable.bg_wanjie,
                R.drawable.bg_xiaoyuan,
                R.drawable.bg_xionggui,
                R.drawable.bg_xiuji,
                R.drawable.bg_xuanhuan,
                R.drawable.bg_xuanyi,
                R.drawable.bg_yinv,
                R.drawable.bg_youxi,
                R.drawable.bg_yuanqi,
                R.drawable.bg_yujie,
                R.drawable.bg_zhaiwu,
                R.drawable.bg_zhentai
        };
        String[] names = {
                "悬疑",
                "傲娇",
                "百合",
                "笨蛋",
                "cos",
                "耽美",
                "大叔",
                "动漫",
                "动作",
                "毒舌",
                "都市",
                "腹黑",
                "扶她",
                "搞笑",
                "工口",
                "古风",
                "绘画",
                "竞技",
                "科幻",
                "恐怖",
                "恋爱",
                "励志",
                "路痴",
                "萝莉",
                "魔幻",
                "女王",
                "热血",
                "软妹",
                "三无",
                "少年",
                "少女",
                "生活",
                "手办",
                "天然呆",
                "同人",
                "完结",
                "校园",
                "兄贵",
                "秀吉",
                "玄幻",
                "悬疑",
                "乙女",
                "游戏",
                "元气",
                "御姐",
                "宅舞",
                "正太"
        };

        /*寻找匹配的标记**/
        String tmp;
        for (int i = 0; i < names.length; i++) {
            tmp = names[i];
            if (tmp.equals(keyWord)) {
                return resId[i];
            }
        }
        return R.drawable.bg_sanwu;
    }

}
