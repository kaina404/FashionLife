package fashionlife.com.base.viewholder;

import android.content.Context;
import android.view.View;

import fashionlife.com.base.data.FLBaseBean;

/**
 * Created by lovexujh on 2017/10/12
 */

public abstract class BaseViewHolder<T extends FLBaseBean> {

    protected Context mContext;
    protected View mItemView;

    public BaseViewHolder(Context context) {
        this.mContext = context;
        mItemView = View.inflate(mContext, getItemViewLayoutId(), null);
    }

    public View getItemView() {
        return mItemView;
    }

    protected abstract int getItemViewLayoutId();

    public abstract void setData(T bean, int position, View convertView);
}
