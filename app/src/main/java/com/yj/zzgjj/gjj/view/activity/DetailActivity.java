package com.yj.zzgjj.gjj.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yj.zzgjj.gjj.R;
import com.yj.zzgjj.gjj.util.ResouceUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 详情页
 */
public class DetailActivity extends Activity implements
        SwipeRefreshLayout.OnRefreshListener {

    /********************* 标题栏 **********************/
    @Bind(R.id.tv_left)
    TextView mTvTitleBarLeft;           // 标题栏左边按钮
    @Bind(R.id.tv_center)
    TextView mTvTitleBarTitle;          // 标题栏中间标题
    @Bind(R.id.tv_right)
    TextView mTvTitleBarSignIn;         // 标题栏右边按钮

    @Bind(R.id.srl_refresh)
    SwipeRefreshLayout mRefreshView;    // 刷新数据视图

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();
        initEvent();
    }

    private void initView() {
        ButterKnife.bind(this);
        initTitleBar();
        initRefresh();
    }

    /**
     * 初始化标题栏
     */
    private void initTitleBar() {
        mTvTitleBarLeft.setText(getString(R.string.back_text));
        ResouceUtils.setResourceCompoundDrawables(this, mTvTitleBarLeft,
                R.drawable.ic_top_back, 0, 0, 0);
        mTvTitleBarLeft.setCompoundDrawablePadding(10);
        mTvTitleBarTitle.setText(getResources().getString(R.string.string_detail));
        mTvTitleBarSignIn.setVisibility(View.GONE); // 隐藏
    }

    /**
     * 初始化刷新数据视图
     */
    private void initRefresh() {
        mRefreshView.setColorSchemeColors(
                ResouceUtils.getRescourceColor(this, R.color.colorPrimary),
                ResouceUtils.getRescourceColor(this, R.color.colorPrimaryDark),
                ResouceUtils.getRescourceColor(this, R.color.colorPrimary));
        mRefreshView.setRefreshing(true);
        mRefreshView.setOnRefreshListener(this);
        onRefresh();
    }

    /**
     * 初始化事件监听
     */
    private void initEvent() {
        // 点击返回按钮
        mTvTitleBarLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onRefresh() {
        loadData();
        mRefreshView.setRefreshing(false);
        Toast.makeText(DetailActivity.this, "正在刷新数据", Toast.LENGTH_SHORT).show();
    }

    /**
     * 加载数据
     */
    private void loadData(){

    }
}
