package com.yj.zzgjj.gjj.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.yj.zzgjj.gjj.R;
import com.yj.zzgjj.gjj.util.ResouceUtils;
import com.yj.zzgjj.gjj.widget.loadingandretry.LoadingAndRetryManager;
import com.yj.zzgjj.gjj.widget.loadingandretry.OnLoadingAndRetryListener;

/**
 * Activity基类
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSystemBarUI();
        int layoutId = getMainContentViewId();
        if (0 != layoutId){
            setContentView(layoutId);
            initView();
            Object bindLARtryView = bindLoadingAndRetryView();
            if (null != bindLARtryView){
                LoadingAndRetryManager mLoadingAndRetryManager = LoadingAndRetryManager
                        .generate(bindLARtryView, getOnLoadingAndRetryListener());
                setLoadingAndRetryManager(mLoadingAndRetryManager);
                startLoading();
            }
        }
    }

    /**
     * 设置状态栏和导航栏为透明的和沉浸式
     */
    private void setSystemBarUI(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        int color = ResouceUtils.getRescourceColor(this, R.color.colorPrimary);
        if (!tintManager.isStatusBarTintEnabled()) {
            // enable status bar tint
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintColor(color);
        }
        if (!tintManager.isNavBarTintEnabled()) {
            // enable navigation bar tint
            tintManager.setNavigationBarTintEnabled(true);
            tintManager.setNavigationBarTintColor(color);
        }
    }

    /**
     * 启动Activity
     * @param context   当前Activity上下文
     * @param cls       跳转到的Activity
     * @param bundle    携带的数据
     * @param flags     不同界面的调整标识
     */
    public static void startActivity(@NonNull Context context, @NonNull Class<?> cls,
                                     @Nullable Bundle bundle, int flags){
        Intent intent = new Intent(context, cls);
        if (null != bundle){
            intent.putExtra("bundle", bundle);
        }
        if (0 != flags){
            intent.setFlags(flags);
        }
        context.startActivity(intent);
    }

    public static void startActivity(@NonNull Context context, @NonNull Class<?> cls){
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }

    public static void startActivityForResult(@NonNull Activity activity, @NonNull Class<?> cls,
                                              @Nullable Bundle bundle, int flagsOrRequestCode){
        Intent intent = new Intent(activity, cls);
        if (bundle != null){
            intent.putExtra("bundle", bundle);
        }
        if (0 != flagsOrRequestCode){
            intent.setFlags(flagsOrRequestCode);
        }
        activity.startActivityForResult(intent, flagsOrRequestCode);
    }

    protected abstract void initView();
    public void initTitleBar(){}
    public void initEvent(){}

    /**
     * 获取xml布局id
     * @return xml布局id
     */
    @LayoutRes protected abstract int getMainContentViewId();

    /**
     * 绑定加载和重试界面
     * @return  被绑定的视图
     */
    @Nullable protected abstract Object bindLoadingAndRetryView();

    @Nullable protected abstract OnLoadingAndRetryListener getOnLoadingAndRetryListener();

    protected abstract void setLoadingAndRetryManager(LoadingAndRetryManager loadingAndRetryManager);

    protected abstract void startLoading();
}