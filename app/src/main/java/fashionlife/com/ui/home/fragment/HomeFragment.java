package fashionlife.com.ui.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import fashionlife.com.R;
import fashionlife.com.base.component.AbsBaseFragment;
import fashionlife.com.util.image.FLImageLoader;

/**
 * Created by lovexujh on 2017/10/9
 */

public class HomeFragment extends AbsBaseFragment {

    private ImageView mIV;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }


    @Override
    public Object attachPresenter() {
        return null;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mIV = (ImageView) view.findViewById(R.id.iv);
    }

    @Override
    public void onResume() {
        super.onResume();
        FLImageLoader.getFLImageLoader().init(getActivity()).loadImage(mIV, "https://www.baidu.com/img/bd_logo1.png");
    }
}
