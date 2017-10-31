package com.fashionlife.util;

import com.alibaba.fastjson.JSON;

/**
 * @author
 * Created by lovexujh on 2017/10/15
 */

public class JsonHelper {


    public static <T> T parseObject(String json, Class<T> clazz) {
        try{
            return JSON.parseObject(json, clazz);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static String toJSONString(Object object) {
        try {
           return JsonHelper.toJSONString(object);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
