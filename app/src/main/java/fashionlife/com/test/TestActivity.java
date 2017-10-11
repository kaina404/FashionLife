package fashionlife.com.test;

import fashionlife.com.R;
import fashionlife.com.base.BaseActivity;

/**
 * Created by lovexujh on 2017/9/19
 */

public class TestActivity extends BaseActivity<TestView, TestPresenter> implements TestView {

    private TestPresenter presenter;



    @Override
    public TestPresenter attachPresenter() {
        return presenter = new TestPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.requestData();
    }

    @Override
    public void onSuccess(String result) {

    }

    @Override
    public void onFailed(String reason) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}
