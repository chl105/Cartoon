package org.sltpaya.cartoon.net.cache;

import android.util.SparseArray;
import com.google.gson.Gson;
import org.sltpaya.cartoon.net.entry.Entry;
import org.sltpaya.cartoon.net.entry.detail.BookDetailAdEntry;
import org.sltpaya.cartoon.net.entry.detail.BookDetailEntry;
import org.sltpaya.cartoon.net.entry.detail.DetailSameEntry;
import org.sltpaya.cartoon.net.http.BaseHttp;
import org.sltpaya.cartoon.net.http.CartoonHttp;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: SLTPAYA
 * Date: 2017/3/6
 */
public class BookDetailCache extends NetCache {

    private final int BOOK_INFO_TYPE = 1;
    private final int BOOK_SAME_TYPE = 2;
    private final int AD_TYPE = 3;

    private Gson mGson = new Gson();
    private DataSuccessful dataListener;
    private static BookDetailCache bookDetailCache;
    private SparseArray<Entry> mArrays = new SparseArray<>(5);
    private static String mBookId;

    private int[] mTypes = {
            BOOK_INFO_TYPE,
            BOOK_SAME_TYPE,
            AD_TYPE
    };

    private BookDetailCache(String bookId) {
        mBookId = bookId;
    }

    public static BookDetailCache newInstance(String bookId) {
        if (bookDetailCache == null) {
            synchronized (SquareCache.class) {
                if (bookDetailCache == null) {
                    bookDetailCache = new BookDetailCache(bookId);
                }
            }
        }
        mBookId = bookId;
        return bookDetailCache;
    }

    /**
     * 当网络请求全部成功后，将会回调传入的{@link DataSuccessful}
     *
     * @param listener {@link DataSuccessful}
     */
    public void setDataListener(DataSuccessful listener) {
        this.dataListener = listener;
    }

    /**
     * 获取收获到的缓存
     *
     * @return Entry
     */
    public SparseArray<Entry> getData() {
        if (mArrays.size() == mTypes.length) {
            return mArrays;
        }
        return null;
    }

    /**
     * 通知数据做出了改变，调用传入的对象
     */
    private void notifyData() {
        if (dataListener != null && getData() != null) {
            dataListener.onResponse(mArrays);
        }
    }

    @Override
    public void onResponse(String json, Map<String, String> params) {
        String action = params.get("a");
        switch (action) {
            case "get_book_info":
                BookDetailEntry entry = mGson.fromJson(json, BookDetailEntry.class);
                mArrays.put(BOOK_INFO_TYPE, entry);
                break;
            case "get_same_books":
                DetailSameEntry sameEntry = mGson.fromJson(json, DetailSameEntry.class);
                mArrays.put(BOOK_SAME_TYPE, sameEntry);
                break;
            case "get_main_advertising_position":
                BookDetailAdEntry adEntry = mGson.fromJson(json, BookDetailAdEntry.class);
                mArrays.put(AD_TYPE, adEntry);
                break;
        }
        notifyData();
    }

    @Override
    public void onFailed(Map<String, String> params) {
    }

    @Override
    public void requestNet() {
        mArrays.clear();
        for (int type : mTypes) {
            BaseHttp http = getHttpInstance(getParams(null, type));
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
        params.put("c", "BookDetail");
        params.put("userid", "0");
        params.put("ui", "default");
        params.put("ui_id", "0");
        switch (type) {
            case BOOK_INFO_TYPE:
                params.put("a", "get_book_info");
                params.put("id", mBookId);
                break;
            case BOOK_SAME_TYPE:
                params.put("a", "get_same_books");
                params.put("bid", mBookId);
                break;
            case AD_TYPE:
                params.put("c", "MainRecommend");
                params.put("a", "get_main_advertising_position");
                params.put("type_map", "1");
                params.put("bid", mBookId);
                break;
        }
        return params;
    }

}
