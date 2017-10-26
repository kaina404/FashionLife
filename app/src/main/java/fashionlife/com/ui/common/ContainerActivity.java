package fashionlife.com.ui.common;

import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioGroup;

import fashionlife.com.R;
import fashionlife.com.app.AppUtils;
import fashionlife.com.base.component.AbstractTabFragmentActivity;
import fashionlife.com.common.FragmentId;
import fashionlife.com.manager.PermissionMangerHelper;
import fashionlife.com.util.Utils;

/**
 * @author 盛放Fragment
 *         Created by lovexujh on 2017/10/9
 */

public class ContainerActivity extends AbstractTabFragmentActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup rg;

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
        rg = (RadioGroup) findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(this);
    }


    @Override
    protected int getLayoutId() {
//        if (Build.VERSION.SDK_INT >= LOLLIPOP) {
//            View decorView = getWindow().getDecorView();
//            decorView.setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_FULLSCREEN |
//                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            getWindow().setStatusBarColor(Color.RED);
//        }
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
        return new String[]{FragmentId.HOME, FragmentId.WEATHER, FragmentId.USER};
    }

    @Override
    protected void onResume() {
        super.onResume();
        rg.getChildAt(0).performClick();

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.home:
                showFragment(FragmentId.HOME);
                break;
            case R.id.find:
                showFragment(FragmentId.WEATHER);
                break;
            case R.id.user:
                showFragment(FragmentId.USER);
                break;
            default:
        }
    }

    @Override
    public void onBackPressed() {
        if (!Utils.isEmpty(mFragmentName) && !mFragmentName.endsWith(FragmentId.HOME)) {
            showFragment(FragmentId.HOME);
            rg.getChildAt(mFragmentIndex).performClick();
        } else {
            AppUtils.exitAPP();
        }
    }
}
