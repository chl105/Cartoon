package org.sltpaya.cartoon.net.cache;

import android.util.SparseArray;
import com.google.gson.Gson;
import org.sltpaya.cartoon.net.entry.Entry;
import org.sltpaya.cartoon.net.entry.square.BannerEntry;
import org.sltpaya.cartoon.net.entry.square.FollowerEntry;
import org.sltpaya.cartoon.net.entry.square.HotEntry;
import org.sltpaya.cartoon.net.entry.square.NormalEntry;
import org.sltpaya.cartoon.net.http.BaseHttp;
import org.sltpaya.cartoon.net.http.CartoonHttp;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: SLTPAYA
 * Date: 2017/3/3
 */
public class SquareCache extends NetCache {

    private final String BANNER_TYPE = "get_plaza_advertise";
    private final String HOT_TYPE = "get_plaza_theme";
    private final String FOLLOW_TYPE = "get_plaza_user_recommend";
    private final String NORMAL_TYPE = "get_plaza_host_works";

    private static SparseArray<Entry> mEntrys = new SparseArray<>();
    private DataSuccessful dataListener;
    private static SquareCache squareCache;
    private Gson gson = new Gson();

    private SquareCache(){}

    public static SquareCache newInstance() {
        if (squareCache == null) {
            synchronized (SquareCache.class) {
                if (squareCache == null) {
                    squareCache = new SquareCache();
                }
            }
        }
        return squareCache;
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

    /**
     * 获取收获到的缓存
     *
     * @return Entry
     */
    public SparseArray<Entry> getData() {
        if (mEntrys.size() == 4) {
            return mEntrys;
        }
        return null;
    }

    /**
     * 通知数据做出了改变，调用传入的对象
     */
    public void notifyData() {
        if (dataListener != null && getData() != null) {
            dataListener.onResponse(mEntrys);
        }
    }


    @Override
    public void onResponse(String json, Map<String, String> params) {
        String action = params.get("a");
//        switch (action) {
//            case BANNER_TYPE:
//                BannerEntry bannerEntry = gson.fromJson(json, BannerEntry.class);
//                mEntrys.put(0, bannerEntry);
//                break;
//            case HOT_TYPE:
//                System.out.println(json);
//                HotEntry entry = gson.fromJson(json, HotEntry.class);
//                mEntrys.put(1, entry);
//                break;
//            case FOLLOW_TYPE:
//                FollowerEntry fEntry = gson.fromJson(json, FollowerEntry.class);
//                mEntrys.put(2, fEntry);
//                break;
//            default:
//                NormalEntry normalEntry = gson.fromJson(json, NormalEntry.class);
//                mEntrys.put(3, normalEntry);
//        }
//        notifyData();
    }

    @Override
    public void onFailed(Map<String, String> params) {

    }

    @Override
    public void requestNet() {
        String[] types = {
                BANNER_TYPE,
                HOT_TYPE,
                FOLLOW_TYPE,
                NORMAL_TYPE
        };
        for (String type : types) {
            BaseHttp http = getHttpInstance(getParams(type, 0));
            http.request();
            http.getObserver().addNetListener(this);
        }
    }

    @Override
    protected BaseHttp getHttpInstance(Map<String, String> params) {
        return new CartoonHttp(params);
    }


    @Override
    protected HashMap<String, String> getParams(String action, int type) {
        HashMap<String, String> params = new HashMap<>();
        params.put("c", "Plaza/EditorPlaza");
        params.put("a", action);
        params.put("userid", "0");
        params.put("ui", "default");
        params.put("ui_id", "0");
        switch (action) {
            case BANNER_TYPE:
                params.put("ui_id","0");
                break;
            case HOT_TYPE:
                params.put("home","1");
                params.put("start", "0");
                break;
            case FOLLOW_TYPE:
                params.put("home","1");
                break;
            default:
                params.put("start", "0");
                params.put("ui", "0");
        }
        return params;
    }

}
