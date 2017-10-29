package fashionlife.com.ui.web;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.widget.LinearLayout;

import fashionlife.com.R;
import fashionlife.com.app.AppUtils;
import fashionlife.com.base.component.BaseActivity;
import fashionlife.com.common.ActivityId;
import fashionlife.com.common.CommonConstant;
import fashionlife.com.common.IntentKeys;
import fashionlife.com.manager.StartManager;
import fashionlife.com.util.NetStatusUtil;
import fashionlife.com.util.SwipeBackController;

/**
 * @author
 */
public class WebViewActivity extends BaseActivity {


    private BaseWebView mWebView;
    private LinearLayout mRootView;
    private BaseWebViewClient mWebViewClient;
    private BaseWebChromeClient mWebChromeClient;
    private SwipeBackController mSwipeBackController;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWebView = getWebView();
        mRootView = (LinearLayout) findViewById(R.id.root_view);
        mRootView.addView(mWebView);
        initWebSettings();
        handlerIntent(getIntent());
        if (null != savedInstanceState) {
            mWebView.restoreState(savedInstanceState);
        }

        mSwipeBackController = new SwipeBackController(this);
    }

    private void handlerIntent(Intent intent) {
        if (null != intent) {
            String url = intent.getStringExtra(IntentKeys.URL);
            mWebView.loadUrl(url);
        }
    }

    private BaseWebView getWebView() {
        BaseWebView webView = new BaseWebView(getApplicationContext());
        mWebViewClient = new BaseWebViewClient();
        mWebChromeClient = new BaseWebChromeClient();
        webView.setWebViewClient(mWebViewClient);
        webView.setWebChromeClient(mWebChromeClient);
        webView.removeJavascriptInterface("searchBoxJavaBridge_");
        webView.removeJavascriptInterface("accessibility");
        webView.removeJavascriptInterface("accessibilityTraversal");
        webView.addJavascriptInterface(new WebViewJsInterface() {

            @JavascriptInterface
            @Override
            public void onClickImg(String url) {
                Intent intent = new Intent();
                intent.putExtra(IntentKeys.IMG_URL, url);
                StartManager.startActivity(ActivityId.ZOOM_IMAGE_ACTIVITY, WebViewActivity.this, intent);
            }

        }, CommonConstant.JS_IMG_LISTENER);
        return webView;
    }

    public interface WebViewJsInterface {
        void onClickImg(String url);
    }

    private void initWebSettings() {
        //声明WebSettings子类

        WebSettings webSettings = mWebView.getSettings();

        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        //缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件
        //设置缓存
        if (NetStatusUtil.isNetworkConnected(getApplicationContext())) {
            webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);//根据cache-control决定是否从网络上取数据。
        } else {
            webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//没网，则从本地获取，即离线加载
        }

        webSettings.setDomStorageEnabled(true); // 开启 DOM storage API 功能
        webSettings.setDatabaseEnabled(true);   //开启 database storage API 功能
        webSettings.setAppCacheEnabled(true);//开启 Application Caches 功能
        String cacheDirPath = getFilesDir().getAbsolutePath() + AppUtils.APP_CACHE_DIRNAME;
        webSettings.setAppCachePath(cacheDirPath); //设置  Application Caches 缓存目录

        //其他细节操作
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mWebView != null) {
            mWebView.onResume();
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (null != mWebView) {
            mWebView.onPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mWebView) {
            clearCache();
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mRootView.removeView(mWebView);
            mWebView.destroy();
            mWebView = null;
        }
    }

    /**
     * 清空浏览的缓存
     */
    public void clearCache() {
        if (null != mWebView) {
            mWebView.clearCache(true);
            mWebView.clearHistory();
            mWebView.clearFormData();
        }
    }


    @Override
    public void onBackPressed() {
        if (null != mWebView && mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (null != mWebView) {
            mWebView.saveState(outState);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (mSwipeBackController.processEvent(ev)) {
            return true;
        } else {
            return super.dispatchTouchEvent(ev);
        }
    }


    //
//    //手指上下滑动时的最小速度
//    private static final int YSPEED_MIN = 1000;
//
//    //手指向右滑动时的最小距离
//    private static final int XDISTANCE_MIN = 50;
//
//    //手指向上滑或下滑时的最小距离
//    private static final int YDISTANCE_MIN = 100;
//
//    //记录手指按下时的横坐标。
//    private float xDown;
//
//    //记录手指按下时的纵坐标。
//    private float yDown;
//
//    //记录手指移动时的横坐标。
//    private float xMove;
//
//    //记录手指移动时的纵坐标。
//    private float yMove;
//
//    //用于计算手指滑动的速度。
//    private VelocityTracker mVelocityTracker;
//
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent event) {
//        createVelocityTracker(event);
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                xDown = event.getRawX();
//                yDown = event.getRawY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                xMove = event.getRawX();
//                yMove = event.getRawY();
//                //滑动的距离
//                int distanceX = (int) (xMove - xDown);
//                int distanceY = (int) (yMove - yDown);
//                //获取顺时速度
//                int ySpeed = getScrollVelocity();
//                //关闭Activity需满足以下条件：
//                //1.x轴滑动的距离>XDISTANCE_MIN
//                //2.y轴滑动的距离在YDISTANCE_MIN范围内
//                //3.y轴上（即上下滑动的速度）<XSPEED_MIN，如果大于，则认为用户意图是在上下滑动而非左滑结束Activity
//                if (distanceX > XDISTANCE_MIN && (distanceY < YDISTANCE_MIN && distanceY > -YDISTANCE_MIN) && ySpeed < YSPEED_MIN) {
//                    finish();
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//                recycleVelocityTracker();
//                break;
//            default:
//                break;
//        }
//        return super.dispatchTouchEvent(event);
//    }
//
//    /**
//     * 创建VelocityTracker对象，并将触摸界面的滑动事件加入到VelocityTracker当中。
//     *
//     * @param event
//     */
//    private void createVelocityTracker(MotionEvent event) {
//        if (mVelocityTracker == null) {
//            mVelocityTracker = VelocityTracker.obtain();
//        }
//        mVelocityTracker.addMovement(event);
//    }
//
//    /**
//     * 回收VelocityTracker对象。
//     */
//    private void recycleVelocityTracker() {
//        mVelocityTracker.recycle();
//        mVelocityTracker = null;
//    }
//
//    /**
//     * @return 滑动速度，以每秒钟移动了多少像素值为单位。
//     */
//    private int getScrollVelocity() {
//        mVelocityTracker.computeCurrentVelocity(1000);
//        int velocity = (int) mVelocityTracker.getYVelocity();
//        return Math.abs(velocity);
//    }


}
