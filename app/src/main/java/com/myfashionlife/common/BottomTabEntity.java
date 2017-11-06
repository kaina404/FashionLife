package com.myfashionlife.common;

import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;

/**
 * @author: lovexujh
 * @time: 2017/10/27
 * @descripition:
 */

public class BottomTabEntity {
    private @DrawableRes int mCheckedIconRes;
    private @DrawableRes int mNoCheckIconRes;
    private String mCheckedIconUrl;
    private String mNoCheckIconUrl;
    private String mText;
    private @ColorRes int mCheckedTxtColor;
    private @ColorRes int mNoCheckTxtColor;

    public BottomTabEntity(int checkedIconRes, int noCheckIconRes, String text, int checkedTxtColor, int noCheckTxtColor) {
        mCheckedIconRes = checkedIconRes;
        mNoCheckIconRes = noCheckIconRes;
        mText = text;
        mCheckedTxtColor = checkedTxtColor;
        mNoCheckTxtColor = noCheckTxtColor;
    }


    public int getCheckedTxtColor() {
        return mCheckedTxtColor;
    }

    public void setCheckedTxtColor(int checkedTxtColor) {
        mCheckedTxtColor = checkedTxtColor;
    }

    public int getNoCheckTxtColor() {
        return mNoCheckTxtColor;
    }

    public void setNoCheckTxtColor(int noCheckTxtColor) {
        mNoCheckTxtColor = noCheckTxtColor;
    }

    public String getCheckedIconUrl() {
        return mCheckedIconUrl;
    }

    public void setCheckedIconUrl(String checkedIconUrl) {
        mCheckedIconUrl = checkedIconUrl;
    }

    public String getNoCheckIconUrl() {
        return mNoCheckIconUrl;
    }

    public void setNoCheckIconUrl(String noCheckIconUrl) {
        mNoCheckIconUrl = noCheckIconUrl;
    }

    public int getNoCheckIconRes() {
        return mNoCheckIconRes;
    }

    public void setNoCheckIconRes(int noCheckIconRes) {
        mNoCheckIconRes = noCheckIconRes;
    }


    public int getCheckedIconRes() {
        return mCheckedIconRes;
    }

    public void setCheckedIconRes(@DrawableRes int checkedIconRes) {
        mCheckedIconRes = checkedIconRes;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }
}
