package vn.com.winta.quan.pushnotifyfb.API;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by QWAN on 17/03/2017.
 */

public interface APIServiceImp {
    String URL="http://192.168.0.105:3000";
    Retrofit retrofit= new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
    @FormUrlEncoded
    @POST("token")
    Call<Void> insertToken(@Field("token") String token,@Field("name") String name);
}
