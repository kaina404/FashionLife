package fashionlife.com.ui.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;

import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import fashionlife.com.R;
import fashionlife.com.app.APPConstant;
import fashionlife.com.app.AppUtils;
import fashionlife.com.base.component.BaseFragment;
import fashionlife.com.base.ui.BaseListView;
import fashionlife.com.common.ActivityId;
import fashionlife.com.common.IntentKeys;
import fashionlife.com.db.CacheDb;
import fashionlife.com.manager.StartManager;
import fashionlife.com.ui.home.adapter.WXNewsAdapter;
import fashionlife.com.ui.home.data.WXNewsBean;
import fashionlife.com.ui.home.impl.WXNewsContract;
import fashionlife.com.ui.home.impl.WXNewsPresenter;
import fashionlife.com.util.LogUtil;

/**
 * Created by lovexujh on 2017/10/15
 */

public class WXNewsFragment extends BaseFragment<WXNewsPresenter> implements WXNewsContract.View, BaseRefreshListener, AdapterView.OnItemClickListener {

    private BaseListView mListView;
    private String mCid = "1";//查询新闻的cid
    private WXNewsAdapter mAdapter;
    private List<WXNewsBean.ResultBean.ListBean> mDatas;
    private PullToRefreshLayout mPullToRefresh;
    private int mPager;
    private boolean mCanLoadMore = true;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDatas = new ArrayList<>();
        mPullToRefresh = (PullToRefreshLayout) view.findViewById(R.id.pull_to_refresh);
        mPullToRefresh.setRefreshListener(this);
//        mPullToRefresh.setHeaderView();

        mAdapter = new WXNewsAdapter(getContext(), mDatas);
        mListView = (BaseListView) view.findViewById(R.id.lv);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
        initData();
    }

    private void initData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            mCid = bundle.getString(APPConstant.WX_NEWS_CID, "1");
            mPager = 1;
            mPresenter.queryWXNews(mCid, mPager);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }


    @Override
    protected WXNewsPresenter attachPresenter() {
        return new WXNewsPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wx_news_layout;
    }


    @Override
    public void onError(String msg) {
        mCanLoadMore = false;
    }

    @Override
    public void onRefresh(WXNewsBean.ResultBean result) {
        if (result.getList() == null) {
            return;
        }
        mCanLoadMore = result.getList().size() >= APPConstant.WXNews.SIZE_20;
        mPullToRefresh.setCanLoadMore(mCanLoadMore);
        mPullToRefresh.finishRefresh();
        mPullToRefresh.finishLoadMore();
        if (result.getCurPage() == 1) {
            mDatas.clear();
        }
        mDatas.addAll(result.getList());
        mAdapter.notifyDataSetChanged();

        // TODO: 2017/10/15  TEST
        CacheDb cacheDb = new CacheDb();
        String cache = cacheDb.getCache(AppUtils.getWXNewsKey(mCid, mPager));
        LogUtil.d(this, "缓存是=", cache);
    }

    @Override
    public void refresh() {//下拉刷新时
        mPager = 1;
        mPresenter.queryWXNews(mCid, mPager);
    }

    @Override
    public void loadMore() {//上拉加载时
        if (mCanLoadMore) {
            mPresenter.queryWXNews(mCid, ++mPager);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        WXNewsBean.ResultBean.ListBean listBean = mDatas.get(position);
        Intent intent = new Intent();
        intent.putExtra(IntentKeys.URL, listBean.getSourceUrl());
        StartManager.startActivity(ActivityId.WEB_VIEW_ACTIVITY, getContext(), intent);
    }
}
