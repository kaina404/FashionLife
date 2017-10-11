package fashionlife.com.ui.home;

import fashionlife.com.R;
import fashionlife.com.base.AbsTabFragmentActivity;
import fashionlife.com.comman.FragmentId;

/**
 * 盛放Fragment
 * Created by lovexujh on 2017/10/9
 */

public class ContainerActivity extends AbsTabFragmentActivity {

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
    }
}
