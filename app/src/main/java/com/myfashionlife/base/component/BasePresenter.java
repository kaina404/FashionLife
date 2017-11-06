package com.myfashionlife.base.component;

import com.myfashionlife.base.BaseView;

/**
 * Created by lovexujh on 2017/9/19
 */

public class BasePresenter<V extends BaseView> implements IPresenter<V> {

    protected V mView;

    public BasePresenter(V v){

    }

    @Override
    public void attachView(V view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
    }

    public V getView(){
        return this.mView;
    }
}
