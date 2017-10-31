package com.fashionlife.base.component;

import com.fashionlife.base.BaseView;

/**
 * Created by lovexujh on 2017/9/19
 */

public interface IPresenter<V extends BaseView> {

    void attachView(V view);

    void detachView();
}
