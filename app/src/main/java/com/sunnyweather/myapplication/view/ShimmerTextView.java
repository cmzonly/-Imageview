package com.sunnyweather.myapplication.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

/*
 *  @项目名：  My Application
 *  @包名：    com.sunnyweather.myapplication.view
 *  @文件名:   ShimmerTextView
 *  @创建者:   lenovo
 *  @创建时间:  2021/7/10 0:25
 *  @描述：    TODO
 */
public class ShimmerTextView extends AppCompatTextView {

    //渲染器
    private LinearGradient mLinearGradient;
    //渲染范围
    private Matrix mGradientMatrix;
    //渲染的起始位置
    private int mViewWidth = 0;
    //渲染的终止距离
    private int mTranslate = 0;
    //是否启动动画
    private boolean mAnimating = true;
    //多少毫秒刷新一次
    private int speed = 50;
    private Paint mPaint;

    public ShimmerTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = getPaint();
        mGradientMatrix = new Matrix();
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mViewWidth = getMeasuredWidth();
        mLinearGradient = new LinearGradient(0, 0, mViewWidth, 0, new int[] { Color.GRAY, Color.WHITE, Color.GRAY }, null, Shader.TileMode.CLAMP);
        mPaint.setShader(mLinearGradient);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mAnimating && mGradientMatrix != null) {
            mTranslate += mViewWidth / 10;//每次移动屏幕的1/10宽
            if (mTranslate > 2 * mViewWidth) {
                mTranslate = -mViewWidth;
            }
            mGradientMatrix.setTranslate(mTranslate, 0);
            mLinearGradient.setLocalMatrix(mGradientMatrix);//在指定矩阵上渲染
            postInvalidateDelayed(speed);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

}



