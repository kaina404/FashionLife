package fashionlife.com.base;

/**
 * Created by lovexujh on 2017/9/19
 */

public interface BasePresenter<V extends BaseView> {

    void attachView(V view);

    void detachView();
}
