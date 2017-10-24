package fashionlife.com.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fashionlife.com.R;
import fashionlife.com.ui.find.data.WeatherBean;
import fashionlife.com.util.ScreenUtils;
import fashionlife.com.util.Utils;

/**
 * @author: lovexujh
 * @time: 2017/10/24
 * @descripition:
 */

public class WeatherView extends View {
    private Paint mPaint;
    private List<PointF> mDayPointFs;
    private List<PointF> mNightPointFs;
    private List<ControlPoint> mNightControlPoints;
    private Path mPath;
    private int mMax;
    private int mMin;
    private int mItemPadding;
    private Paint mTxtPaint;
    private int mPaintColor;
    private float mItemLength;
    private List<String> mWeeks;
    private float smoothness = 0.16f;//贝塞尔曲线多个点之间的光滑因子，建议为0.16

    private int mViewHeight;
    private int mDayLinePaddingTop = 400;
    private int mTemperatureLinesHeight = 300;
    private int mViewBottom = 100;
    private int mTxtPaddingTop = 15;
    private List<String> mDates;
    private List<String> mDayWeathers;
    private List<String> mNightWeathers;
    private List<String> mWinds;
    private List<String> mWindLevels;
    private float mAscent;
    private float mTitleTxtPaddingTopBottom = 10;
    private float mTxtLength;
    private Paint mImgPaint;
    private List<PointF> mDayTemperatures;
    private List<PointF> mNightTemperatures;
    private int mViewWidth;
    private ControlPoint controlPoint = new ControlPoint();
    private List<ControlPoint> mDayControlPoints;
    private LinearGradient mLinearGradient;
    private HashMap<String, Bitmap> mWeatherBitmapMap;
    private Bitmap mDefaultWeatherBitmap;
    private int mDefaultWeatherBitmapHeight;


    //http://blog.csdn.net/it_zouxiang/article/details/52667896

    public WeatherView(Context context) {
        this(context, null);
    }

    public WeatherView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WeatherView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaintColor = Color.WHITE;
        mImgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(5f);
        mPaint.setShader(mLinearGradient);

        mPath = new Path();


        // 周一
        mTxtPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTxtPaint.setColor(mPaintColor);
        mTxtPaint.setTextSize(ScreenUtils.spToPx(15, getContext()));
        mTxtPaint.setStrokeWidth(1.0f);
        mTxtPaint.setTextAlign(Paint.Align.CENTER);
        mAscent = mTxtPaint.getFontMetrics().ascent;
        mTxtLength = mTxtPaint.measureText("周一");
        mItemPadding = 80;//
        mItemLength = mItemPadding * 2 + mTxtLength;


        initData();
        updateView();

        initLinearGradient();
        mViewHeight = (int) (
                mTxtPaddingTop * 3 + mDayLinePaddingTop + mTemperatureLinesHeight +
                        mDefaultWeatherBitmapHeight + mDefaultWeatherBitmapHeight -
                        mAscent - mAscent - mAscent +
                        mViewBottom);

    }

    private void initLinearGradient() {
        mLinearGradient = new LinearGradient(
                0,
                mDayLinePaddingTop,
                0,
                mDayLinePaddingTop + mTemperatureLinesHeight,
                new int[]{
                        getResources().getColor(R.color.colorAccent),
                        getResources().getColor(R.color.orange),
                        getResources().getColor(R.color.white)},
                null,
                Shader.TileMode.CLAMP
        );
    }

    private void initData() {
        mDayTemperatures = new ArrayList<>();
        mNightTemperatures = new ArrayList<>();
        mDayWeathers = new ArrayList<>();
        mWeeks = new ArrayList<>();
        mDates = new ArrayList<>();
        mNightWeathers = new ArrayList<>();
        mWinds = new ArrayList<>();
        mWindLevels = new ArrayList<>();
    }

    public void updateView() {
        //多云,少云,晴,阴,小雨,雨,雷阵雨,中雨,阵雨,零散阵雨,零散雷雨,小雪,雨夹雪,阵雪,霾
        String[] weatherConditions = new String[]{
                "多云", "少云", "晴",
                "阴", "小雨", "雨",
                "雷阵雨", "中雨", "阵雨",
                "零散阵雨", "零散雷雨", "小雪",
                "雨夹雪", "阵雪", "霾"};
        mWeatherBitmapMap = initWeatherBitmap(weatherConditions,
                new Integer[]{
                        R.mipmap.duoyun, R.mipmap.duoyun, R.mipmap.duoyun,
                        R.mipmap.duoyun, R.mipmap.duoyun, R.mipmap.duoyun,
                        R.mipmap.duoyun, R.mipmap.duoyun, R.mipmap.duoyun,
                        R.mipmap.duoyun, R.mipmap.duoyun, R.mipmap.duoyun,
                        R.mipmap.duoyun, R.mipmap.duoyun, R.mipmap.duoyun});
        //解析默认图片
        Matrix matrix = new Matrix();
        matrix.reset();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        float sx = mTxtLength / bitmap.getWidth();
        matrix.postScale(sx, sx);
        mDefaultWeatherBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        mDefaultWeatherBitmapHeight = mDefaultWeatherBitmap.getHeight();
    }

    public HashMap<String, Bitmap> initWeatherBitmap(String[] weatherConditions, Integer[] weatherResources) {
        Matrix matrix = new Matrix();

        HashMap<String, Bitmap> bitmapMap = new HashMap<>();
        //15
        for (int i = 0; i < weatherResources.length; i++) {
            matrix.reset();
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), weatherResources[i]);
            float sx = mTxtLength / bitmap.getWidth();
            matrix.postScale(sx, sx);
            bitmapMap.put(weatherConditions[i], Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true));
        }
        return bitmapMap;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(
                View.MeasureSpec.makeMeasureSpec(mViewWidth, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(mViewHeight, View.MeasureSpec.EXACTLY));
    }

    /**
     * 获取温度值中的最大和最小值
     *
     * @param pointFs
     * @param pointFs2
     */
    private void getMaxMinPoint(List<PointF> pointFs, List<PointF> pointFs2) {
        //获取最大最小值
        mMax = Integer.MIN_VALUE;
        mMin = Integer.MAX_VALUE;
        for (int i = 0; i < pointFs.size(); i++) {
            mMax = (int) Math.max(mMax, pointFs.get(i).y);
            mMin = (int) Math.min(mMin, pointFs.get(i).y);
        }

        for (int i = 0; i < pointFs2.size(); i++) {
            mMax = (int) Math.max(mMax, pointFs2.get(i).y);
            mMin = (int) Math.min(mMin, pointFs2.get(i).y);
        }
    }


    /**
     * 把一般坐标转为 Android中的视图坐标
     **/
    private List<PointF> changePoint(List<PointF> oldPointFs) {
        List<PointF> pointFs = new ArrayList<>();
        //间隔，减去某个值是为了空出多余空间，为了画线以外，还要写坐标轴的值，除以坐标轴最大值(这里设为定值)
        //相当于缩小图像
        int intervalX = mViewWidth / oldPointFs.size();
        int k = -mTemperatureLinesHeight / (mMax - mMin == 0 ? 1 : mMax - mMin);//斜率
        int b = mDayLinePaddingTop - k * mMax;
        PointF p;
        float x;
        float y;
        for (PointF pointF : oldPointFs) {
            //最后的正负值是左移右移
            x = pointF.x * intervalX + mItemLength / 2;
            //y = k * x + b
            y = k * pointF.y + b + 0;
            p = new PointF(x, y);
            pointFs.add(p);
        }
        return pointFs;
    }

    public void updateView(List<WeatherBean.ResultBean.FutureBean> future) {

        mDayTemperatures.clear();
        mNightTemperatures.clear();
        mWeeks.clear();
        mDates.clear();
        mDayWeathers.clear();
        mNightWeathers.clear();
        mWinds.clear();
        mWindLevels.clear();

        mViewWidth = (int) (future.size() * mItemLength);

        WeatherBean.ResultBean.FutureBean futureBean;
        for (int i = 0; i < future.size(); i++) {
            futureBean = future.get(i);
            String temperature = futureBean.getTemperature().trim();
            //handler temperature
            String[] split = temperature.replace("C", "").replace("°", "").split("/");
            String day;
            String night;
            if (split.length == 2) {
                day = split[0].trim();
                night = split[1].trim();
            } else {
                day = night = split[0].trim();
            }

            mDayTemperatures.add(new PointF(i, Float.valueOf(day)));
            mNightTemperatures.add(new PointF(i, Float.valueOf(night)));

            mWeeks.add(futureBean.getWeek().trim());
            String[] split1 = futureBean.getDate().split("-");
            mDates.add(split1[1] + "/" + split1[2]);
            if (Utils.isEmpty(futureBean.getDayTime())) {
                mDayWeathers.add(" ");
            } else {
                mDayWeathers.add(futureBean.getDayTime().trim());
            }
            if (Utils.isEmpty(futureBean.getNight())) {
                mNightWeathers.add(" ");
            } else {
                mNightWeathers.add(futureBean.getNight().trim());

            }

            String wind = futureBean.getWind();
            int index = wind.indexOf("风");
            mWinds.add(wind.substring(0, index + 1).trim());
            mWindLevels.add(wind.substring(index + 1).trim());
        }
        getMaxMinPoint(mDayTemperatures, mNightTemperatures);

        //输入点数转为实际坐标
        mDayPointFs = changePoint(mDayTemperatures);
        mNightPointFs = changePoint(mNightTemperatures);
        mDayControlPoints = controlPoint.getControlPointList(mDayPointFs);
        mNightControlPoints = controlPoint.getControlPointList(mNightPointFs);

        //重新绘制UI
        invalidate();
    }

    public class ControlPoint {
        private PointF conPoint1;
        private PointF conPoint2;


        public ControlPoint() {

        }

        public ControlPoint(PointF p1, PointF p2) {
            this.conPoint1 = p1;
            this.conPoint2 = p2;
        }

        public PointF getConPoint1() {
            return conPoint1;
        }

        public void setConPoint1(PointF conPoint1) {
            this.conPoint1 = conPoint1;
        }

        public PointF getConPoint2() {
            return conPoint2;
        }

        public void setConPoint2(PointF conPoint2) {
            this.conPoint2 = conPoint2;
        }

        public List<ControlPoint> getControlPointList(List<PointF> pointFs) {
            List<ControlPoint> controlPoints = new ArrayList<>();

            PointF p1;
            PointF p2;
            float conP1x;
            float conP1y;
            float conP2x;
            float conP2y;
            for (int i = 0; i < pointFs.size() - 1; i++) {

                if (i == 0) {
                    //第一断1曲线 控制点 new Point(p1.x + (p2.x - p1.x) * smoothness, p1.y)
                    conP1x = pointFs.get(i).x + (pointFs.get(i + 1).x - pointFs.get(i).x) * smoothness;
                    conP1y = pointFs.get(i).y + (pointFs.get(i + 1).y - pointFs.get(i).y) * smoothness;

                    conP2x = pointFs.get(i + 1).x - (pointFs.get(i + 2).x - pointFs.get(i).x) * smoothness;
                    conP2y = pointFs.get(i + 1).y - (pointFs.get(i + 2).y - pointFs.get(i).y) * smoothness;

                } else if (i == pointFs.size() - 2) {
                    //最后一段曲线 控制点
                    conP1x = pointFs.get(i).x + (pointFs.get(i + 1).x - pointFs.get(i - 1).x) * smoothness;
                    conP1y = pointFs.get(i).y + (pointFs.get(i + 1).y - pointFs.get(i - 1).y) * smoothness;

                    conP2x = pointFs.get(i + 1).x - (pointFs.get(i + 1).x - pointFs.get(i).x) * smoothness;
                    conP2y = pointFs.get(i + 1).y - (pointFs.get(i + 1).y - pointFs.get(i).y) * smoothness;
                } else {
                    conP1x = pointFs.get(i).x + (pointFs.get(i + 1).x - pointFs.get(i - 1).x) * smoothness;
                    conP1y = pointFs.get(i).y + (pointFs.get(i + 1).y - pointFs.get(i - 1).y) * smoothness;

                    conP2x = pointFs.get(i + 1).x - (pointFs.get(i + 2).x - pointFs.get(i).x) * smoothness;
                    conP2y = pointFs.get(i + 1).y - (pointFs.get(i + 2).y - pointFs.get(i).y) * smoothness;
                }

                p1 = new PointF(conP1x, conP1y);
                p2 = new PointF(conP2x, conP2y);

                ControlPoint controlPoint = new ControlPoint(p1, p2);
                controlPoints.add(controlPoint);
            }

            return controlPoints;
        }

    }


    @Override
    protected void onDraw(Canvas canvas) {

        //绘制天
        canvas.save();
        canvas.translate(0, -mAscent + mTitleTxtPaddingTopBottom);
        mTxtPaint.setTextSize(ScreenUtils.spToPx(15, getContext()));
        drawDefaultText(mWeeks, mTxtPaint, canvas);
        canvas.translate(0, -mAscent + mTitleTxtPaddingTopBottom);
        //绘制日期
        mTxtPaint.setTextSize(ScreenUtils.spToPx(10, getContext()));
        drawDefaultText(mDates, mTxtPaint, canvas);
        canvas.translate(0, -mAscent + mTitleTxtPaddingTopBottom);
        //绘制白天的天气
        mTxtPaint.setTextSize(ScreenUtils.spToPx(13, getContext()));
        drawDefaultText(mDayWeathers, mTxtPaint, canvas);
        canvas.restore();
        //绘制白天的天气图标
        drawWeatherIcon((-mAscent + mTitleTxtPaddingTopBottom) * 3 - mAscent, mDayWeathers, canvas);

        //绘制线
        canvas.save();
        mPath.reset();
        drawLine(mDayControlPoints, mDayPointFs, canvas, mPath, mPaint);
        mPath.reset();
        drawLine(mNightControlPoints, mNightPointFs, canvas, mPath, mPaint);
        //绘制每个点
        drawPoints(mDayPointFs, canvas, mPaint);
        drawPoints(mNightPointFs, canvas, mPaint);
        canvas.restore();

        //绘制线上面的温度数值
        canvas.save();
        drawTemperatures(mTxtPaint, mDayTemperatures, mDayPointFs, canvas, true);
        drawTemperatures(mTxtPaint, mNightTemperatures, mNightPointFs, canvas, false);
        canvas.restore();

        int height1 = mDayLinePaddingTop + mTemperatureLinesHeight + mDefaultWeatherBitmapHeight;
        //绘制晚上的天气图标
        drawWeatherIcon(height1, mNightWeathers, canvas);
        //绘制晚上的天气状况
        mTxtPaint.setTextSize(ScreenUtils.spToPx(13, getContext()));
        drawDefaultText(mNightWeathers, mTxtPaddingTop + height1 + mDefaultWeatherBitmapHeight - mAscent, mTxtPaint, canvas);
        //绘制晚上的风向
        mTxtPaint.setTextSize(ScreenUtils.spToPx(11, getContext()));
        drawDefaultText(mWinds, mTxtPaddingTop * 2 + height1 + mDefaultWeatherBitmapHeight - mAscent - mAscent, mTxtPaint, canvas);
        //绘制风的强度
        mTxtPaint.setTextSize(ScreenUtils.spToPx(9, getContext()));
        drawDefaultText(mWindLevels, mTxtPaddingTop * 3 + height1 + mDefaultWeatherBitmapHeight - mAscent - mAscent - mAscent, mTxtPaint, canvas);
    }

    /**
     * 绘制温度上面的数字
     *
     * @param paint
     * @param temperatures
     * @param location
     * @param canvas
     * @param day
     */
    public void drawTemperatures(Paint paint, List<PointF> temperatures, List<PointF> location, Canvas canvas, boolean day) {
        paint.setTextSize(ScreenUtils.spToPx(10, getContext()));
        for (int i = 0; i < temperatures.size(); i++) {
            canvas.drawText(String.valueOf(temperatures.get(i).y) + "°", location.get(i).x, (float) (location.get(i).y + (day ? mAscent / 2 : -mAscent * 1.2)), paint);// mAscent/2 代表上移一点位置
        }

    }

    /**
     * 绘制天气图标
     *
     * @param top
     * @param dayWeathers
     * @param canvas
     */
    private void drawWeatherIcon(float top, List<String> dayWeathers, Canvas canvas) {

        canvas.save();
        for (int i = 0; i < dayWeathers.size(); i++) {
            float x = (2 * i + 1) * mItemPadding + mTxtLength * i;
            String s = dayWeathers.get(i);
            if (Utils.isEmpty(s)) {
                continue;
            }
            Bitmap bitmap = mWeatherBitmapMap.get(s);
            if (bitmap == null) {
                continue;
            }
            canvas.drawBitmap(bitmap, x, top, mImgPaint);
        }
        canvas.restore();
    }

    /**
     * 绘制文字
     *
     * @param strings
     * @param paint
     * @param canvas
     */
    private void drawDefaultText(List<String> strings, Paint paint, Canvas canvas) {

        for (int i = 0; i < strings.size(); i++) {
            String date = strings.get(i);
            float x = mItemLength / 2 + mItemLength * i;
            canvas.drawText(date, x, 0, paint);
        }
    }


    /**
     * 绘制文字
     *
     * @param strings
     * @param y       文字距顶边的距离
     * @param paint
     * @param canvas
     */
    private void drawDefaultText(List<String> strings, float y, Paint paint, Canvas canvas) {

        for (int i = 0; i < strings.size(); i++) {
            String date = strings.get(i);
            if (Utils.isEmpty(date)) {
                continue;
            }
            float x = mItemLength / 2 + mItemLength * i;
            canvas.drawText(date, x, y, paint);
        }
    }

    /**
     * 绘制温度线上面的点
     *
     * @param pointFs
     * @param canvas
     * @param paint
     */
    private void drawPoints(List<PointF> pointFs, Canvas canvas, Paint paint) {
        paint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < pointFs.size(); i++) {
            canvas.drawCircle(pointFs.get(i).x, pointFs.get(i).y, 10f, paint);
        }
    }

    /**
     * 绘制温度曲线
     *
     * @param controlPoints
     * @param pointFs
     * @param canvas
     * @param path
     * @param paint
     */
    private void drawLine(List<ControlPoint> controlPoints, List<PointF> pointFs, Canvas canvas, Path path, Paint paint) {
        paint.setStyle(Paint.Style.STROKE);

        //贝塞尔曲线获取控制点
        for (int i = 0; i < controlPoints.size(); i++) {
            if (i == 0) {
                path.moveTo(pointFs.get(i).x, pointFs.get(i).y);
            }
            //画三阶贝塞尔曲线
            path.cubicTo(
                    controlPoints.get(i).getConPoint1().x, controlPoints.get(i).getConPoint1().y,
                    controlPoints.get(i).getConPoint2().x, controlPoints.get(i).getConPoint2().y,
                    pointFs.get(i + 1).x, pointFs.get(i + 1).y
            );
        }
        paint.setShader(mLinearGradient);
        canvas.drawPath(path, paint);
    }

}
