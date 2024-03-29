package com.example.azheng.rxjavamvpdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * @author azheng
 * @date 2018/4/24.
 * GitHub：https://github.com/RookieExaminer
 * Email：wei.azheng@foxmail.com
 * Description：
 */
public abstract class BaseMvpFragment<T extends BasePresenter>  extends BaseFragment implements BaseView{

    protected T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresent();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroyView();
    }

    /**
     * 子类去实现
     * @return
     */
    public abstract T  createPresent();
}
