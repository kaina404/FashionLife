package fashionlife.com.test;

import android.os.Bundle;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

import fashionlife.com.R;
import fashionlife.com.base.component.BaseActivity;
import fashionlife.com.base.ui.FLListView;

/**
 * Created by lovexujh on 2017/9/19
 */

public class TestActivity extends BaseActivity<TestView, TestPresenter> implements TestView {

    private FLListView mListView;
    private TestAdapter mAdapter;
    private List<TestBean.ResultBean> mDatas;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListView = (FLListView) findViewById(R.id.lv);
        mDatas = new ArrayList<>();
        mAdapter = new TestAdapter(this, mDatas);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public TestPresenter attachPresenter() {
        return new TestPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.requestData();
    }

    @Override
    public void onSuccess(String result) {
        TestBean testBean = JSON.parseObject(result, TestBean.class);
//        List<TestBean.ResultBean.ChildsBeanX> childs = testBean.getResult().getChilds();
//        List<TestBean.ResultBean.ChildsBeanX.ChildsBean> beanList = childs.get(0).getChilds();
        mDatas.clear();
        mDatas.addAll(testBean.getResult());
        mAdapter.notifyDataSetChanged();
//        mAdapter.setData(mDatas);
    }

    @Override
    public void onFailed(String reason) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}
