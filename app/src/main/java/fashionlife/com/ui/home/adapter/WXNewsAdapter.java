package fashionlife.com.ui.home.adapter;

import android.content.Context;

import java.util.List;

import fashionlife.com.base.adapter.FLBaseAdapter;
import fashionlife.com.ui.home.data.WXNewsBean;
import fashionlife.com.ui.home.viewholder.WXNewsViewHolder;

/**
 * Created by lovexujh on 2017/10/15
 */

public class WXNewsAdapter extends FLBaseAdapter<WXNewsBean.ResultBean.ListBean, WXNewsViewHolder> {


    public WXNewsAdapter(Context context, List<WXNewsBean.ResultBean.ListBean> beans) {
        super(context, beans);
    }

    @Override
    protected WXNewsViewHolder getBaseViewHolder() {
        return new WXNewsViewHolder(mContext);
    }

}
