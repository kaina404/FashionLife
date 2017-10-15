package fashionlife.com.ui.home.model;

import fashionlife.com.app.APPConstant;
import fashionlife.com.app.AppUtils;
import fashionlife.com.base.data.BaseModel;
import fashionlife.com.common.SpConstant;
import fashionlife.com.manager.NetManager;
import fashionlife.com.net.INetCall;
import fashionlife.com.net.NetId;
import fashionlife.com.ui.home.data.WXNewsBean;
import fashionlife.com.util.JSONUtils;
import fashionlife.com.util.SpUtils;

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

        // TODO: 2017/10/15  test
//        DBCache cacheDb = new DBCache();
//        long value = cacheDb.update(AppUtils.getWXNewsKey(mCid, mPage), "你好中国");

        SpUtils spUtils = new SpUtils(SpConstant.WXNEWS);
        spUtils.insert(AppUtils.getWXNewsKey(mCid, mPage), mResponse);

        WXNewsBean wxNewsBean = JSONUtils.parseObject(response, WXNewsBean.class);
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
