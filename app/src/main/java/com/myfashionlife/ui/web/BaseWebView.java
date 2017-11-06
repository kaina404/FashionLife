package com.myfashionlife.ui.web;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 * Created by lovexujh on 2017/10/15
 */

public class BaseWebView extends WebView {
    public BaseWebView(Context context) {
        super(context);
    }

    public BaseWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
