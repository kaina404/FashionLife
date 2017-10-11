package fashionlife.com.manager;

import fashionlife.com.base.AbsBaseFragment;

/**
 * Created by lovexujh on 2017/10/9
 */

public class FragmentRecord {
    private final Class<? extends AbsBaseFragment> fragmentClz;
    private final String title;

    public FragmentRecord(String title, Class<? extends AbsBaseFragment> clz) {
        this.title = title;
        this.fragmentClz = clz;
    }

    public Class<? extends AbsBaseFragment> getFragmentClz() {
        return fragmentClz;
    }

    public String getTitle() {
        return title;
    }
}
