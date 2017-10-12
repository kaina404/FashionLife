package fashionlife.com.base.data;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * Created by lovexujh on 2017/10/12
 */

public class FLBaseBean implements Serializable{
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
