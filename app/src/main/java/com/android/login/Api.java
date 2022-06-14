package com.android.login;

import java.util.List;
import java.util.Map;

import model.JsonModel;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Api {

    @GET("public/v2/users")
    Call<ResponseBody> getString();
//    Call<List<JsonModel>> getString();

    @GET("public/v2/users")
    Call<List<JsonModel>> getUserModel();


    @Headers("Authorization: Bearer 1bbcb28f7364e4d5915d4f16c39d23e28825260af4f482511b2358f5aafe33db")
    @POST("public/v2/users")
    Call<JsonModel> createPost(@Body RequestBody body);
//    Call<JsonModel> createPost(@Field("name") String name, @Field("email") String email, @Field("gender") String gender, @Field("status") String status);
//    Call<JsonModel> createPost(@HeaderMap String headers, @Body String body);

}