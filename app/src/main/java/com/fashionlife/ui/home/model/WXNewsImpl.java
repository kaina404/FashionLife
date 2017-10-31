package com.fashionlife.ui.home.model;

import com.fashionlife.ui.home.data.WXNewsBean;

/**
 * Created by lovexujh on 2017/10/15
 */

public interface WXNewsImpl {
    void onError(String errmsg);

    void refresh(WXNewsBean.ResultBean result);
}
