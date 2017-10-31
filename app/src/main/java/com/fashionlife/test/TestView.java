package com.fashionlife.test;

import com.fashionlife.base.BaseView;

/**
 * Created by lovexujh on 2017/9/19
 */

public interface TestView extends BaseView {
    void onSuccess(String result);
    void onFailed(String reason);
}
