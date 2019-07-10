package com.rocky.newringtones.http.callback;

import com.rocky.newringtones.base.basemvp.model.JsonResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class RequestCallBack<T> implements Callback<JsonResult<T>> {
    @Override
    public void onResponse(Call<JsonResult<T>> call, Response<JsonResult<T>> response) {

        JsonResult<T> body = response.body();
        if (null!=body){
            if (response.isSuccessful()&&body.getCode()==200){
                onSuccess(body.getData());
            }else{
                onFailed("服务器出错");
            }
        }
    }

    @Override
    public void onFailure(Call<JsonResult<T>> call, Throwable t) {
            onFailed(t.getMessage());
    }

    public abstract void onSuccess(T response);

    protected abstract void onFailed(String msg);
}
