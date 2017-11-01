package com.fashionlife.ui.home.model;

import com.fashionlife.base.data.BaseModel;
import com.fashionlife.manager.NetManager;
import com.fashionlife.net.INetCall;
import com.fashionlife.net.NetId;
import com.fashionlife.ui.home.data.WXNewsTitleBean;
import com.fashionlife.util.CacheUtils;
import com.fashionlife.util.JsonHelper;
import com.fashionlife.util.Utils;

/**
 * 首页微信精选的标题
 * Created by lovexujh on 2017/10/14
 */

public class HomeWXNewsModel extends BaseModel<HomeWXNewsTitleImpl> implements INetCall {


    public HomeWXNewsModel(HomeWXNewsTitleImpl homeWXNewsTitle) {
        super(homeWXNewsTitle);
    }



    public void queryTitle() {//查询title
        NetManager.queryWXNewsTitle(NetId.QUERY_WXNEWS_TITLE, this);
    }

    @Override
    public void onResponse(int requestId, String response) {
        if (mModel == null) {
            return;
        }
        WXNewsTitleBean bean = JsonHelper.parseObject(response, WXNewsTitleBean.class);
        if (bean == null) {
            onFailure(requestId, "");
        } else {
            if (Utils.isEmpty(bean.getResult())) {
                mModel.onTitleEmpty();
            } else {
                CacheUtils.setWXNewsTitleCache(response);
                mModel.refreshTitle(bean.getResult());
            }

        }
    }


    @Override
    public void onFailure(int requestId, String errObj) {
        if (mModel == null) {
            return;
        }
        mModel.onTitleEmpty();
    }
}
