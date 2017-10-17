package fashionlife.com.base.impl;

/**
 * Created by lovexujh on 2017/10/17
 */

public interface MultiItemRecycle<T> {
    int getItemViewType(int position, T t);
    int getItemLayoutId(int viewType);
}
