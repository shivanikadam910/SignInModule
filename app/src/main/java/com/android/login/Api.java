package com.android.login;

import java.util.List;

import model.JsonModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;

public interface Api {

    @GET("public/v2/users")
    Call<ResponseBody> getString();
//    Call<List<JsonModel>> getString();

    @GET("public/v2/users")
    Call<List<JsonModel>> getUserModel();
}