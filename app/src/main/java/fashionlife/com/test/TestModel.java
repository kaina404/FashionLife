package fashionlife.com.test;

import fashionlife.com.manager.NetManager;
import fashionlife.com.net.INetCall;
import fashionlife.com.util.LogUtil;

/**
 * Created by lovexujh on 2017/9/19
 */

public class TestModel {

    public void requestData(final TestModelCallBack callBack) {

        NetManager.queryCook(new INetCall() {
            @Override
            public void onResponse(int requestId, String response) {
                LogUtil.d("RESULT = " + response);
                callBack.onGetData(response);
            }

            @Override
            public void onFailure(int requestId, Object errObj) {
                LogUtil.d("失败 = " + errObj);
                callBack.onFailed("加载失败");
            }
        });


    }

    public interface TestModelCallBack {
        void onGetData(String response);

        void onFailed(String errInfo);
    }
}
