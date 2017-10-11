package fashionlife.com.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import fashionlife.com.manager.ActivityManager;

/**
 * Created by lovexujh on 2017/9/19
 */

public abstract class BaseActivity<V extends BaseView, P extends BasePresenter<V>> extends FragmentActivity implements BaseView<P> {

    private P presenter;
    protected ActivityManager activityManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        if (this.presenter == null) {
            this.presenter = attachPresenter();
            if (this.presenter != null) {
                this.presenter.attachView((V) this);
            }
        }
        activityManager = ActivityManager.getInstance();
        activityManager.addActivity(this);
    }

    protected abstract int getLayoutId();


    @Override
    protected void onDestroy() {
        activityManager.removeActivity(this);
        super.onDestroy();
        if (this.presenter != null) {
            this.presenter.detachView();
            this.presenter = null;
        }
    }
}
