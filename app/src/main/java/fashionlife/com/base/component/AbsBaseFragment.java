package fashionlife.com.base.component;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fashionlife.com.base.BaseView;

/**
 * Created by lovexujh on 2017/9/19
 */

public abstract class AbsBaseFragment<V extends BaseView, P extends BasePresenter<V>> extends Fragment implements BaseView<P> {

    private P presenter;
    private View mContainerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.presenter == null) {
            this.presenter = attachPresenter();
            if (this.presenter != null) {
                this.presenter.attachView((V) this);
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContainerView = inflater.inflate(getLayoutId(), null);
        return mContainerView;
    }

    protected abstract int getLayoutId();


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (this.presenter != null) {
            this.presenter.detachView();
            this.presenter = null;
        }
    }
}
