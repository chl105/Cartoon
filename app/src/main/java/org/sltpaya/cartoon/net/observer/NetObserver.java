package org.sltpaya.cartoon.net.observer;

import java.util.ArrayList;
import java.util.Map;

/**
 * Author: SLTPAYA
 * Date: 2017/2/20
 */
public class NetObserver implements NetListener {

    private ArrayList<NetListener> listeners = new ArrayList<>();
    private String lastJson;
    private Map<String, String> lastParams;

    @Override
    public void onResponse(String json,Map<String, String> params) {
        lastJson = json;
        lastParams = params;
        for (NetListener listener : listeners) {
            listener.onResponse(json, params);
        }
    }

    @Override
    public void onFailed(Map<String, String> params) {
        for (NetListener listener : listeners) {
            listener.onFailed(params);
        }
    }

    public void addNetListener(NetListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
            if (lastJson != null && lastParams != null) {
                listener.onResponse(lastJson, lastParams);
            }
        }
    }

    public void removeNetListener(NetListener listener) {
        if (listeners.contains(listener)) {
            listeners.remove(listener);
        }
    }

    public void clear() {
        listeners.clear();
        lastJson = null;
        lastParams = null;
    }

}



