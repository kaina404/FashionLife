package com.myfashionlife.base.component;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myfashionlife.base.BaseView;

/**
 * Created by lovexujh on 2017/9/19
 */

public abstract class BaseFragment<P extends IPresenter> extends Fragment implements BaseView {

    protected P mPresenter;
    protected View mContainerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.mPresenter == null) {
            this.mPresenter = attachPresenter();
            if (this.mPresenter != null) {
                this.mPresenter.attachView(this);
            }
        }
    }

    protected abstract P attachPresenter();

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
        if (this.mPresenter != null) {
            this.mPresenter.detachView();
            this.mPresenter = null;
        }
    }
}
