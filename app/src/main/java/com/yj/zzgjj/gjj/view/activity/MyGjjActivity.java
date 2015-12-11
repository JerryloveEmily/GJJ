package com.yj.zzgjj.gjj.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yj.zzgjj.gjj.R;
import com.yj.zzgjj.gjj.util.ResouceUtils;
import com.yj.zzgjj.gjj.widget.loadingandretry.LoadingAndRetryManager;
import com.yj.zzgjj.gjj.widget.loadingandretry.OnLoadingAndRetryListener;
import com.yj.zzgjj.gjj.widget.recyclerview.adapter.BaseRcvAdapterHelper;
import com.yj.zzgjj.gjj.widget.recyclerview.adapter.BaseRcvQuickAdapter;
import com.yj.zzgjj.gjj.widget.recyclerview.adapter.QuickRcvAdapter;
import com.yj.zzgjj.gjj.widget.recyclerview.extra.EndlessRecyclerOnScrollListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 我的公积金
 */
public class MyGjjActivity extends BaseActivity implements
        SwipeRefreshLayout.OnRefreshListener {

    /********************
     * 标题栏
     **********************/
    @Bind(R.id.tv_left)
    TextView mTvTitleBarLeft;           // 标题栏左边按钮
    @Bind(R.id.tv_center)
    TextView mTvTitleBarTitle;          // 标题栏中间标题
    @Bind(R.id.tv_right)
    TextView mTvTitleBarSignIn;         // 标题栏右边按钮

    @Bind(R.id.srl_refresh)
    SwipeRefreshLayout mRefreshView;    // 刷新数据视图
    @Bind(R.id.rcv_gjj)
    RecyclerView mGjjListView;          // 公积金列表

    private QuickRcvAdapter<String> mGjjAdapter; // 公积金列表适配器

    private LoadingAndRetryManager mLoadingAndRetryManager;  // 加载和重试加载管理器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initEvent();
    }

    @Override
    protected int getMainContentViewId() {
        return R.layout.activity_my_gjj;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        initTitleBar();
        initGJJListView();
        initRefresh();
    }

    @Override
    protected Object bindLoadingAndRetryView() {
        return mRefreshView;
    }

    @Override
    protected void setLoadingAndRetryManager(LoadingAndRetryManager manager) {
        this.mLoadingAndRetryManager = manager;
    }

    @Nullable
    @Override
    protected OnLoadingAndRetryListener getOnLoadingAndRetryListener() {
        return new OnLoadingAndRetryListener() {

            @Override
            public void setLoadingEvent(View loadingView) {
                super.setLoadingEvent(loadingView);

            }

            @Override
            public void setRetryEvent(View retryView) {
                // 重试加载
                retryLoading(retryView);
            }

            @Override
            public void setEmptyEvent(View emptyView) {
                super.setEmptyEvent(emptyView);
                // 没有内容
                emptyLoading(emptyView);
            }
        };
    }

    @Override
    protected void startLoading() {
        startLoading(-2);
    }

    /**
     * 开始加载
     */
    private void startLoading(final int index){
        // 显示loading界面
        mLoadingAndRetryManager.showLoading();
        mRefreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadData(index);
            }
        }, 2000);
    }

    /**
     * 重试加载
     * @param retryView 重试加载的布局界面
     */
    private void retryLoading(View retryView) {
        // 点击重试
        View view = retryView.findViewById(R.id.btn_retry);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击重试加载
                startLoading(-1);
            }
        });
    }

    /**
     * 没有数据内容
     * @param emptyView 没有内容的布局视图
     */
    private void emptyLoading(View emptyView){
        // 点击重试
        View view = emptyView.findViewById(R.id.btn_retry);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击重试加载
                startLoading(0);
            }
        });
    }

    /**
     * 初始化标题栏
     */
    public void initTitleBar() {
        mTvTitleBarLeft.setText(getString(R.string.back_text));
        ResouceUtils.setResourceCompoundDrawables(this, mTvTitleBarLeft,
                R.drawable.ic_top_back, 0, 0, 0);
        mTvTitleBarLeft.setCompoundDrawablePadding(10);
        mTvTitleBarTitle.setText(getResources().getString(R.string.string_my_gjj));
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
        mRefreshView.setOnRefreshListener(this);
    }

    /**
     * 初始化公积金列表视图
     */
    private void initGJJListView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mGjjListView.setLayoutManager(manager);
        mGjjListView.setHasFixedSize(true);
        mGjjListView.setItemAnimator(new DefaultItemAnimator());
        mGjjAdapter = new QuickRcvAdapter<String>(this, R.layout.activity_my_gjj_list_item,
                null) {
            @Override
            protected void convert(BaseRcvAdapterHelper helper, String item) {
                // 列表项内容
                TextView tvName = helper.getView(R.id.tv_name);
                tvName.setText(item);
                // 列表项时间
                TextView tvTime = helper.getView(R.id.tv_time);
                tvTime.setText("2015/10");
            }
        };
        mGjjListView.setAdapter(mGjjAdapter);
    }

    /**
     * 初始化事件监听
     */
    public void initEvent() {
        // 点击返回按钮
        mTvTitleBarLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 点击公积金列表项
        mGjjAdapter.setOnItemClickListener(new BaseRcvQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String item = mGjjAdapter.getItem(position);
                Toast.makeText(MyGjjActivity.this, item, Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putString("data", item);
                BaseActivity.startActivity(MyGjjActivity.this, DetailActivity.class, bundle, 0);
            }
        });

        // 滚动到列表底部加载下一页数据
        mGjjListView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {

            @Override
            public void onLoadNextPage(View view) {
                super.onLoadNextPage(view);
                Toast.makeText(MyGjjActivity.this, "加载下一页数据", Toast.LENGTH_SHORT).show();
                int lastPosition = mGjjAdapter.getItemCount() - 1;
                loadData(lastPosition);
            }
        });
    }

    @Override
    public void onRefresh() {
        loadData(0);
        mRefreshView.setRefreshing(false);
        Toast.makeText(MyGjjActivity.this, "正在刷新数据", Toast.LENGTH_SHORT).show();
    }

    /**
     * 加载数据
     */
    private void loadData(int index) {
        if (index == -2){
            mLoadingAndRetryManager.showRetry();
        }else {
            if (index == -1){
                mLoadingAndRetryManager.showEmpty();
            }else {
//                mLLLayout.setVisibility(View.GONE);
                mLoadingAndRetryManager.showContent();
                List<String> gjjDatas = new ArrayList<>(12);
                for (int i = 0; i < 12; i++) {
                    if (index == 0 && i == 0) {
                        gjjDatas.add("下拉刷新数据" + i);
                    }
                    gjjDatas.add("第" + (i + 1) + "条");
                }
                mGjjAdapter.addAllToLocation(index, gjjDatas);
            }
        }
    }
}
