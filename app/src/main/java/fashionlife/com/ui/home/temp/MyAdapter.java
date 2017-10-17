package fashionlife.com.ui.home.temp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Random;

import fashionlife.com.R;
import fashionlife.com.ui.home.data.WXNewsBean;
import fashionlife.com.util.ScreenUtils;
import fashionlife.com.util.Tool;

/**
 * Created by lovexujh on 2017/10/16
 */

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private final Context mContext;
    private List<WXNewsBean.ResultBean.ListBean> mData;
    private int mImgWidth;
    private int mScreenWidth;

    public MyAdapter(Context context) {
        this.mContext = context;
        mScreenWidth = ScreenUtils.getScreenWidth(mContext);
        mImgWidth = mScreenWidth / 2;
    }

    public void setData(List<WXNewsBean.ResultBean.ListBean> data) {
        this.mData = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder viewHolder = new MyViewHolder(View.inflate(mContext, R.layout.item_wxnews_layout, null));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final WXNewsBean.ResultBean.ListBean bean = mData.get(position);
        int imageHeight = bean.getImageHeight();

        if (imageHeight <= 0) {

            imageHeight = getRandomHeight();
            bean.setImageHeight(imageHeight);
//            LogUtil.d(this, position + " <= 0 " + imageHeight);
//            Glide.with(mContext).load(bean.getThumbnails()).asBitmap().placeholder(R.mipmap.loading).diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop().override(mImgWidth, SIZE_ORIGINAL).into(new SimpleTarget<Bitmap>() {
//                @Override
//                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//                    if (mData.get(position).getImageHeight() <= 0) {
//                        int height = resource.getHeight();
//                        mData.get(position).setImageHeight(height);
//                    }
//                    holder.mIv.setImageBitmap(resource);
//                }
//            });
        }
//        else {
//        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) holder.mIv.getLayoutParams();
//        params.height = imageHeight;
        Glide.with(mContext).load(bean.getThumbnails()).placeholder(R.mipmap.loading).override(mImgWidth, imageHeight).centerCrop().override(mImgWidth, imageHeight).into(holder.mIv);
//        }

        holder.mTv.setText(bean.getSubTitle());

        //设置监听
        if (null != mOnItemClickListener) {
            holder.mItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(holder.mItemView, position);
                }
            });
            holder.mItemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return mOnItemClickListener.onItemLongClick(holder.mItemView, position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        if (Tool.isEmpty(mData)) {
            return 0;
        }
        return mData.size();
    }

    public int getRandomHeight() {
        int max = 620;
        int min = 400;
        Random random = new Random();

        int s = random.nextInt(max) % (max - min + 1) + min;
        return s;
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        boolean onItemLongClick(View view, int position);
    }


    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickLitener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

}
