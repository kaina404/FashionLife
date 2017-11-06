package com.myfashionlife.ui.common;

import android.os.Bundle;

import com.myfashionlife.R;
import com.myfashionlife.app.AppUtils;
import com.myfashionlife.base.component.AbstractTabFragmentActivity;
import com.myfashionlife.common.FragmentId;
import com.myfashionlife.manager.PermissionMangerHelper;
import com.myfashionlife.util.ScreenUtils;
import com.myfashionlife.util.Utils;
import com.myfashionlife.util.location.LocationHelper;
import com.myfashionlife.widget.BottomTabWidget;

/**
 * @author 盛放Fragment
 *         Created by lovexujh on 2017/10/9
 */

public class ContainerActivity extends AbstractTabFragmentActivity implements BottomTabWidget.OnCheckedListener {

    private BottomTabWidget mBottomView;

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        PermissionMangerHelper.init(ContainerActivity.this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }

    private void initViews() {
        mBottomView = (BottomTabWidget) findViewById(R.id.bottom_tab_view);
        BottomTabData.create().setBottomView(mBottomView).initView();
        mBottomView.setOnCheckedListener(this);
    }


    @Override
    protected int getLayoutId() {
        ScreenUtils.translucentTitle(this);
        return R.layout.activity_container;
    }


    @Override
    protected int getContainerViewId() {
        return R.id.container;
    }

    @Override
    public String[] getFragments() {
        return new String[]{FragmentId.HOME, FragmentId.WEATHER, FragmentId.TOOL};
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBottomView.setCheck(mFragmentIndex >= 0 ? mFragmentIndex : 0, true);
        Utils.handlerNoNet(this);
    }


    @Override
    public void onBackPressed() {
        if (!Utils.isEmpty(mFragmentName) && !mFragmentName.endsWith(FragmentId.HOME)) {
            showFragment(FragmentId.HOME);
            mBottomView.setCheck(mFragmentIndex, true);
        } else {
            LocationHelper.getInstance().stop();
            AppUtils.exitAPP();
        }
    }

    @Override
    public void onChecked(int index) {
        switch (index){
            case 0:
                showFragment(FragmentId.HOME);
                break;
            case 1:
                showFragment(FragmentId.WEATHER);
                break;
            case 2:
                showFragment(FragmentId.TOOL);
                break;
            default:
        }
    }
}
