package com.rocky.newringtones.http.interceptor;

import android.util.Log;

import com.rocky.newringtones.R;
import com.rocky.newringtones.http.model.PublicRepository;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RetrofitInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request orgRequest = chain.request();


        RequestBody body = orgRequest.body();
        //收集请求参数，方便调试
        StringBuilder paramsBuilder = new StringBuilder();

        if (body != null) {

            RequestBody newBody = null;

            if (body instanceof FormBody) {
                newBody = addParamsToFormBody((FormBody) body, paramsBuilder);
            } else if (body instanceof MultipartBody) {
                newBody = addParamsToMultipartBody((MultipartBody) body, paramsBuilder);
            }


            if (null != newBody) {
                //打印参数
                Log.e("requestParames", "intercept: "+paramsBuilder.toString());

                Request newRequest = orgRequest.newBuilder()
                        .url(orgRequest.url())
                        .method(orgRequest.method(), newBody)
                        .build();

                return chain.proceed(newRequest);
            }


        }

        return chain.proceed(orgRequest);
    }

    /**
     * 为MultipartBody类型请求体添加参数
     *
     * @param body
     * @param paramsBuilder
     * @return
     */
    private MultipartBody addParamsToMultipartBody(MultipartBody body, StringBuilder paramsBuilder) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);

        //添加appcode

        builder.addFormDataPart("title", "title");


        paramsBuilder.append("title=" + "title");
        PublicRepository intance = PublicRepository.getIntance();
        if (null!=intance){
            paramsBuilder.append("&");
            paramsBuilder.append(intance.getUid());
            paramsBuilder.append("&");
            paramsBuilder.append(intance.getSign());
            paramsBuilder.append("&");
            paramsBuilder.append(intance.getTooken());
            paramsBuilder.append("&");
            paramsBuilder.append(intance.getUrl());
        }
        //添加原请求体
        for (int i = 0; i < body.size(); i++) {
            builder.addPart(body.part(i));
        }

        return builder.build();
    }
    /**
     * 为FormBody类型请求体添加参数
     *
     * @param body
     * @param paramsBuilder
     * @return
     */
    private FormBody addParamsToFormBody(FormBody body, StringBuilder paramsBuilder) {
        FormBody.Builder builder = new FormBody.Builder();
        paramsBuilder.append("title=" + "title");
        PublicRepository intance = PublicRepository.getIntance();
        if (null!=intance){
            paramsBuilder.append("&");
            paramsBuilder.append(intance.getUid());
            paramsBuilder.append("&");
            paramsBuilder.append(intance.getSign());
            paramsBuilder.append("&");
            paramsBuilder.append(intance.getTooken());
            paramsBuilder.append("&");
            paramsBuilder.append(intance.getUrl());
        }

        //添加原请求体
        for (int i = 0; i < body.size(); i++) {
            builder.addEncoded(body.encodedName(i), body.encodedValue(i));
            paramsBuilder.append("&");
            paramsBuilder.append(body.name(i));
            paramsBuilder.append("=");
            paramsBuilder.append(body.value(i));
        }

        return builder.build();
    }
}
