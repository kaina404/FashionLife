package fashionlife.com.ui.web;

import android.graphics.Bitmap;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * 辅助 WebView 处理 Javascript 的对话框,网站图标,网站标题等等。
 * Created by lovexujh on 2017/10/15
 */

public class BaseWebChromeClient extends WebChromeClient {

    /**
     * web加载进度
     *
     * @param view
     * @param newProgress
     */
    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
    }

    @Override
    public void onReceivedIcon(WebView view, Bitmap icon) {
        super.onReceivedIcon(view, icon);// TODO: 2017/10/15  设置icon
    }

    /**
     * 获取Web页中的标题
     *
     * @param view
     * @param title
     */
    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);// TODO: 2017/10/15  设置显示的标题
    }
}
