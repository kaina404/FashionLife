package com.myfashionlife.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author: lovexujh
 * @time: 2017/10/22
 * @descripition: 圆角线
 */

public class CircularBeadView extends View {

    private static final int AIR_CONDITION_INDEX_EXCELLENT = 50;
    private static final int AIR_CONDITION_INDEX_FINE = 100;
    private static final int AIR_CONDITION_INDEX_MILD_CONTAMINATION = 150;
    private static final int AIR_CONDITION_INDEX_MIDDLE_LEVEL_POLLUTION = 200;
    private static final int AIR_CONDITION_INDEX_SERIOUS_CONTAMINATION = 300;
    private Paint mPaint;
    private int mWidth;
    private RectF mRect;
    private int mHeight;
    private int mGreenColor;
    private int mYellowColor;
    private int mOrangeColor;
    private int mRedColor;
    //紫色
    private int mPurpleColor;
    private int mMaroonColor;

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
        mPaint.setStrokeWidth(20f);
        mRect = new RectF();
        mHeight = 20;

        mGreenColor = Color.parseColor("#00FF7F");
        mYellowColor = Color.parseColor("#FFFF00");
        mOrangeColor = Color.parseColor("#FF7F00");
        mRedColor = Color.parseColor("#FF0000");
        //紫色
        mPurpleColor = Color.parseColor("#871F78");
        //褐红色
        mMaroonColor = Color.parseColor("#8E236B");

        mPaint.setColor(Color.parseColor("#00FF7F"));
    }

    public void setColor(int airConditionIndex) {
        if (airConditionIndex <= AIR_CONDITION_INDEX_EXCELLENT) {
            mPaint.setColor(mGreenColor);
        } else if (airConditionIndex <= AIR_CONDITION_INDEX_FINE) {
            mPaint.setColor(mYellowColor);
        } else if (airConditionIndex <= AIR_CONDITION_INDEX_MILD_CONTAMINATION) {
            mPaint.setColor(mOrangeColor);
        } else if (airConditionIndex <= AIR_CONDITION_INDEX_MIDDLE_LEVEL_POLLUTION) {
            mPaint.setColor(mRedColor);
        } else if (airConditionIndex <= AIR_CONDITION_INDEX_SERIOUS_CONTAMINATION) {
            mPaint.setColor(mPurpleColor);
        } else {
            mPaint.setColor(mMaroonColor);
        }
        setVisibility(VISIBLE);
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
