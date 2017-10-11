package fashionlife.com.ui.home;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.RadioGroup;

import fashionlife.com.R;
import fashionlife.com.app.AppUtils;
import fashionlife.com.base.AbsTabFragmentActivity;
import fashionlife.com.comman.FragmentId;
import fashionlife.com.util.Tool;

/**
 * 盛放Fragment
 * Created by lovexujh on 2017/10/9
 */

public class ContainerActivity extends AbsTabFragmentActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup rg;

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
    public Object attachPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_container;
    }


    @Override
    protected int getContainerViewId() {
        return R.id.container;
    }

    @Override
    public String[] getFragments() {
        return new String[]{FragmentId.HOME, FragmentId.FIND, FragmentId.USER};
    }

    @Override
    protected void onResume() {
        super.onResume();
        rg.getChildAt(0).performClick();
        // TODO: 2017/10/12  关于权限的要封装起来
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, RESULT_OK);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.home:
                showFragment(FragmentId.HOME);
                break;
            case R.id.find:
                showFragment(FragmentId.FIND);
                break;
            case R.id.user:
                showFragment(FragmentId.USER);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (!Tool.isEmpty(mFragmentName) && !mFragmentName.endsWith(FragmentId.HOME)) {
            showFragment(FragmentId.HOME);
            rg.getChildAt(mFragmentIndex).performClick();
        } else {
            AppUtils.exitAPP();
        }
    }
}
