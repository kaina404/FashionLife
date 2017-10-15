package fashionlife.com.ui.home.model;

import fashionlife.com.app.APPConstant;
import fashionlife.com.base.data.BaseModel;
import fashionlife.com.manager.NetManager;
import fashionlife.com.net.INetCall;
import fashionlife.com.net.NetId;
import fashionlife.com.ui.home.data.WXNewsBean;
import fashionlife.com.util.JSONUtils;

/**
 * Created by lovexujh on 2017/10/15
 */

public class WXNewsModel extends BaseModel<WXNewsImpl> implements INetCall {


    public WXNewsModel(WXNewsImpl wxNews) {
        super(wxNews);
    }


    public void queryNews(String cid, String page) {
        NetManager.queryWXNews(NetId.WX_NEWS_ID, cid, page, APPConstant.WXNews.SIZE_20, this);
    }

    @Override
    public void onResponse(int requestId, String response) {
        if (mModel == null) {
            return;
        }
        WXNewsBean wxNewsBean = JSONUtils.parseObject(response, WXNewsBean.class);
        if (wxNewsBean == null || wxNewsBean.getResult() == null || wxNewsBean.getResult().getList() == null) {
            mModel.onError("");
            return;
        }
        mModel.refresh(wxNewsBean.getResult().getList());

    }


    @Override
    public void onFailure(int requestId, String errObj) {
        if(mModel != null){
            mModel.onError(errObj);
        }
    }
}
