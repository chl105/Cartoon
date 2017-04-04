package org.sltpaya.cartoon.net.cache;

import android.util.Log;
import android.util.SparseArray;

import com.google.gson.Gson;

import org.sltpaya.cartoon.net.entry.BannerEntry;
import org.sltpaya.cartoon.net.entry.Entry;
import org.sltpaya.cartoon.net.entry.novel.NovelAdEntry;
import org.sltpaya.cartoon.net.entry.novel.HorizontalOneEntry;
import org.sltpaya.cartoon.net.entry.novel.UpdateEntry;
import org.sltpaya.cartoon.net.http.BaseHttp;
import org.sltpaya.cartoon.net.http.NovelHttp;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: SLTPAYA
 * Date: 2017/2/25
 */
public class NovelCache extends NetCache {

    private static NovelCache novelCache;
    private DataSuccessful dataListener;
    private int callCount = 0;//回调次数
    private Gson parseGson = new Gson();
    private SparseArray<Entry> entrys = new SparseArray<>();

    /**
     * 获取单例对象
     *
     * @return NovelCache
     */
    public static NovelCache getInstance() {
        if (novelCache == null) {
            synchronized (NovelCache.class) {
                if (novelCache == null) {
                    novelCache = new NovelCache();
                }
            }
        }
        return novelCache;
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
        if (entrys.size() == 9) {
            return entrys;
        }
        return null;
    }

    /**
     * 通知数据做出了改变，调用传入的对象
     */
    public void notifyData() {
        if (dataListener != null && getData() != null) {
            dataListener.onResponse(entrys);
        }
    }

    @Override
    public void requestNet() {
        int[] adTypes = {0, 3};
        int[] recommendTypes = {1, 18, 20, 21, 22, 23, 27};
        requestAdvertise(adTypes, ADVERTISE);
        requestRecommend(recommendTypes, RECOMMEND);
    }

    @Override
    protected BaseHttp getHttpInstance(Map<String, String> params) {
        return new NovelHttp(params);
    }

    @Override
    protected HashMap<String, String> getParams(String action, int type) {
        HashMap<String, String> params = new HashMap<>();
        String uiFlag = "FragmentNovlRecommend";
        if (type == 0) {
            uiFlag = "default";
        }
        params.put("c", "MainRecommend");
        params.put("a", action);
        params.put("type", String.valueOf(type));
        params.put("userid", "0");
        params.put("ui", uiFlag);
        params.put("start", "0");
        params.put("ui_id", "0");
        return params;
    }

    @Override
    public void onResponse(String json, Map<String, String> params) {
        //-----调试信息-------
        int type = Integer.parseInt(params.get("type"));
        callCount++;
        logger("回调了：" + callCount + "次. 本次type为：" + type);
        //-------------------
        switch (type) {
            case 0:
                logger("Novel banner生成！");
                BannerEntry entry = parseGson.fromJson(json, BannerEntry.class);
                entrys.put(0, entry);
                break;
            case 18:
                logger("Novel 刚刚更新列表生成！");
                UpdateEntry updateEntry = parseGson.fromJson(json, UpdateEntry.class);
                entrys.put(18, updateEntry);
            case 3:
                logger("Novel adEntry生成！");
                //TODO:错误修复
//                logger("Json:"+json);
//                NovelAdEntry adEntry = parseGson.fromJson(json, NovelAdEntry.class);
                entrys.put(3, null);
                break;
            default:
                logger("Novel HorizontalOneEntry生成！");
                HorizontalOneEntry oneEntry = parseGson.fromJson(json, HorizontalOneEntry.class);
                entrys.put(type, oneEntry);
                break;
        }
        notifyData();
    }

    @Override
    public void onFailed(Map<String, String> params) {
        logger("网络连接失败了Novel!");
    }

    private void logger(String des) {
        Log.d("NovelCache", des);
    }

}
