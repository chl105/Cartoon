package org.sltpaya.cartoon.net.cache;

import android.util.SparseArray;

import org.sltpaya.cartoon.net.entry.Entry;
import org.sltpaya.cartoon.net.http.BaseHttp;
import org.sltpaya.cartoon.net.http.CartoonHttp;
import org.sltpaya.cartoon.net.observer.NetListener;
import java.util.HashMap;
import java.util.Map;


/**
 * Author: SLTPAYA
 * Date: 2017/2/25
 */
public abstract class NetCache implements NetListener {

    protected final String ADVERTISE = "get_main_advertise";
    protected final String RECOMMEND = "get_main_recommend";

    /**
     * 发起网络请求
     * {@link CartoonHttp}
     */
    public abstract void requestNet();

    protected abstract BaseHttp getHttpInstance(Map<String, String> params);

    protected void requestAdvertise(int[] types, String flag) {
        for (int type : types) {
            HashMap<String, String> params = getParams(flag, type);
            BaseHttp http = getHttpInstance(params);
            http.request();
            http.getObserver().addNetListener(this);
        }
    }

    protected void requestRecommend(int[] types, String flag) {
        for (int type : types) {
            HashMap<String, String> params = getParams(flag, type);
            BaseHttp http = getHttpInstance(params);
            http.request();
            http.getObserver().addNetListener(this);
        }
    }

//    /**
//     * 供子类重写方法
//     */
//    protected void requestOther() {
//
//    }

    protected abstract HashMap<String, String> getParams(String action, int type);

    /**
     * 网络访问错误时
     */
    protected void handlerError(String action, int type) {
        HashMap<String, String> params = null;
        if (ADVERTISE.equals(action)) {
            params = getParams(ADVERTISE, type);
        }
        if (RECOMMEND.equals(action)) {
            params = getParams(ADVERTISE, type);
        }
        if (params != null) {
            BaseHttp http = getHttpInstance(params);
            http.request();
            http.getObserver().addNetListener(this);
        }
    }

    public interface DataSuccessful {

        void onResponse(SparseArray<Entry> data);

    }

}
