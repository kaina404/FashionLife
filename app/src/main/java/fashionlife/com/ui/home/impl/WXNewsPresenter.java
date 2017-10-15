package fashionlife.com.ui.home.impl;

import fashionlife.com.base.component.BasePresenter;
import fashionlife.com.ui.home.data.WXNewsBean;
import fashionlife.com.ui.home.model.WXNewsImpl;
import fashionlife.com.ui.home.model.WXNewsModel;

/**
 * Created by lovexujh on 2017/10/15
 */

public class WXNewsPresenter extends BasePresenter<WXNewsContract.View> implements WXNewsImpl {

    private WXNewsModel mModel;

    public WXNewsPresenter(WXNewsContract.View view) {
        super(view);
        mModel = new WXNewsModel(this);
    }

    public void queryWXNews(String cid, int page) {
        mModel.queryNews(cid, page);
    }



    @Override
    public void onError(String errmsg) {
        if(mView != null){
            mView.onError(errmsg);
        }
    }

    @Override
    public void refresh(WXNewsBean.ResultBean result) {
        if(mView != null){
            mView.onRefresh(result);
        }
    }
}
