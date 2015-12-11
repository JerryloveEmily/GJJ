package com.yj.zzgjj.gjj.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yj.zzgjj.gjj.R;
import com.yj.zzgjj.gjj.util.ResouceUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 办事指南
 */
public class GuideActivity extends Activity {

    /******************** 标题栏 **********************/
    @Bind(R.id.tv_left)
    TextView mTvTitleBarLeft;           // 标题栏左边按钮
    @Bind(R.id.tv_center)
    TextView mTvTitleBarTitle;          // 标题栏中间标题
    @Bind(R.id.tv_right)
    TextView mTvTitleBarSignIn;         // 标题栏右边按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
        initEvent();
    }

    private void initView() {
        ButterKnife.bind(this);
        initTitleBar();
    }

    /**
     * 初始化标题栏
     */
    private void initTitleBar() {
        mTvTitleBarLeft.setText(getString(R.string.back_text));
        ResouceUtils.setResourceCompoundDrawables(this, mTvTitleBarLeft,
                R.drawable.ic_top_back, 0, 0, 0);
        mTvTitleBarLeft.setCompoundDrawablePadding(10);
        mTvTitleBarTitle.setText(getResources().getString(R.string.string_guide));
        mTvTitleBarSignIn.setVisibility(View.GONE); // 隐藏
    }

    /**
     * 初始化事件监听
     */
    private void initEvent(){
        // 点击返回按钮
        mTvTitleBarLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
