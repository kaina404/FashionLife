package fashionlife.com.ui.common;

import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import fashionlife.com.R;
import fashionlife.com.app.AppUtils;
import fashionlife.com.base.component.AbstractTabFragmentActivity;
import fashionlife.com.common.FragmentId;
import fashionlife.com.manager.PermissionMangerHelper;
import fashionlife.com.util.Utils;
import fashionlife.com.util.location.LocationHelper;
import fashionlife.com.widget.BottomTabWidget;

/**
 * @author 盛放Fragment
 *         Created by lovexujh on 2017/10/9
 */

public class ContainerActivity extends AbstractTabFragmentActivity implements BottomTabWidget.OnCheckedListener {

    private BottomTabWidget mBottomView;

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        PermissionMangerHelper.init(this);
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
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
