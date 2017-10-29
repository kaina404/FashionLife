package fashionlife.com.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import fashionlife.com.R;
import fashionlife.com.base.component.BaseApplication;
import fashionlife.com.common.bean.ShortcutBean;
import fashionlife.com.util.Utils;

/**
 * @author: lovexujh
 * @time: 2017/10/29
 * @descripition:
 */

public class ShortCutPopWindow {


    private Animation mImageViewScaleAnimation;
    private Animation mContentViewScaleAnimation;
    private ViewGroup mDecorView;
    private PopupWindow mPopupWindow;
    private Activity mActivity;
    private OnItemClickListener mOnItemClickListener;

    public ShortCutPopWindow() {
        Context context = BaseApplication.getInstance().getApplicationContext();
        mImageViewScaleAnimation = AnimationUtils.loadAnimation(context, R.anim.scaleanim);
        mContentViewScaleAnimation = AnimationUtils.loadAnimation(context, R.anim.scaleanim2small);

    }


    public ShortCutPopWindow createList(Activity activity, int resId, List<ShortcutBean> mShortcutBeans) {
        mActivity = activity;
        mDecorView = (ViewGroup) activity.getWindow().getDecorView();

        final LinearLayout mContentView = getPopwindowContentView(mShortcutBeans);

        if (mContentView == null) {
            return this;
        }

        mPopupWindow = new PopupWindow(mContentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(activity.getResources(), (Bitmap) null));

        final ImageView imageView = new ImageView(activity);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.BOTTOM | Gravity.RIGHT;
        layoutParams.setMargins(0, 0, 30, 30);
        imageView.setLayoutParams(layoutParams);
        Glide.with(activity).load(resId).override(150, 150).into(imageView);
        mDecorView.addView(imageView);
        imageView.setClickable(true);
        mImageViewScaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(imageView == null){
                    return;
                }
                if (imageView.getVisibility() == View.VISIBLE) {
                    imageView.setVisibility(View.GONE);
                } else {
                    imageView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if(imageView == null){
                    return;
                }
                imageView.setVisibility(View.VISIBLE);
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.startAnimation(mImageViewScaleAnimation);
                mContentView.startAnimation(mContentViewScaleAnimation);
                mPopupWindow.showAtLocation(imageView, Gravity.BOTTOM | Gravity.RIGHT, 100, 100);

            }
        });

        return this;

    }

    private LinearLayout getPopwindowContentView(List<ShortcutBean> beans) {
        if (Utils.isEmpty(beans)) {
            return null;
        }

        LinearLayout linearLayout = new LinearLayout(mActivity);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(params);


        for (int i = 0; i < beans.size(); i++) {
            View itemView = LayoutInflater.from(mActivity).inflate(R.layout.layout_short_cut_item, null);
            ImageView iv = (ImageView) itemView.findViewById(R.id.iv);
            TextView tv = (TextView) itemView.findViewById(R.id.tv);
            ShortcutBean item = beans.get(i);
            Glide.with(mActivity).load(item.resId).override(100, 100).into(iv);
            tv.setText(item.text);
            itemView.setTag(i);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick((Integer) v.getTag());
                    }
                }
            });

            linearLayout.addView(itemView);

        }

        return linearLayout;

    }

    public interface OnItemClickListener {
        void onItemClick(int index);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

}
