package fashionlife.com.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import fashionlife.com.R;
import fashionlife.com.util.Utils;

/**
 * @author: lovexujh
 * @time: 2017/10/22
 * @descripition: 圆角线
 */

public class CircularBeadView extends View {

    private Paint mPaint;
    private int mWidth;
    private RectF mRect;
    private int mHeight;

    public CircularBeadView(Context context) {
        this(context, null);
    }

    public CircularBeadView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircularBeadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Utils.getColor(R.color.c_ECC350));
        mPaint.setStrokeWidth(20f);
        mRect = new RectF();
        mHeight = 20;
    }

    public void setColor(@ColorRes int colorRes) {
        mPaint.setColor(Utils.getColor(colorRes));
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.AT_MOST) {
            mWidth = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft() - getPaddingRight();
        }
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(mHeight, MeasureSpec.getMode(MeasureSpec.EXACTLY));
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mRect.set(0, 0, mWidth, mHeight);
        canvas.drawRoundRect(mRect, 7.0f, 7.0f, mPaint);
    }
}
