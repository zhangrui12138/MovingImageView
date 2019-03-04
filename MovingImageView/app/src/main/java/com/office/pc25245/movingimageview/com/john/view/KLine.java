package com.office.pc25245.movingimageview.com.john.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by pc252 on 2019/2/28.
 */

public class KLine extends View {
    private Paint KLinePaint;
    private static final int A_zhenfu = 20;
    private float W_zhouqi = 8;
    private static final int H_yzhou = 330;
    private static final int B_chuxiang = 90;
    private int ViewWidth = 0;
    private int ViewHeight = 0;
    private float[] D_Xs;
    private float[] D_Ys;
    private float[] D_Ysbianhu;
    int biuanhuaX = 0;
    private PaintFlagsDrawFilter myDrawFilter;
    public KLine(Context context) {
        super(context);
        init();
    }

    public KLine(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public KLine(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        KLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        KLinePaint.setColor(Color.parseColor("#aaFF0000"));
        myDrawFilter = new PaintFlagsDrawFilter(0,Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG);
    }

    private void computePoint(){
        int yOneInterval = D_Ys.length - biuanhuaX;
        // 使用System.arraycopy方式重新填充第一条波纹的数据
        System.arraycopy(D_Ys, biuanhuaX, D_Ysbianhu, 0, yOneInterval);
        System.arraycopy(D_Ys, 0, D_Ysbianhu, yOneInterval, biuanhuaX);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        biuanhuaX +=90;
        if(biuanhuaX > ViewWidth){
            biuanhuaX = 0;
        }
        computePoint();
        canvas.setDrawFilter(myDrawFilter);
        for(int j=0;j<ViewWidth;j++){
          canvas.drawLine(j,D_Ysbianhu[j],j,D_Ysbianhu[j]+5,KLinePaint);
        }

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        postInvalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d("zhangrui","onSizeChanged");
        ViewWidth = w;
        ViewHeight = h;
        D_Xs = new float[ViewWidth];
        D_Ys = new float[ViewWidth];
        D_Ysbianhu = new float[ViewWidth];
        W_zhouqi = (float) (2 * Math.PI / ViewWidth);
        for(int i = 0;i < ViewWidth ;i++ ){
            D_Xs[i]=i;
            D_Ys[i]=(float)(A_zhenfu * Math.sin( W_zhouqi * i  + B_chuxiang) + H_yzhou);
        }
    }
}
