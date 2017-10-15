package fashionlife.com.util;

import com.alibaba.fastjson.JSON;

/**
 * Created by lovexujh on 2017/10/15
 */

public class JSONUtils {


    public static <T> T parseObject(String json, Class<T> clazz) {
        try{
            return JSON.parseObject(json, clazz);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
