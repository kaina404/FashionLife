package fashionlife.com.ui.web;

import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

/**
 * @author
 * 辅助 WebView 处理 Javascript 的对话框,网站图标,网站标题等等。
 * Created by lovexujh on 2017/10/15
 */

public class BaseWebChromeClient extends WebChromeClient {

    private ProgressBar mProgressBar;

    public BaseWebChromeClient(ProgressBar progressBar) {
        mProgressBar = progressBar;
    }

    /**
     * web加载进度
     *
     * @param view
     * @param newProgress
     */
    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
        if(mProgressBar != null){
            mProgressBar.setProgress(newProgress);
            if(newProgress >= 100){
                mProgressBar.setVisibility(View.GONE);
            }else {
                mProgressBar.setVisibility(View.VISIBLE);
            }
        }
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
