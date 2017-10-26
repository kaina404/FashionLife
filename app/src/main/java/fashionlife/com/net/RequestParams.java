package fashionlife.com.net;

import android.support.annotation.Nullable;

import fashionlife.com.base.data.BaseBean;
import fashionlife.com.listener.ProgressResponseListener;

/**
 * @author: lovexujh
 * @time: 2017/10/26
 * @descripition:
 */

public class RequestParams {

    private @Nullable INetCall mNetCall;
    private @Nullable boolean mIsGetJson;
    private @Nullable String mUrl;
    private @Nullable int mRequestId;
    /**
     * 用来下载文件时，查看下载进度的。
     */
    private ProgressResponseListener mProgressResponseListener;

    /**
     * 当请求JSON数据时，设置了后，http响应后会去进行fastjson处理
     */
    private Class<? extends BaseBean> mTClass;


    public RequestParams(boolean isGetJson, String url, int requestId, INetCall netCall) {
        mIsGetJson = isGetJson;
        mUrl = url;
        mRequestId = requestId;
        mNetCall = netCall;
    }


    public Class<? extends BaseBean> getTClass() {
        return mTClass;
    }

    public void setTClass(Class<? extends BaseBean> TClass) {
        mTClass = TClass;
    }


    public ProgressResponseListener getProgressResponseListener() {
        return mProgressResponseListener;
    }

    public void setProgressResponseListener(ProgressResponseListener progressResponseListener) {
        mProgressResponseListener = progressResponseListener;
    }


    public INetCall getNetCall() {
        return mNetCall;
    }

    public void setNetCall(INetCall mNetCall) {
        this.mNetCall = mNetCall;
    }


    public boolean isIsGetJson() {
        return mIsGetJson;
    }

    public void setIsGetJson(boolean mIsGetJson) {
        this.mIsGetJson = mIsGetJson;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public int getRequestId() {
        return mRequestId;
    }

    public void setRequestId(int mRequestId) {
        this.mRequestId = mRequestId;
    }

}
