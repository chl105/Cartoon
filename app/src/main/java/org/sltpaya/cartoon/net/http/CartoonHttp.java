package org.sltpaya.cartoon.net.http;

import org.sltpaya.cartoon.consts.Consts;

import java.util.Map;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.QueryMap;


/**
 * Author: SLTPAYA
 * Date: 2017/2/20
 */
public class CartoonHttp extends BaseHttp {

    public CartoonHttp(Map<String, String> params) {
        super(params);
    }

    @Override
    protected Call<ResponseBody> createServer() {
        MainRecommendServer server = new Retrofit.Builder()
                .baseUrl(Consts.CARTOON_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MainRecommendServer.class);
        return server.request(params);
    }

    interface MainRecommendServer {

        @Headers("User-Agent: kidstone.cn/2.7.2/30/Android/4.4.4/MI-4C/2e59c41af4797b51/YingYongBao")
        @GET("dacu_app/app/")
        Call<ResponseBody> request(@QueryMap Map<String, String> params);

    }

}
