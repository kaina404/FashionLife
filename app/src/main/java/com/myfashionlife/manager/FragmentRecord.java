package com.myfashionlife.manager;

import com.myfashionlife.base.component.BaseFragment;

/**
 * Created by lovexujh on 2017/10/9
 */

public class FragmentRecord {
    private final Class<? extends BaseFragment> fragmentClz;
    private final String title;

    public FragmentRecord(String title, Class<? extends BaseFragment> clz) {
        this.title = title;
        this.fragmentClz = clz;
    }

    public Class<? extends BaseFragment> getFragmentClz() {
        return fragmentClz;
    }

    public String getTitle() {
        return title;
    }
}
