package org.sltpaya.cartoon.net.observer;

import java.util.Map;

/**
 * Author: SLTPAYA
 * Date: 2017/2/20
 */
public interface NetListener {

    void onResponse(String json, Map<String, String> params);

    void onFailed(Map<String, String> params);

}
