package fashionlife.com.ui.home.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import fashionlife.com.R;
import fashionlife.com.app.AppUtils;
import fashionlife.com.base.viewholder.BaseViewHolder;
import fashionlife.com.ui.home.data.WXNewsBean;
import fashionlife.com.util.Utils;
import fashionlife.com.util.image.FLImageLoader;

/**
 * Created by lovexujh on 2017/10/15
 */

public class WXNewsViewHolder extends BaseViewHolder<WXNewsBean.ResultBean.ListBean> {
    private TextView mTvTitle;
    private ImageView mIv;

    public WXNewsViewHolder(Context context) {
        super(context);
    }

    @Override
    protected void initView(View itemView) {
        mTvTitle = (TextView) findViewById(R.id.item_wx_news_tv_title);
        mIv = (ImageView) findViewById(R.id.item_wx_news_iv);
    }

    @Override
    protected int getItemViewLayoutId() {
        return R.layout.item_wx_news_layout;
    }

    @Override
    public void setData(WXNewsBean.ResultBean.ListBean bean, int position, View convertView) {
        mTvTitle.setText(bean.getTitle());
        String url = AppUtils.getMobWXImgURL(bean.getThumbnails());
        if(Utils.isEmpty(url)){
            mIv.setVisibility(View.GONE);
        }else {
            mIv.setVisibility(View.VISIBLE);
            FLImageLoader.getFLImageLoader().init(mContext).loadImage(mIv, url);
        }

    }
}
