package fashionlife.com.base.data;

import java.io.Serializable;

import fashionlife.com.util.JsonHelper;

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
