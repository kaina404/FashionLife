package fashionlife.com.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;

import fashionlife.com.R;
import fashionlife.com.base.component.BaseActivity;
import fashionlife.com.common.IntentKeys;
import fashionlife.com.util.SwipeBackController;
import fashionlife.com.util.Utils;
import fashionlife.com.util.image.ImageLoadHelper;
import fashionlife.com.widget.ZoomImageView;

/**
 * @author
 */
public class ZoomImageActivity extends BaseActivity {


    private ZoomImageView mIv;
    private SwipeBackController mSwipeBackController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIv = (ZoomImageView) findViewById(R.id.iv);
        handlerIntent(getIntent());
        mSwipeBackController = new SwipeBackController(this);
    }

    private void handlerIntent(Intent intent) {
        String url = intent.getStringExtra(IntentKeys.IMG_URL);
        if (!Utils.isEmpty(url)) {
            ImageLoadHelper.getFLImageLoader().init(this).loadImage(mIv, url);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_zoom_image;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (mSwipeBackController.processEvent(ev)) {
            return true;
        } else {
            return super.dispatchTouchEvent(ev);
        }
    }

}
