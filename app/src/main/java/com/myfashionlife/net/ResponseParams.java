package com.myfashionlife.net;

import com.myfashionlife.base.data.BaseBean;

/**
 * @author: lovexujh
 * @time: 2017/10/26
 * @descripition:
 */

public class ResponseParams {

    private int mRequestId;
    private RequestParams mRequestParams;
    private String mResponse;
    private BaseBean mBean;

    public int getRequestId() {
        return mRequestId;
    }

    public void setRequestId(int requestId) {
        mRequestId = requestId;
    }

    public RequestParams getRequestParams() {
        return mRequestParams;
    }

    public void setRequestParams(RequestParams requestParams) {
        mRequestParams = requestParams;
    }

    public String getResponse() {
        return mResponse;
    }

    public void setResponse(String response) {
        mResponse = response;
    }

    public ResponseParams(int requestId, RequestParams requestParams, String response) {
        mRequestId = requestId;
        mRequestParams = requestParams;
        mResponse = response;
    }

    public void setParseJsonObject(BaseBean baseBean) {
        mBean = baseBean;
    }
}
