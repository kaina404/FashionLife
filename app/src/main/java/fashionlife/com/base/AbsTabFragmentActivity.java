package fashionlife.com.base;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;

import fashionlife.com.manager.FragmentManager;
import fashionlife.com.manager.FragmentRecord;

/**
 * Created by lovexujh on 2017/10/9
 */

public abstract class AbsTabFragmentActivity extends BaseActivity {

    private ArrayList<AbsBaseFragment> fragmentList;
    private int fragmentIndex = -1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFragment();
    }

    private void initFragment() {
        if (getLayoutId() <= 0) {
            throw new IllegalArgumentException("error layout id !");
        }
        fragmentList = new ArrayList<>();
        for (int i = 0; i < getFragments().length; i++) {
            FragmentRecord fragmentRecord = FragmentManager.getInstance().getFragmentRecord(getFragments()[i]);
            if (fragmentRecord == null) {
                break;
            }
            Class<? extends AbsBaseFragment> fragmentClz = fragmentRecord.getFragmentClz();
            if (fragmentClz == null) {
                break;
            }
            try {
                fragmentList.add(fragmentClz.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
                break;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                break;
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }

        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < fragmentList.size(); i++) {
            transaction.add(getContainerViewId(), fragmentList.get(i), getFragments()[i]).hide(fragmentList.get(i));
        }
        if (!isFinishing()) {
            transaction.commitAllowingStateLoss();
        }
    }

    protected abstract int getContainerViewId();

    public abstract String[] getFragments();

    public boolean showFragment(int page) {
        if (page != fragmentIndex && page < fragmentList.size()) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            for (int i = 0; i < fragmentList.size(); i++) {
                if (fragmentList.get(i) != null) {
                    transaction.hide(fragmentList.get(i));
                }
            }
            transaction.show(fragmentList.get(page));
            if (!isFinishing()) {
                transaction.commitAllowingStateLoss();
                fragmentIndex = page;
                return true;
            }
        } else {
            return true;
        }
        return false;
    }

}
