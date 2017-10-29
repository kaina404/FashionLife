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
 * @author
 * Created by lovexujh on 2017/10/9
 */

public class NetManager {

    public static void queryCook(INetCall netCall) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("key", "1c66066891045");
        NetRequest.get(11, hashMap, "http://apicloud.mob.com/wx/article/category/query", NetRequest.JSON_TYPE, netCall);
    }

    public static void queryWXNewsTitle(int requestId, INetCall netCall) {
        NetRequest.getMobAPI(requestId, null, UrlConstant.WXNewsTitle_URL, NetRequest.JSON_TYPE, netCall);
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
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(APPConstant.WXNews.CID, cid);
        hashMap.put(APPConstant.WXNews.PAGE, String.valueOf(page));
        hashMap.put(APPConstant.WXNews.SIZE, String.valueOf(size));
        NetRequest.getMobAPI(requestId, hashMap, UrlConstant.WXNews_DETAIL_INFO_URL, NetRequest.JSON_TYPE, netCall);
    }

    /**
     * @param netCall
     * @param province     天津
     * @param city 塘沽
     */
    public static void queryWeatherTmp(INetCall netCall, String province, String city) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(APPConstant.Weather.PROVINCE, province);
        hashMap.put(APPConstant.Weather.CITY, city);
        NetRequest.getMobAPI(NetId.QUERY_WEATHER, hashMap, UrlConstant.QUERY_WEATHER, NetRequest.JSON_TYPE, netCall);
    }

    public static void queryWeatherSupportCity(INetCall netCall) {
        NetRequest.getMobAPI(NetId.QUERY_SUPPORT_CITY_WEATHER, null, UrlConstant.QUERY_SUPPORT_WEATHER, NetRequest.JSON_TYPE, netCall);
    }

    /**
     * 查询bing美图的response
     *
     * @param netCall
     */
    public static void queryBingWallpaperUrl(INetCall netCall) {
        NetRequest.http(NetId.QUERY_BING_WALLPAPER_URL, null, UrlConstant.QUERY_BING_WALLPAPER_URL, NetRequest.Method.GET, NetRequest.JSON_TYPE, netCall);
    }

    public static void downloadImg(int requestId, final String url, final INetCall netCall) {
        NetRequest.downloadFile(requestId, url, netCall, new ProgressResponseListener() {
            @Override
            public void onResponseProgress(double read, double all) {
                if(read == all){
                    LogUtil.d("NetManager", "下载壁纸成功");
                }

            }
        });
    }
}
