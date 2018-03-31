package com.beidou.ybz.accountbook.retrofit;

import android.content.Context;

import com.beidou.ybz.accountbook.mvp.entity.BaseResponse;
import com.beidou.ybz.accountbook.mvp.entity.RequestBody2;
import com.beidou.ybz.accountbook.mvp.entity.SecretKeyResponseModel;
import com.beidou.ybz.accountbook.mvp.entity.SercetKeyOverdueModel;
import com.beidou.ybz.accountbook.mvp.entity.UnencryptedResponseModel;
import com.beidou.ybz.accountbook.util.GsonTools;
import com.beidou.ybz.accountbook.util.LogUtils;
import com.beidou.ybz.accountbook.util.SharePreferenceUtil;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author: xu.yang on 2017/12/11
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module:
 * 参考：http://www.jianshu.com/p/8d1ee61bc2d2
 */
public class TokenInterceptor implements Interceptor {
    private Context mContext;
    public SharePreferenceUtil spUtils;

    public TokenInterceptor(Context context) {
        mContext = context;
        spUtils = new SharePreferenceUtil(mContext, "xinliangbao");
    }

    @Override
    public Response intercept(final Chain chain) {
        Response response = null;
        Request request = chain.request();
        try {
            response = chain.proceed(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
//        LogUtils.loge("TokenInterceptor::response.code=" + response.code());
//        LogUtils.loge("TokenInterceptor::body" + response.body());
            LogUtils.loge("TokenInterceptor::body().string()" + getBodyString(response));

            BaseResponse baseResponse = GsonTools.getObject(getBodyString(response), BaseResponse.class);
            if (baseResponse != null && baseResponse.getMsgType() != null && baseResponse.getMsgType().equals("1")) {

                UnencryptedResponseModel unencryptedResponseModel = GsonTools.getObject(getBodyString(response), UnencryptedResponseModel.class);
                SercetKeyOverdueModel sercetKeyOverdueModel = GsonTools.getObject(unencryptedResponseModel.getMsg(), SercetKeyOverdueModel.class);
                LogUtils.loge("null:::" + (sercetKeyOverdueModel == null));
                LogUtils.loge("111111111111111111111111111::");

//            LogUtils.loge(sercetKeyOverdueModel.getHeader().getCode()+"::"+sercetKeyOverdueModel.getHeader().getDesc());
                if (sercetKeyOverdueModel != null && sercetKeyOverdueModel.getHeader().getCode().equals("0901")) {
                    //秘钥过时，获取秘钥并重新请求
                    LogUtils.loge("aaaaaaaaaaaaaaaaaaaaaaaa::");
                    RequestBody2 requestBody = new RequestBody2();
                    RequestBody2.HeaderBean headerBean = new RequestBody2.HeaderBean();
                    requestBody.setHeader(headerBean);
                    RequestBody2.BodyBean bodyBean = new RequestBody2.BodyBean();
                    requestBody.setBody(bodyBean);
                    Gson gson = new Gson();
                    String json = gson.toJson(requestBody);
                    Logger.e("aaaa:：" + json);
                    Logger.json(json);

                    ApiStores apiStores = AppClient.retrofit(mContext).create(ApiStores.class);//retrofit.create(ApiStores.class);
                    Observable<UnencryptedResponseModel> observable = apiStores.getSecretkey("1", json);
                    observable.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Subscriber<UnencryptedResponseModel>() {
                                @Override
                                public void onCompleted() {
                                    LogUtils.loge("onCompleted()");
                                }

                                @Override
                                public void onError(Throwable e) {
                                    LogUtils.loge("onError()" + e.getMessage());
                                }

                                @Override
                                public void onNext(UnencryptedResponseModel movieEntity) {
                                    LogUtils.loge("onNext()" + movieEntity.getMsg());
                                    SecretKeyResponseModel secretKeyResponseModel = GsonTools.getObject(movieEntity.getMsg(), SecretKeyResponseModel.class);
                                    LogUtils.loge(secretKeyResponseModel.getHeader().getCode());
                                    LogUtils.loge(secretKeyResponseModel.getBody().getSecretKeyId());

                                    String secretKey = secretKeyResponseModel.getBody().getSecretKey();
                                    String secretIv = secretKeyResponseModel.getBody().getSecretIv();
                                    String secretKeyId = secretKeyResponseModel.getBody().getSecretKeyId();
                                    LogUtils.loge("onNext()+secretKeyId::" + secretKeyId);
                                    spUtils.setSecretKey(secretKey);
                                    spUtils.setSecretIv(secretIv);
                                    spUtils.setSecretKeyId(secretKeyId);

                                }
                            });
                    Request newRequest = chain.request()
                            .newBuilder()
                            .header("Cookie", "JSESSIONID=")
                            .build();
                    LogUtils.loge("TokenInterceptor::重新请求");
                    return chain.proceed(newRequest);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * @param response
     * @return
     * @throws IOException
     */
    public String getBodyString(Response response) throws Exception {
        Buffer buffer = null;
        Charset charset = null;
        MediaType contentType = null;
        try {
            ResponseBody responseBody = response.body();
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE);
            buffer = source.buffer();
            charset = Charset.forName("UTF-8");
            contentType = responseBody.contentType();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (contentType != null) {
            contentType.charset(charset);
        }
        return buffer.clone().readString(charset);
    }
}
