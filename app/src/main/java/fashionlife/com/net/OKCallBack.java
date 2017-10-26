package fashionlife.com.net;

import android.os.Handler;
import android.os.Message;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import fashionlife.com.listener.ProgressResponseListener;
import fashionlife.com.util.JsonHelper;
import fashionlife.com.util.LogUtil;
import fashionlife.com.util.Utils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author: lovexujh
 * @time: 2017/10/26
 * @descripition:
 */

public class OkCallBack implements Callback {

    private static final int HTTP_ERROR = 404;//http未响应
    private static final int HTTP_JSON_SUCCEED = 200;//仅仅HTTP响应

    private static MyHandler myHandler = new MyHandler();


    public OkCallBack() {

    }


    @Override
    public void onFailure(Call call, IOException e) {

        RequestParams requestParams = (RequestParams) call.request().tag();

        Message msg = Message.obtain();
        msg.obj = new ResponseParams(requestParams.getRequestId(), requestParams, e.getMessage());
        msg.arg1 = HTTP_ERROR;
        myHandler.sendMessage(msg);
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        RequestParams requestParams = (RequestParams) response.request().tag();

        ResponseParams responseParams;
        if (requestParams.isIsGetJson()) {
            String result = response.body().string();
            responseParams = new ResponseParams(requestParams.getRequestId(), requestParams, result);
            if (requestParams.getTClass() != null) {
                responseParams.setParseJsonObject(JsonHelper.parseObject(result, requestParams.getTClass()));
            }
        } else {
            String tmp = parseDownloadFile(response, requestParams.getUrl(), requestParams.getProgressResponseListener());
            if (!Utils.isEmpty(tmp)) {
                responseParams = new ResponseParams(requestParams.getRequestId(), requestParams, tmp);
            } else {
                onFailure(call, new IOException("下载文件解析出错"));
                return;
            }
        }

        //将httpResponse发送到UI线程
        Message msg = Message.obtain();
        msg.obj = responseParams;
        msg.arg1 = HTTP_JSON_SUCCEED;
        myHandler.sendMessage(msg);
    }

    private String parseDownloadFile(Response response, String url, ProgressResponseListener progressListener) throws IOException {
        LogUtil.d("=======开始解析下载文件 " + url + " ======");
        ResponseBody responseBody = response.body();
        InputStream inputStream = responseBody.byteStream();
        if (inputStream == null) {
            return "";
        }

        String absolutePath = Utils.createDownloadFilePath(url);
        if (!Utils.isEmpty(absolutePath)) {
            File file = new File(absolutePath);
            boolean readSuccess = Utils.inputStream2File(inputStream, file, progressListener, responseBody.contentLength());
            inputStream.close();
            if (readSuccess) {
                absolutePath = file.getAbsolutePath();
                LogUtil.d("=======下载文件== " + url + " ==解析失败===");
            } else {
                absolutePath = "";
            }
            LogUtil.d("=======下载文件====解析成功===路径是=== " + absolutePath);

        } else {
            LogUtil.d("=======下载文件== " + url + " ==解析失败===");
        }
        return absolutePath;
    }


    private static class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg != null && msg.obj instanceof ResponseParams) {
                ResponseParams responseParams = (ResponseParams) msg.obj;

                switch (msg.arg1) {
                    case HTTP_JSON_SUCCEED:
                        responseParams.getRequestParams().getNetCall().onResponse(responseParams.getRequestId(), responseParams.getResponse());
                        break;
                    case HTTP_ERROR:
                        String err = msg.obj == null ? "未知错误" : (String) msg.obj;
                        responseParams.getRequestParams().getNetCall().onFailure(msg.what, err);
                        break;
                    default:
                }
            }

        }
    }


}
