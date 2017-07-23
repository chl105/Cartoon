package org.sltpaya.cartoon.net.cache;

import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;

import com.google.gson.Gson;

import org.sltpaya.cartoon.net.entry.AdEntry;
import org.sltpaya.cartoon.net.entry.BannerEntry;
import org.sltpaya.cartoon.net.entry.Entry;
import org.sltpaya.cartoon.net.entry.PreviewEntry;
import org.sltpaya.cartoon.net.entry.TypeOneEntry;
import org.sltpaya.cartoon.net.entry.TypeTwoEntry;
import org.sltpaya.cartoon.net.http.BaseHttp;
import org.sltpaya.cartoon.net.http.CartoonHttp;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import static android.R.attr.type;

/**
 * Author: SLTPAYA
 * Date: 2017/2/21
 */
public class RecommendCache extends NetCache {

    private static RecommendCache recommendCache;
    private static final int DEFALUT_SIZE = 10;
    private DataSuccessful dataListener;
    private Gson parseGson = new Gson();
    private int callCount = 0;//回调次数
    private SparseArray<Entry> entrys = new SparseArray<>();

    private RecommendCache() {
    }

    public static RecommendCache getInstance() {
        if (recommendCache == null) {
            synchronized (RecommendCache.class) {
                if (recommendCache == null) {
                    recommendCache = new RecommendCache();
                }
            }
        }
        return recommendCache;
    }

    /**
     * 当网络请求全部成功后，将会回调传入的{@link DataSuccessful}
     *
     * @param listener {@link DataSuccessful}
     */
    public void setDataListener(DataSuccessful listener) {
        this.dataListener = listener;
        notifyData();
    }

    public SparseArray<Entry> getData() {
        if (entrys.size() == DEFALUT_SIZE) {
            return entrys;
        }
        return null;
    }

    public void notifyData() {
        if (dataListener != null && getData() != null) {
            dataListener.onResponse(entrys);
        }
    }

    /**
     * 发起网络请求
     * {@link CartoonHttp}
     */
    @Override
    public void requestNet() {
        int[] adTypes = {0, 3};
        int[] recommendTypes = {1, 20, 18, 21, 28, 23, 27};
        requestAdvertise(adTypes, ADVERTISE);
        requestRecommend(recommendTypes, RECOMMEND);
        requestPreview();
    }

    /**
     * 请求推荐模块下的水平滑动展示数据
     *
     */
    private void requestPreview() {
        HashMap<String, String> params = new HashMap<>();
        params.put("c", "MainRank");
        params.put("a", "get_label_rank");
        params.put("label", "0");
        params.put("get_type", "0");
        params.put("start", "0");
        params.put("ui", "0");
        params.put("ui_id", "0");
        params.put("userid", "0");
        params.put("home", "1");
        BaseHttp http = getHttpInstance(params);
        http.request();
        http.getObserver().addNetListener(this);
    }

    @Override
    protected BaseHttp getHttpInstance(Map<String, String> params) {
        return new CartoonHttp(params);
    }

    @Override
    protected HashMap<String, String> getParams(String action, int type) {
        HashMap<String, String> params = new HashMap<>();
        params.put("c", "MainRecommend");
        params.put("a", action);
        params.put("type", String.valueOf(type));
        params.put("userid", "0");
        params.put("ui", "default");
        params.put("start", "0");
        params.put("ui_id", "0");
        return params;
    }


    @Override
    public void onResponse(String json, Map<String, String> params) {
        String typeValue = params.get("type");
        System.out.println(typeValue+"这是标志");
        if (typeValue != null && !TextUtils.isEmpty(typeValue)) {
            try {
                System.out.println("进来 了i");
                int type = Integer.parseInt(typeValue);
                switch (type) {
                    case 0:
                        logger("banner生成！");
                        BannerEntry entry = parseGson.fromJson(json, BannerEntry.class);
                        entrys.put(0, entry);
                        break;
                    case 1:
                        logger("TypeOneEntry生成！");
                        TypeOneEntry oneEntry = parseGson.fromJson(json, TypeOneEntry.class);
                        entrys.put(1, oneEntry);
                        break;
                    case 3:
                        logger("adEntry生成！");
                        AdEntry adEntry = parseGson.fromJson(json, AdEntry.class);
                        entrys.put(3, adEntry);
                        break;
                    default:
                        logger("TypeTwoEntry生成！");
                        TypeTwoEntry twoEntry = parseGson.fromJson(json, TypeTwoEntry.class);
                        entrys.put(type, twoEntry);
                }
            } catch (NumberFormatException e) {
               e.printStackTrace();
            }
        }

        String previewValue = params.get("a");
        if (previewValue != null && !TextUtils.isEmpty(previewValue)
                && "get_label_rank".equals(previewValue)) {
            System.out.println("最新的排行数据生成了！");
            PreviewEntry previewEntry = parseGson.fromJson(json, PreviewEntry.class);
            entrys.put(-1, previewEntry);
        }
        callCount++;
        notifyData();
    }

    @Override
    public void onFailed(Map<String, String> params) {
//        String action = params.get("a");
//        int type = Integer.parseInt(params.get("type"));
//        handlerError(action, type);
//        logger("网络失败！,正在重试！");
    }

    private void logger(String des) {
        Log.d("RecommendCache", des);
    }


}
