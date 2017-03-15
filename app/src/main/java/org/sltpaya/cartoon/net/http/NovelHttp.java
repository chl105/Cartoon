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
 * Date: 2017/2/25
 */
public class NovelHttp extends BaseHttp {

    private boolean mFlag = false;

    public NovelHttp(Map<String, String> params) {
        super(params);
    }

    public void setFlag(boolean flag) {
        mFlag = flag;
    }

    @Override
    protected Call<ResponseBody> createServer() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Consts.NOVEL_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        if (!mFlag) {
            NovelServer novelServer = retrofit.create(NovelServer.class);
            return novelServer.request(params);
        }
        UpdateServer updateServer = retrofit.create(UpdateServer.class);
        return updateServer.request(params);
    }

    interface NovelServer {

        @Headers("User-Agent: kidstone.cn")
        @GET("app/")
        Call<ResponseBody> request(@QueryMap Map<String, String> params);

    }

    interface UpdateServer {

        @Headers("User-Agent: kidstone.cn/2.7.2/30/Android/4.4.4/MI-4C/2e59c41af4797b51/YingYongBao")
        @GET("index.php")
        Call<ResponseBody> request(@QueryMap Map<String, String> params);

    }

}
