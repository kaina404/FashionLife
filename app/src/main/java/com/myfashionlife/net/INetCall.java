package com.myfashionlife.net;

/**
 * @author
 * Created by lovexujh on 2017/10/9
 */

public interface INetCall{

    /**
     * 响应的数据
     * @param requestId
     * @param response
     */
    void onResponse(int requestId, String response);

    /**
     * 加载数据失败
     * @param requestId
     * @param errObj
     */
    void onFailure(int requestId, String errObj);
}
