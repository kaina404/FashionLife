package com.myfashionlife.ui.home.temp;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.request.target.BitmapImageViewTarget;

/**
 * Created by lovexujh on 2017/10/16
 */

public class DriverViewTarget extends BitmapImageViewTarget {

    private int mImgWidth;
    private ImageView mIv;

    public DriverViewTarget(ImageView view, int imgWidth) {
        super(view);
        mIv = view;
        mImgWidth = imgWidth;
    }

//    @Override
//    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
////        int viewWidth = mIv.getWidth();
////        float scale = resource.getWidth() / (viewWidth * 1.0f);
////        int viewHeight = (int) (resource.getHeight() * scale);
//        setCardViewLayoutParams(mImgWidth, resource.getHeight());
//        super.onResourceReady(resource, glideAnimation);
//    }

//    @Override
//    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//        if(holder.getAdapterPosition() != RecyclerView.NO_POSITION) {
//            if (mData.get(holder.getAdapterPosition()).getHeight() <= 0) {
//                int width = resource.getWidth();
//                int height = resource.getHeight();
//                int realHeight = screenWidth * height / width / 2;
//                mData.get(holder.getAdapterPosition()).setHeight(realHeight);
//                ViewGroup.LayoutParams lp = holder.mIvMm.getLayoutParams();
//                lp.height = realHeight;
//                if(width < screenWidth / 2)
//                    lp.width = screenWidth / 2;
//            }
//            holder.mIvMm.setImageBitmap(resource);
//        }
//    }

    private void setCardViewLayoutParams(int width, int height) {
        ViewGroup.LayoutParams layoutParams = mIv.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = height;
        mIv.setLayoutParams(layoutParams);
    }
}
