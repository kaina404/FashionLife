package fashionlife.com.test;

import android.content.Context;

import java.util.List;

import fashionlife.com.base.adapter.FLBaseAdapter;
import fashionlife.com.base.viewholder.BaseViewHolder;

/**
 * Created by lovexujh on 2017/10/12
 */

public class TestAdapter extends FLBaseAdapter<TestBean.ResultBean> {

    public TestAdapter(Context context, List<TestBean.ResultBean> beans) {
        super(context, beans);
    }

    @Override
    protected BaseViewHolder getBaseViewHolder() {
        return new TestViewHolder(mContext);
    }

}
