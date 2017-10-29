package fashionlife.com.net;

import android.support.annotation.Nullable;

import org.apache.http.message.BasicNameValuePair;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import fashionlife.com.app.APPConstant;
import fashionlife.com.base.component.BaseApplication;
import fashionlife.com.listener.ProgressResponseListener;
import fashionlife.com.util.NetStatusUtil;
import fashionlife.com.util.URLEncodedUtils;
import fashionlife.com.util.Utils;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.internal.Util;

/**
 * @author Created by lovexujh on 2017/10/9
 */

public class NetRequest {
    private static OkHttpClient okHttpClient;
    public static final MediaType JSON_TYPE = MediaType.parse("application/json");
    public static final MediaType FORM_TYPE = MediaType.parse("application/x-www-form-urlencoded");
    private static final String CONTENT_TYPE = "Content-Type";

    static {
        okHttpClient = new OkHttpClient.Builder().
                connectTimeout(10, TimeUnit.SECONDS).
                readTimeout(10, TimeUnit.SECONDS).
                writeTimeout(10, TimeUnit.SECONDS).build();
    }


    public interface Method {
        String POST = "POST";
        String GET = "GET";
    }

    public static RequestBody create(@Nullable MediaType contentType, String content) {
        Charset charset = Util.UTF_8;
        if (contentType != null) {
            charset = contentType.charset();
            if (charset == null) {
                charset = Util.UTF_8;
                contentType = MediaType.parse(contentType + "");
            }
        }
        byte[] bytes = content.getBytes(charset);
        return RequestBody.create(contentType, bytes);
    }

    public static void http(int requestId, Object params, String url, String method, MediaType mediaType, INetCall netCall) {

        if (NetStatusUtil.getAPNType(BaseApplication.getInstance().getApplicationContext()) == NetStatusUtil.CONNECTION_FAILED) {
            Utils.handlerNoNet(BaseApplication.getInstance().getApplicationContext());
            return;
        }

        RequestBody body = null;
        Map<String, List<String>> headers = new HashMap<>();
        //设置Content-Type
        ArrayList<String> list = new ArrayList<>();
        if (Method.POST.equals(method) && params != null && params instanceof String) {
            if (mediaType == null) {
                body = create(JSON_TYPE, (String) params);
                list.add(JSON_TYPE.type() + "/" + JSON_TYPE.subtype());
            } else {
                body = create(mediaType, (String) params);
                list.add(FORM_TYPE.type() + "/" + FORM_TYPE.subtype());
            }
        }
        headers.put(CONTENT_TYPE, list);
        //构建OKHttpRequest
        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.tag(new RequestParams(true, url, requestId, netCall));
        requestBuilder.url(url);
        requestBuilder.method(method, body);
        if (headers != null && !headers.isEmpty()) {
            Set<String> keySet = headers.keySet();
            for (String key : keySet) {
                List<String> values = headers.get(key);
                requestBuilder.removeHeader(key);
                for (String value : values) {
                    requestBuilder.addHeader(key, value);
                }
            }
        }


        //请求
        Call call = okHttpClient.newCall(requestBuilder.build());
        if (netCall != null) {
            call.enqueue(new OkCallBack());
        }
    }


    public static void post(int requestId, String params, String url, MediaType mediaType, INetCall netCall) {
        http(requestId, params, url, Method.POST, mediaType, netCall);
    }

    public static void get(int requestId, List<BasicNameValuePair> params, String url, MediaType mediaType, INetCall netCall) {
        url = URLEncodedUtils.attachHttpGetParams(url, params);
        http(requestId, "", url, Method.GET, mediaType, netCall);
    }

    public static void get(int requestId, HashMap<String, String> params, String url, MediaType mediaType, INetCall netCall) {
        url = URLEncodedUtils.attachHttpGetParams(url, params);
        http(requestId, "", url, Method.GET, mediaType, netCall);
    }

    public static void getMobAPI(int requestId, HashMap<String, String> params, String url, MediaType mediaType, INetCall netCall) {
        if (params == null) {
            params = new HashMap<>();
        }
        params.put(APPConstant.KEY_STR, APPConstant.MOBAPI_KEY);
        url = URLEncodedUtils.attachHttpGetParams(url, params);
        http(requestId, "", url, Method.GET, mediaType, netCall);
    }


    /**
     * url需要时一个标准的文件链接，如：xiaomi.mp4、apple_demo.avi等等，否则存储文件命名时会出错
     *
     * @param url
     * @param netCall 回调会返回下载的文件路径
     */
    public static void downloadFile(int requestId, String url, INetCall netCall, final ProgressResponseListener progressListener) {
        if (Utils.isEmpty(url)) {
            return;
        }
        RequestParams requestParams = new RequestParams(false, url, requestId, netCall);
        requestParams.setProgressResponseListener(progressListener);
        Request request = new Request.Builder().get().url(url).tag(requestParams).build();
        okHttpClient.newCall(request).enqueue(new OkCallBack());
    }

}
