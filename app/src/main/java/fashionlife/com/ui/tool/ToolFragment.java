package fashionlife.com.ui.tool;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import fashionlife.com.R;
import fashionlife.com.base.component.BaseFragment;
import fashionlife.com.base.component.IPresenter;
import fashionlife.com.common.UrlConstant;
import fashionlife.com.util.image.ImageLoadHelper;

/**
 * @author
 * Created by lovexujh on 2017/10/9
 */

public class ToolFragment extends BaseFragment {
    private ImageView mIvHead;

    @Override
    protected IPresenter attachPresenter() {
        return null;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mIvHead = (ImageView) view.findViewById(R.id.iv_head);
        ImageLoadHelper.getFLImageLoader().init(this).loadImage(mIvHead, UrlConstant.TOOL_FRAGMENT_HEAD_IMG_URL);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user_layout;
    }


}
