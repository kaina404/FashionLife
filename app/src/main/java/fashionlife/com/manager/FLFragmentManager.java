package fashionlife.com.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fashionlife.com.base.component.BaseActivity;
import fashionlife.com.common.FragmentId;
import fashionlife.com.ui.find.fragment.WeatherFragment;
import fashionlife.com.ui.home.fragment.HomeFragment;
import fashionlife.com.ui.tool.ToolFragment;

/**
 * Created by lovexujh on 2017/10/9
 */

public class FLFragmentManager {

    private static List<BaseActivity> activityList;
    private static FLFragmentManager instance;
    private Map<String, FragmentRecord> fragmentRecordMap;


    public static FLFragmentManager getInstance() {
        if (instance == null) {
            synchronized (ActivityManager.class) {
                if (instance == null) {
                    instance = new FLFragmentManager();
                    activityList = new ArrayList<>();
                }
            }
        }
        return instance;
    }

    public FLFragmentManager() {
        fragmentRecordMap = new HashMap<>();
        initData();
    }

    private void initData() {
        fragmentRecordMap.put(FragmentId.HOME, new FragmentRecord("", HomeFragment.class));
        fragmentRecordMap.put(FragmentId.WEATHER, new FragmentRecord("", WeatherFragment.class));
        fragmentRecordMap.put(FragmentId.TOOL, new FragmentRecord("", ToolFragment.class));
    }


    public FragmentRecord getFragmentRecord(String activityId) {
        if (fragmentRecordMap == null) {
            return null;
        } else {
            return fragmentRecordMap.get(activityId);
        }
    }
}
