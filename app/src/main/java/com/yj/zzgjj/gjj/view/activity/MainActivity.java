package com.yj.zzgjj.gjj.view.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.yj.zzgjj.gjj.R;
import com.yj.zzgjj.gjj.view.fragment.AccountFragment;
import com.yj.zzgjj.gjj.view.fragment.HomeFragment;
import com.yj.zzgjj.gjj.view.fragment.ServerFragment;
import com.yj.zzgjj.gjj.view.fragment.SettingFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.tab_bar)
    RadioGroup mTabBar;
    @Bind(R.id.tab_home)
    RadioButton mHomeTab;
    @Bind(R.id.fragment_container)
    FrameLayout mContentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }

    protected void initView() {
        ButterKnife.bind(this);
        selectChanged(mTabBar.getCheckedRadioButtonId());
    }

    protected void initEvent() {

        mTabBar.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                selectChanged(checkedId);
            }
        });
    }

    private void selectChanged(int checkedId){
        FragmentManager fManger = getSupportFragmentManager();
        HomeFragment homeFragment = (HomeFragment) fManger.findFragmentByTag(HomeFragment.class.getSimpleName());
        ServerFragment serverFragment = (ServerFragment) fManger.findFragmentByTag(ServerFragment.class.getSimpleName());
        AccountFragment accountFragment = (AccountFragment) fManger.findFragmentByTag(AccountFragment.class.getSimpleName());
        SettingFragment settingFragment = (SettingFragment) fManger.findFragmentByTag(SettingFragment.class.getSimpleName());
        FragmentTransaction ft = fManger.beginTransaction();

        // 隐藏对应的fragment界面
        if (checkedId == R.id.tab_home
                || checkedId == R.id.tab_server
                || checkedId == R.id.tab_account
                || checkedId == R.id.tab_setting){
            if (null != homeFragment && !homeFragment.isHidden()) {
                ft.hide(homeFragment);
            }
            if (null != serverFragment && !serverFragment.isHidden()) {
                ft.hide(serverFragment);
            }
            if (null != accountFragment && !accountFragment.isHidden()) {
                ft.hide(accountFragment);
            }
            if (null != settingFragment && !settingFragment.isHidden()) {
                ft.hide(settingFragment);
            }
        }

        // 选中的fragment，加入事务或者直接显示
        switch (checkedId){
            case R.id.tab_home:
                if (null == homeFragment){
                    ft.add(R.id.fragment_container, new HomeFragment(), HomeFragment.class.getSimpleName());
                }else {
                    ft.show(homeFragment);
                }
                break;
            case R.id.tab_server:
                if (null == serverFragment){
                    ft.add(R.id.fragment_container, new ServerFragment(), ServerFragment.class.getSimpleName());
                }else {
                    ft.show(serverFragment);
                }
                break;
            case R.id.tab_account:
                if (null == accountFragment){
                    ft.add(R.id.fragment_container, new AccountFragment(), AccountFragment.class.getSimpleName());
                }else {
                    ft.show(accountFragment);
                }
                break;
            case R.id.tab_setting:
                if (null == settingFragment){
                    ft.add(R.id.fragment_container, new SettingFragment(), SettingFragment.class.getSimpleName());
                }else {
                    ft.show(settingFragment);
                }
                break;
        }
        ft.commitAllowingStateLoss();
    }

    // 用来计算返回键的点击间隔时间
    private long exitTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                // 彻底退出程序
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
