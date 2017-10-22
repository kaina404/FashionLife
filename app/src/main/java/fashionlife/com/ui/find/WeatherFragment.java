package fashionlife.com.ui.find;

import fashionlife.com.R;
import fashionlife.com.base.component.BaseFragment;
import fashionlife.com.base.component.IPresenter;

/**
 * @author: lovexujh
 * @time: 2017/10/22
 * @descripition: 天气
 */

public class WeatherFragment extends BaseFragment {
    @Override
    protected IPresenter attachPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_weather;
    }
}
