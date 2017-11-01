package com.fashionlife.ui.home.model;

import com.fashionlife.app.APPConstant;
import com.fashionlife.app.AppUtils;
import com.fashionlife.base.data.BaseModel;
import com.fashionlife.common.SpConstant;
import com.fashionlife.manager.NetManager;
import com.fashionlife.net.INetCall;
import com.fashionlife.net.NetId;
import com.fashionlife.ui.home.data.WXNewsBean;
import com.fashionlife.util.JsonHelper;
import com.fashionlife.util.SpUtils;

/**
 * Created by lovexujh on 2017/10/15
 */

public class WXNewsModel extends BaseModel<WXNewsImpl> implements INetCall {


    private String mCid;
    private int mPage;
    private String mResponse;

    public WXNewsModel(WXNewsImpl wxNews) {
        super(wxNews);
    }


    public void queryNews(String cid, int page) {
        this.mCid = cid;
        this.mPage = page;
        NetManager.queryWXNews(NetId.WX_NEWS_ID, cid, page, APPConstant.WXNews.SIZE_20, this);
    }

    @Override
    public void onResponse(int requestId, String response) {
        if (mModel == null) {
            return;
        }
        mResponse = response;


        WXNewsBean wxNewsBean = JsonHelper.parseObject(response, WXNewsBean.class);

        if(wxNewsBean != null){
            SpUtils spUtils = new SpUtils(SpConstant.WXNEWS);
            spUtils.insert(AppUtils.getWXNewsKey(mCid, mPage), mResponse);
        }

        if (wxNewsBean == null || wxNewsBean.getResult() == null || wxNewsBean.getResult().getList() == null) {
            mModel.onError("");
            return;
        }
        mModel.refresh(wxNewsBean.getResult());

    }


    @Override
    public void onFailure(int requestId, String errObj) {
        if(mModel != null){
            mModel.onError(errObj);
        }
    }
}
