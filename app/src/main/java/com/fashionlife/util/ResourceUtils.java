package com.fashionlife.util;

import com.fashionlife.base.component.BaseApplication;

/**
 * @author: lovexujh
 * @time: 2017/11/1
 * @descripition:
 */

public class ResourceUtils {

    public static String getString(int res){
        return BaseApplication.getInstance().getString(res);
    }

}
