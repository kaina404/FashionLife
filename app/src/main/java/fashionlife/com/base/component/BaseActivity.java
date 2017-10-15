package fashionlife.com.base.component;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import fashionlife.com.base.BaseView;
import fashionlife.com.manager.ActivityManager;

/**
 * Created by lovexujh on 2017/9/19
 */

public abstract class BaseActivity<P extends IPresenter> extends FragmentActivity implements BaseView {

    protected P mPresenter;
    protected ActivityManager mActivityManager;
    protected View mContentView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContentView = View.inflate(this, getLayoutId(), null);
        setContentView(mContentView);
        if (this.mPresenter == null) {
            this.mPresenter = attachPresenter();
            if (this.mPresenter != null) {
                this.mPresenter.attachView(this);
            }
        }
        mActivityManager = ActivityManager.getInstance();
        mActivityManager.addActivity(this);
    }

    protected P attachPresenter() {
        return null;
    }

    protected abstract int getLayoutId();


    @Override
    protected void onDestroy() {
        mActivityManager.removeActivity(this);
        super.onDestroy();
        if (this.mPresenter != null) {
            this.mPresenter.detachView();
            this.mPresenter = null;
        }
    }
}
