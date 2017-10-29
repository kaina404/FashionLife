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
import fashionlife.com.util.ScreenUtils;
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
        ScreenUtils.translucentTitle(this);
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
        //将图片调整到适合webview的大小
        webSettings.setUseWideViewPort(true);
        // 缩放至屏幕的大小
        webSettings.setLoadWithOverviewMode(true);
        /**
         * 缩放操作
         */
        //支持缩放，默认为true。是下面那个的前提。
        webSettings.setSupportZoom(true);
        //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setBuiltInZoomControls(true);
        //隐藏原生的缩放控件
        webSettings.setDisplayZoomControls(false);
        //设置缓存
        if (NetStatusUtil.isNetworkConnected(getApplicationContext())) {
            //根据cache-control决定是否从网络上取数据。
            webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        } else {
            //没网，则从本地获取，即离线加载
            webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
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

}
