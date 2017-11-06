package com.myfashionlife.util;

import com.myfashionlife.app.AppUtils;
import com.myfashionlife.common.SpConstant;

/**
 * @author: lovexujh
 * @time: 2017/11/1
 * @descripition:
 */

public class CacheUtils {

    public static String getWXNewsTitleCache(){
        //先来一波缓存
        SpUtils spUtils = new SpUtils(SpConstant.WXNEWS);
        return spUtils.getString(AppUtils.getWXTitleCacheKey(), "");
    }

    public static void setWXNewsTitleCache(String value){
        SpUtils spUtils = new SpUtils(SpConstant.WXNEWS);
        spUtils.setString(AppUtils.getWXTitleCacheKey(), value);
    }

    public static String getWXNewsInfoCache(String cid, int page) {
        SpUtils spUtils = new SpUtils(SpConstant.WXNEWS);
        return spUtils.getString(AppUtils.getWXNewsKey(cid, page), "");
    }
}
