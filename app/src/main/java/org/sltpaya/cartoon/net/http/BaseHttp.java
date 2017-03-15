package org.sltpaya.cartoon.net.http;

import org.sltpaya.cartoon.net.observer.NetObserver;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Author: SLTPAYA
 * Date: 2017/2/20
 */
 public abstract class BaseHttp {

    protected Map<String, String> params;
    private NetObserver observer = new NetObserver();

    BaseHttp(Map<String, String> params) {
        this.params = params;
    }

    public void request() {
        final Call<ResponseBody> request = createServer();
        request.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String json = null;
                if (response.body() == null) {
                    request();
                    return;
                }
                try {
                    json = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                observer.onResponse(json, params);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                observer.onFailed(params);
            }
        });
    }

    protected abstract Call<ResponseBody> createServer();

    public NetObserver getObserver() {
        return observer;
    }

}
