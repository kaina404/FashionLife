package fashionlife.com.ui.home.model;

import fashionlife.com.base.data.BaseModel;
import fashionlife.com.manager.NetManager;
import fashionlife.com.net.INetCall;
import fashionlife.com.net.NetId;
import fashionlife.com.ui.home.data.WXNewsTitleBean;
import fashionlife.com.util.JsonHelper;
import fashionlife.com.util.Utils;

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
