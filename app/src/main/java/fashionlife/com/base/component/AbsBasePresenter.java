package fashionlife.com.base.component;

import fashionlife.com.base.BaseView;

/**
 * Created by lovexujh on 2017/9/19
 */

public class AbsBasePresenter<V extends BaseView> implements BasePresenter<V> {

    private V view;

    @Override
    public void attachView(V view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    public V getView(){
        return this.view;
    }
}
