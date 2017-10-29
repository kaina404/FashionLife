package fashionlife.com.util;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

/**
 * @author: lovexujh
 * @time: 2017/10/29
 * @descripition:
 */

public class SwipeBackController {

    private static final String TAG = SwipeBackController.class.getSimpleName();
    public static final int ANIMATION_DURATION = 1000;//默认动画时间
    public static final int DEFAULT_TOUCH_THRESHOLD = ScreenUtils.getScreenWidth(null) / 2;//默认开始滑动的位置距离左边缘的距离
    private int mScreenWidth;
    private int mTouchSlop;

    private boolean isMoving = false;
    private float mInitX;
    private float mInitY;

    /**
     * 窗口根布局
     */
    private ViewGroup mDecorView;
    /**
     * content布局
     */
    private ViewGroup mContentView;
    /**
     * 用户添加的布局
     */
    private ViewGroup mUserView;

    private ValueAnimator mAnimator;
    private VelocityTracker mVelTracker;

    public SwipeBackController(final Activity activity) {
        mScreenWidth = activity.getResources().getDisplayMetrics().widthPixels;
        mTouchSlop = ViewConfiguration.get(activity).getScaledTouchSlop();
        mDecorView = (ViewGroup) activity.getWindow().getDecorView();
        mDecorView.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
        mContentView = (ViewGroup) activity.findViewById(android.R.id.content);
        mUserView = (ViewGroup) mContentView.getChildAt(0);

        mAnimator = new ValueAnimator();
        mAnimator.setDuration(ANIMATION_DURATION);
        mAnimator.setInterpolator(new DecelerateInterpolator());
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int x = (Integer) valueAnimator.getAnimatedValue();
                if (x >= mScreenWidth) {
                    activity.finish();
                }

                handleView(x);
            }
        });
    }

    public void handleView(int x) {
        mUserView.setTranslationX(x);
    }

    public boolean processEvent(MotionEvent event) {
        getVelocityTracker(event);

        if (mAnimator.isRunning()) {
            return true;
        }

        int pointId = -1;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mInitX = event.getRawX();
                mInitY = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (!isMoving) {
                    float dx = Math.abs(event.getRawX() - mInitX);
                    float dy = Math.abs(event.getRawY() - mInitY);
                    if (dx > mTouchSlop && dx > dy && mInitX < DEFAULT_TOUCH_THRESHOLD) {
                        isMoving = true;
                    }
                }
                if (isMoving) {
                    handleView((int) ((int) event.getRawX() - mInitX));
                }

                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                int distance = (int) (event.getRawX() - mInitX);

                mVelTracker.computeCurrentVelocity(1000);
                //获取x方向上的速度
                float velocityX = mVelTracker.getXVelocity(pointId);

                if (isMoving && Math.abs(mUserView.getTranslationX()) >= 0) {
                    if (velocityX > 1000f || distance >= mScreenWidth / 4) {
                        mAnimator.setIntValues((int) event.getRawX(), mScreenWidth);
                    } else {
                        mAnimator.setIntValues((int) event.getRawX(), 0);
                    }
                    mAnimator.start();
                    isMoving = false;
                }

                mInitX = 0;
                mInitY = 0;

                recycleVelocityTracker();
                break;
        }
        return false;
    }

    /**
     * 获取速度追踪器
     *
     * @return
     */
    private VelocityTracker getVelocityTracker(MotionEvent event) {
        if (mVelTracker == null) {
            mVelTracker = VelocityTracker.obtain();
        }
        mVelTracker.addMovement(event);
        return mVelTracker;
    }

    /**
     * 回收速度追踪器
     */
    private void recycleVelocityTracker() {
        if (mVelTracker != null) {
            mVelTracker.clear();
            mVelTracker.recycle();
            mVelTracker = null;
        }
    }

}
