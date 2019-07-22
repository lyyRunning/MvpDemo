package com.example.azheng.rxjavamvpdemo.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by luo on 2019/7/22.
 */

public class MyApplication extends Application {
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }


    /**
     * 提供全局的上下文
     * @return
     */
     public static Context getInstance() {
        return mContext;
    }

}
