package com.example.azheng.rxjavamvpdemo.net;


import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author azheng
 * @date 2018/4/17.
 * GitHub：https://github.com/RookieExaminer
 * email：wei.azheng@foxmail.com
 * description：
 */
public class RetrofitClient {

    private static volatile RetrofitClient instance;
    private APIService apiService;
    private String baseUrl = "http://www.wanandroid.com/";
    private  static volatile OkHttpClient okHttpClient;
    private  static volatile Retrofit retrofitClient;
    private RetrofitClient() {
    }

    public static RetrofitClient getInstance() {
        if (instance == null) {
            synchronized (RetrofitClient.class) {
                if (instance == null) {
                    instance = new RetrofitClient();
                }
            }
        }
        return instance;
    }

    /**
     * 设置Header
     *
     * @return
     */
    private Interceptor getHeaderInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder()
                        //添加Token
                        .header("token", "");
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };

    }

    /**
     * 设置拦截器
     *
     * @return
     */
    private Interceptor getInterceptor() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        //显示日志
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return interceptor;
    }


    /**
     * OKHttp 的单例
     * @return
     */
    private  OkHttpClient getOkHttpClientInstance() {
        if (okHttpClient == null) {
            synchronized (OkHttpClient.class) {
                if (okHttpClient == null) {
                    okHttpClient = new OkHttpClient.Builder()
                            //10秒连接超时
                            .connectTimeout(10, TimeUnit.SECONDS)
                            //10m秒写入超时
                            .writeTimeout(10, TimeUnit.SECONDS)
                            //10秒读取超时
                            .readTimeout(10, TimeUnit.SECONDS)
                            //设置Header
                            .addInterceptor(getHeaderInterceptor())
                            //设置拦截器
                            .addInterceptor(getInterceptor())
                            //.addInterceptor(new HttpHeaderInterceptor())//头部信息统一处理
                            //.addInterceptor(new CommonParamsInterceptor())//公共参数统一处理
                            .build();
                }
            }
        }
        return okHttpClient;
    }


    /**
     * Retrofit 的单例
     * @return
     */
    private  Retrofit getRetrofitInstance() {
        if (retrofitClient == null) {
            synchronized (Retrofit.class) {
                if (retrofitClient == null) {

                    retrofitClient = new Retrofit.Builder()
                            .client(getOkHttpClientInstance())
                            //设置网络请求的Url地址
                            .baseUrl(baseUrl)
                            //设置数据解析器
                            .addConverterFactory(GsonConverterFactory.create())
                            //设置网络请求适配器，使其支持RxJava与RxAndroid
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build();

                }
            }
        }
        return retrofitClient;
    }




    /**
     * OkHttpClient 和 Retrofit全部做成单例
     * @return
     */
    public APIService getApi() {
        //初始化一个client,不然retrofit会自己默认添加一个
//        OkHttpClient client = new OkHttpClient().newBuilder()
//                //设置Header
//                .addInterceptor(getHeaderInterceptor())
//                //设置拦截器
//                .addInterceptor(getInterceptor())
//                .build();

//        Retrofit retrofit = new Retrofit.Builder()
//                .client(client)
//                //设置网络请求的Url地址
//                .baseUrl(baseUrl)
//                //设置数据解析器
//                .addConverterFactory(GsonConverterFactory.create())
//                //设置网络请求适配器，使其支持RxJava与RxAndroid
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build();
        //创建—— 网络请求接口—— 实例
        apiService = getRetrofitInstance().create(APIService.class);
        return apiService;
    }


}
