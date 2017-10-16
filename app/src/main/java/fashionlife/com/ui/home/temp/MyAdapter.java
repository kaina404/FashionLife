package fashionlife.com.ui.home.temp;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.List;
import java.util.Random;

import fashionlife.com.R;
import fashionlife.com.ui.home.data.WXNewsBean;
import fashionlife.com.util.LogUtil;
import fashionlife.com.util.ScreenUtils;
import fashionlife.com.util.Tool;

import static com.bumptech.glide.request.target.Target.SIZE_ORIGINAL;

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

//    private HashMap<Integer, Integer> mHeightMap = new HashMap<>();

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
//        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.mIv.getLayoutParams();
//        layoutParams.width = mImgWidth;
        final WXNewsBean.ResultBean.ListBean bean = mData.get(position);
//
//        if (bean.getImageHeight() == 0) {
//            bean.setImageHeight(getRandomHeight());
//        }
//        layoutParams.height = bean.getImageHeight();


//        Glide.with(mContext).load(bean.getThumbnails()).asBitmap().centerCrop().override(layoutParams.width, BitmapImageViewTarget.SIZE_ORIGINAL).into(new DriverViewTarget(holder.mIv, mImgWidth));

        int imageHeight = bean.getImageHeight();
//        int imageHeight = 500;
        if (imageHeight <= 0) {
            LogUtil.d(this, position + " <= 0 " + imageHeight);
            Glide.with(mContext).load(bean.getThumbnails()).asBitmap().centerCrop().override(mImgWidth, SIZE_ORIGINAL).into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    if (holder.getPosition() != RecyclerView.NO_POSITION) {
                        if (bean.getImageHeight() <= 0) {
//                            int width = resource.getWidth();
                            int height = resource.getHeight();
//                            int realHeight = mScreenWidth * height / width / 2;
                            bean.setImageHeight(height);
//                            ViewGroup.LayoutParams lp = holder.mIv.getLayoutParams();
//                            lp.height = height;
//                            if (width < mScreenWidth / 2)
//                                lp.width = mScreenWidth / 2;
                        }
                        holder.mIv.setImageBitmap(resource);
                    }
                }
            });
        } else {
            LogUtil.d(this, position + " > 0 " + imageHeight);

            Glide.with(mContext).load(bean.getThumbnails()).asBitmap().centerCrop().override(mImgWidth, imageHeight).into(holder.mIv);
        }


//        Glide.with(mContext).load(bean.getThumbnails()).fitCenter().crossFade().into(holder.mIv);
        holder.mTv.setText(bean.getSubTitle());
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
}
