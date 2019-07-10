package com.rocky.newringtones.http.retrofit;

import com.rocky.newringtones.base.basemvp.model.JsonResult;
import com.rocky.newringtones.home.model.LoginJsonBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

interface ApiCommand {
    String BASE_API="http://10.241.80.75:8080/WeCharCost/";
    @POST("login")
    @FormUrlEncoded
    Call<JsonResult<LoginJsonBean>> login(@Field("name") String name, @Field("pwd") String pwd);
}
