package com.yj.zzgjj.gjj.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yj.zzgjj.gjj.R;
import com.yj.zzgjj.gjj.model.HomeFuncItem;
import com.yj.zzgjj.gjj.util.ResouceUtils;
import com.yj.zzgjj.gjj.view.activity.BaseActivity;
import com.yj.zzgjj.gjj.view.activity.FlowActivity;
import com.yj.zzgjj.gjj.view.activity.GuideActivity;
import com.yj.zzgjj.gjj.view.activity.LocalPolicyActivity;
import com.yj.zzgjj.gjj.view.activity.MaterialActivity;
import com.yj.zzgjj.gjj.view.activity.MyGjjActivity;
import com.yj.zzgjj.gjj.view.activity.OnlineQuestionsActivity;
import com.yj.zzgjj.gjj.widget.quickadapter.BaseAdapterHelper;
import com.yj.zzgjj.gjj.widget.quickadapter.QuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 首页 {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFragment {

    /******************** 标题栏 **********************/
    @Bind(R.id.tv_left)
    TextView mTvTitleBarLeft;           // 标题栏左边按钮
    @Bind(R.id.tv_center)
    TextView mTvTitleBarTitle;          // 标题栏中间标题
    @Bind(R.id.tv_right)
    TextView mTvTitleBarSignIn;         // 标题栏右边签到按钮

    /********************* 列表 ***********************/
    @Bind(R.id.gv_list)
    GridView mFuncList;                         // 首页功能列表

    private QuickAdapter<HomeFuncItem> mFuncAdapter;  // 功能列表适配器

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initEvent();
    }

    private void initView(View view) {
        ButterKnife.bind(this, view);
        initTitleBar();
        initHomeFuncListView();
    }

    /**
     * 初始化标题栏
     */
    private void initTitleBar() {
        mTvTitleBarLeft.setVisibility(View.GONE);
        ResouceUtils.setResourceCompoundDrawables(getActivity(), mTvTitleBarTitle,
                R.drawable.ic_tab_account_normal, 0, 0, 0);
        mTvTitleBarTitle.setCompoundDrawablePadding(10);
        mTvTitleBarTitle.setText(getResources().getString(R.string.string_zjgjj));
        mTvTitleBarSignIn.setVisibility(View.GONE);
    }

    /**
     * 初始化功能列表视图
     */
    private void initHomeFuncListView() {

        mFuncAdapter = new QuickAdapter<HomeFuncItem>(getActivity(),
                R.layout.fragment_home_list_item, initHomeFuncListData()) {
            @Override
            protected void convert(BaseAdapterHelper helper, HomeFuncItem item) {
                // 设置列表项背景色
                LinearLayout llItem = helper.getView(R.id.ll_item);
                llItem.setBackgroundColor(item.getBackgroundColorId());
                // 设置列表项图标
                ImageView ivIcon = helper.getView(R.id.iv_icon);
                ivIcon.setImageResource(item.getIconId());
                // 设置列表项标题文字
                TextView tvName = helper.getView(R.id.tv_name);
                tvName.setText(item.getName());
            }
        };
        mFuncList.setAdapter(mFuncAdapter);
    }

    /**
     * 初始化事件监听
     */
    private void initEvent(){
        // 点击功能列表项
        mFuncList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent()
                Toast.makeText(getActivity(), "第" + (position + 1) + "项", Toast.LENGTH_SHORT).show();
                // 获取功能列表项数据，进入对应的功能
                HomeFuncItem homeFuncItem = mFuncAdapter.getItem(position);
                BaseActivity.startActivity(getActivity(), homeFuncItem.getCls());
            }
        });
    }

    /**
     * 初始化首页功能列表项数据
     * @return 列表数据
     */
    private List<HomeFuncItem> initHomeFuncListData(){
        List<HomeFuncItem> homeFuncItems = new ArrayList<>(6);
        // 我的公积金
        homeFuncItems.add(new HomeFuncItem(
                R.drawable.ic_tab_account_normal, getString(R.string.string_my_gjj),
                ResouceUtils.getRescourceColor(getActivity(), R.color.color_my_gjj),
                MyGjjActivity.class));
        // 办事指南
        homeFuncItems.add(new HomeFuncItem(
                R.drawable.ic_tab_account_normal, getString(R.string.string_guide),
                ResouceUtils.getRescourceColor(getActivity(), R.color.color_guide),
                GuideActivity.class));
        // 办事流程
        homeFuncItems.add(new HomeFuncItem(
                R.drawable.ic_tab_account_normal, getString(R.string.string_flow),
                ResouceUtils.getRescourceColor(getActivity(), R.color.color_flow),
                FlowActivity.class));
        // 提取材料
        homeFuncItems.add(new HomeFuncItem(
                R.drawable.ic_tab_account_normal, getString(R.string.string_material),
                ResouceUtils.getRescourceColor(getActivity(), R.color.color_material),
                MaterialActivity.class));
        // 本地政策
        homeFuncItems.add(new HomeFuncItem(
                R.drawable.ic_tab_account_normal, getString(R.string.string_local_policy),
                ResouceUtils.getRescourceColor(getActivity(), R.color.color_local_policy),
                LocalPolicyActivity.class));
        // 在线问答
        homeFuncItems.add(new HomeFuncItem(
                R.drawable.ic_tab_account_normal, getString(R.string.string_online_questions),
                ResouceUtils.getRescourceColor(getActivity(), R.color.color_online_questions),
                OnlineQuestionsActivity.class));
        return homeFuncItems;
    }
}
