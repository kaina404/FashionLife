package com.myfashionlife.common.bean;

/**
 * @author: lovexujh
 * @time: 2017/10/29
 * @descripition:
 */

public class ShortcutBean {

    public String text;
    public int resId;


    public ShortcutBean(int resId, String text) {
        this.text = text;
        this.resId = resId;
    }
}
