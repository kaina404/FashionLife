package com.fashionlife.base.data;

import java.io.Serializable;

import com.fashionlife.util.JsonHelper;

/**
 * @author
 * Created by lovexujh on 2017/10/12
 */

public class BaseBean implements Serializable{
    @Override
    public String toString() {
        return JsonHelper.toJSONString(this);
    }
}
