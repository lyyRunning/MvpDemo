package com.example.azheng.rxjavamvpdemo.util;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.azheng.rxjavamvpdemo.R;

/**
 * @author azheng
 * @date 2018/5/24.
 * GitHub：https://github.com/RookieExaminer
 * Email：wei.azheng@foxmail.com
 * Description：圆形进度条Dialog
 * 页面加载框
 */
public class ProgressDialog {

    private static volatile ProgressDialog instance;

    private MaterialDialog materialDialog;

    private ProgressDialog() {
    }

    /**
     * 单例
     * @return
     */
    public static ProgressDialog getInstance() {
        if (instance == null) {
            synchronized (ProgressDialog.class) {
                if (instance == null) {
                    instance = new ProgressDialog();
                }
            }
        }
        return instance;
    }






    /**
     * 显示弹窗
     * @return
     */
    public void show(Context mContext) {
        materialDialog = new MaterialDialog.Builder(mContext)
//                .title(R.string.progress_dialog_title)
                .content(R.string.progress_please_wait)
                .progress(true, 0)
                .cancelable(false)
                .progressIndeterminateStyle(false)
                .show();
    }

    public void dismiss() {
        if (materialDialog!=null){
            materialDialog.dismiss();
        }

    }
}
