package com.example.azheng.rxjavamvpdemo.contract;

import com.example.azheng.rxjavamvpdemo.base.BaseView;
import com.example.azheng.rxjavamvpdemo.bean.BaseObjectBean;
import com.example.azheng.rxjavamvpdemo.bean.LoginBean;

import io.reactivex.Flowable;


/**
 *
 * 在Contract类中定义3 个接口，分别是 Model，View，Presenter。
 * 在接口内部定义方法，方便查看三者之间的关系，方法，使用起来更加简洁。
 */
public interface MainContract {
    /**
     * 这个是 MVP 中的 M
     */
    interface Model {
        Flowable<BaseObjectBean<LoginBean>> login(String username, String password);
    }

    /**
     * 这个是 MVP 中的 V
     */
    interface View extends BaseView {
        @Override
        void showLoading();

        @Override
        void hideLoading();

        @Override
        void onError(Throwable throwable);

        void onSuccess(BaseObjectBean<LoginBean> bean);
    }


    /**
     * 这个是 MVP 中的 P
     */
    interface Presenter {
        /**
         * 登陆
         *
         * @param username
         * @param password
         */
        void login(String username, String password);
    }
}
