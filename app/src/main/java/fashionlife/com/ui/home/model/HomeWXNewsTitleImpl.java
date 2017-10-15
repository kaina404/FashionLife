package fashionlife.com.ui.home.model;

import java.util.List;

import fashionlife.com.ui.home.data.WXNewsTitleBean;

/**
 * Created by lovexujh on 2017/10/14
 */

public interface HomeWXNewsTitleImpl {

    void refreshTitle(List<WXNewsTitleBean.ResultBean> beanList);
    void onTitleEmpty();
}
