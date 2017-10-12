package fashionlife.com.manager;

import java.util.HashMap;

import fashionlife.com.net.INetCall;
import fashionlife.com.net.NetRequest;

/**
 * Created by lovexujh on 2017/10/9
 */

public class NetManager {
    public static void questPageData(INetCall netCall, String id, int page) {
        HashMap map = new HashMap();
//        map.put(Contants.PARAMS_NAME.VERSION_CODE, Contants.APP.VERSION_CODE_VALUE);
//        map.put(Contants.PARAMS_NAME.CATEGORY_ID, id);
//        map.put(Contants.PARAMS_NAME.PAGE, page);
//        map.put(Contants.PARAMS_NAME.CRYPT, Contants.APP.CRYPT_VALUE);
//        map.put(Contants.PARAMS_NAME.UUID, Contants.APP.UUID_VALUE);
//        String params = "params=" + Tool.Base64(JSON.toJSONString(map));
//        NetRequest request = new NetRequest();
//        request.post(NetId.QUESTPREDATATEST, params, Contants.URL.VIDEO_LIST, NetRequest.FORM_TYPE, netCall);
    }


    /**
     * 请求APP的初始化数据
     *
     * @param netCall
     */
    public static void initList(INetCall netCall) {
//        HashMap map = new HashMap();
//        map.put(Contants.PARAMS_NAME.UUID, Contants.APP.UUID_VALUE);
//        map.put(Contants.PARAMS_NAME.VERSION_CODE, Contants.APP.VERSION_CODE_VALUE);
//        map.put(Contants.PARAMS_NAME.CHANNEL, "c1098");
//        map.put(Contants.PARAMS_NAME.CRYPT, Contants.APP.CRYPT_VALUE);
//
//        String requestString = JSON.toJSONString(map);
//        String params = "params=" + Tool.Base64(requestString);
//
//        NetRequest request = new NetRequest();
//        request.post(NetId.INITLIST, params, Contants.URL.INIT_LIST, NetRequest.FORM_TYPE, netCall);
    }

    public static void queryCook(INetCall netCall){
        NetRequest request = new NetRequest();
        //int reqeustId, String params, String url, String method, MediaType mediaType, INetCall netCall
//        BasicNameValuePair pair = new BasicNameValuePair("key", "1c66066891045");
//        List<BasicNameValuePair> list = new ArrayList<>();
//        list.add(pair);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("key", "1c66066891045");
//        request.get(11, hashMap, "http://apicloud.mob.com/v1/cook/category/query",  NetRequest.JSON_TYPE, netCall);
        request.get(11, hashMap, "http://apicloud.mob.com/wx/article/category/query",  NetRequest.JSON_TYPE, netCall);
    }
}
