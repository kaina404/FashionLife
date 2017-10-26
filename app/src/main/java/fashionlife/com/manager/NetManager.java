package fashionlife.com.manager;

import java.util.HashMap;

import fashionlife.com.app.APPConstant;
import fashionlife.com.app.AppUtils;
import fashionlife.com.common.SpConstant;
import fashionlife.com.common.UrlConstant;
import fashionlife.com.listener.ProgressResponseListener;
import fashionlife.com.net.INetCall;
import fashionlife.com.net.NetId;
import fashionlife.com.net.NetRequest;
import fashionlife.com.util.LogUtil;
import fashionlife.com.util.SpUtils;
import fashionlife.com.util.Utils;

/**
 * Created by lovexujh on 2017/10/9
 */

public class NetManager {
    public static void questPageData(INetCall netCall, String id, int page) {
        HashMap map = new HashMap();
//        map.put(APPConstant.PARAMS_NAME.VERSION_CODE, APPConstant.APP.VERSION_CODE_VALUE);
//        map.put(APPConstant.PARAMS_NAME.CATEGORY_ID, id);
//        map.put(APPConstant.PARAMS_NAME.PAGE, page);
//        map.put(APPConstant.PARAMS_NAME.CRYPT, APPConstant.APP.CRYPT_VALUE);
//        map.put(APPConstant.PARAMS_NAME.UUID, APPConstant.APP.UUID_VALUE);
//        String params = "params=" + Utils.Base64(JSON.toJSONString(map));
//        NetRequest request = new NetRequest();
//        request.post(NetId.QUESTPREDATATEST, params, APPConstant.URL.VIDEO_LIST, NetRequest.FORM_TYPE, netCall);
    }


    /**
     * 请求APP的初始化数据
     *
     * @param netCall
     */
    public static void initList(INetCall netCall) {
//        HashMap map = new HashMap();
//        map.put(APPConstant.PARAMS_NAME.UUID, APPConstant.APP.UUID_VALUE);
//        map.put(APPConstant.PARAMS_NAME.VERSION_CODE, APPConstant.APP.VERSION_CODE_VALUE);
//        map.put(APPConstant.PARAMS_NAME.CHANNEL, "c1098");
//        map.put(APPConstant.PARAMS_NAME.CRYPT, APPConstant.APP.CRYPT_VALUE);
//
//        String requestString = JSON.toJSONString(map);
//        String params = "params=" + Utils.Base64(requestString);
//
//        NetRequest request = new NetRequest();
//        request.post(NetId.INITLIST, params, APPConstant.URL.INIT_LIST, NetRequest.FORM_TYPE, netCall);
    }

    public static void queryCook(INetCall netCall) {
        NetRequest request = new NetRequest();
        //int reqeustId, String params, String url, String method, MediaType mediaType, INetCall netCall
//        BasicNameValuePair pair = new BasicNameValuePair("key", "1c66066891045");
//        List<BasicNameValuePair> list = new ArrayList<>();
//        list.add(pair);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("key", "1c66066891045");
//        request.get(11, hashMap, "http://apicloud.mob.com/v1/cook/category/query",  NetRequest.JSON_TYPE, netCall);
        request.get(11, hashMap, "http://apicloud.mob.com/wx/article/category/query", NetRequest.JSON_TYPE, netCall);
    }

    public static void queryWXNewsTitle(int requestId, INetCall netCall) {
        NetRequest request = new NetRequest();
        request.getMobAPI(requestId, null, UrlConstant.WXNewsTitle_URL, NetRequest.JSON_TYPE, netCall);
    }

    /**
     * key	string	是	用户申请的appkey
     * cid	string	是	分类id
     * page	int	是	分页参数，起始页
     * size	int	是	分页参数，每页记录数据
     * <p>
     * 查询微信精选详情
     *
     * @param requestId
     * @param cid
     * @param netCall
     */
    public static void queryWXNews(int requestId, String cid, int page, int size, INetCall netCall) {
        //先来一波缓存
        SpUtils spUtils = new SpUtils(SpConstant.WXNEWS);
        String cache = spUtils.getString(AppUtils.getWXNewsKey(cid, page), "");
        if (!Utils.isEmpty(cache)) {
            netCall.onResponse(requestId, cache);
        }
        //请求网络
        NetRequest request = new NetRequest();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(APPConstant.WXNews.CID, cid);
        hashMap.put(APPConstant.WXNews.PAGE, String.valueOf(page));
        hashMap.put(APPConstant.WXNews.SIZE, String.valueOf(size));
        request.getMobAPI(requestId, hashMap, UrlConstant.WXNews_DETAIL_INFO_URL, NetRequest.JSON_TYPE, netCall);
    }

    /**
     * @param netCall
     * @param city     天津
     * @param district 塘沽
     */
    public static void queryWeatherTmp(INetCall netCall, String city, String district) {
        NetRequest netRequest = new NetRequest();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(APPConstant.Weather.CITY, city);
        hashMap.put(APPConstant.Weather.PROVINCE, district);
        netRequest.getMobAPI(NetId.QUERY_WEATHER, hashMap, UrlConstant.QUERY_WEATHER, NetRequest.JSON_TYPE, netCall);
    }

    public static void queryWeatherSupportCity(INetCall netCall) {

        NetRequest netRequest = new NetRequest();
        netRequest.getMobAPI(NetId.QUERY_SUPPORT_CITY_WEATHER, null, UrlConstant.QUERY_SUPPORT_WEATHER, NetRequest.JSON_TYPE, netCall);
    }

    /**
     * 查询bing美图的response
     *
     * @param netCall
     */
    public static void queryBingWallpaperUrl(INetCall netCall) {
        NetRequest netRequest = new NetRequest();
        netRequest.http(NetId.QUERY_BING_WALLPAPER_URL, null, UrlConstant.QUERY_BING_WALLPAPER_URL, NetRequest.Method.GET, NetRequest.JSON_TYPE, netCall);
    }

    public static void downloadImg(int requestId, final String url, final INetCall netCall) {
        NetRequest netRequest = new NetRequest();
        netRequest.downloadFile(requestId, url, netCall, new ProgressResponseListener() {
            @Override
            public void onResponseProgress(double read, double all) {
                if(read == all){
                    LogUtil.d("NetManager", "下载壁纸成功");
                }

            }
        });
    }
}
