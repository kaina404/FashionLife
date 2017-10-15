package fashionlife.com.test;

import fashionlife.com.base.BaseView;

/**
 * Created by lovexujh on 2017/9/19
 */

public interface TestView extends BaseView {
    void onSuccess(String result);
    void onFailed(String reason);
}
