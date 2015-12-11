package com.yj.zzgjj.gjj.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.yj.zzgjj.gjj.R;

/**
 * Activity基类
 * Created by Jerry on 2015/11/13.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSystemBarUI();
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
        if (!tintManager.isStatusBarTintEnabled()) {
            // enable status bar tint
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintColor(getResources().getColor(R.color.colorPrimary, getTheme()));
        }
        if (!tintManager.isNavBarTintEnabled()) {
            // enable navigation bar tint
            tintManager.setNavigationBarTintEnabled(true);
            tintManager.setNavigationBarTintColor(getResources().getColor(R.color.colorPrimary, getTheme()));
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

    protected void initView(){}
    protected void initTitleBar(){}
    protected void initEvent(){}
}