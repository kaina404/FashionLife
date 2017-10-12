package fashionlife.com.base.component;

import fashionlife.com.base.BaseView;

/**
 * Created by lovexujh on 2017/9/19
 */

public interface BasePresenter<V extends BaseView> {

    void attachView(V view);

    void detachView();
}
