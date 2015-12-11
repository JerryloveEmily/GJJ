package com.yj.zzgjj.gjj.app;

import android.app.Application;

import com.yj.zzgjj.gjj.R;
import com.yj.zzgjj.gjj.widget.loadingandretry.LoadingAndRetryManager;

/**
 * Application类
 * Created by Administrator on 2015/12/11.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化加载和重试界面布局
        initLoadAndRetryLayoutId();
    }

    /**
     * 初始化加载和重试界面布局
     */
    private void initLoadAndRetryLayoutId(){
        LoadingAndRetryManager.BASE_LOADING_LAYOUT_ID = R.layout.base_loading;
        LoadingAndRetryManager.BASE_RETRY_LAYOUT_ID = R.layout.base_retry;
        LoadingAndRetryManager.BASE_EMPTY_LAYOUT_ID = R.layout.base_empty;
    }
}
