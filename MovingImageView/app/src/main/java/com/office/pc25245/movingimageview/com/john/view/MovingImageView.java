package com.office.pc25245.movingimageview.com.john.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.office.pc25245.movingimageview.R;

/**
 * Created by pc252 on 2019/2/21.
 */

public class MovingImageView extends View {
    private Paint MovingImageViewPaint;
    private int Speed = 10;
    private int viewWidth=0;
    private int viewHeight=0;
    private int currentYPosition = 0;
    private Bitmap showPic;
    private Context baseContext;
    private BitmapDrawable temper;
    public MovingImageView(Context context) {
        super(context);
        init(context);
    }

    public MovingImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MovingImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    private void init(Context context){
        MovingImageViewPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        baseContext = context;
        startRunUI();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        showPic = Bitmap.createScaledBitmap(temper.getBitmap(),viewWidth,viewHeight-200,false);
        Matrix matrix = new Matrix();
        computeLocation();
        matrix.postTranslate(0,(float)currentYPosition);
        canvas.drawBitmap(showPic,matrix,MovingImageViewPaint);
        showPic.recycle();
        showPic = null;
        postInvalidate();
    }

    private void computeLocation(){
        currentYPosition += Speed;
        Log.d("zhangrui","currentYPosition="+currentYPosition);
        if (currentYPosition >= viewHeight-showPic.getHeight()) {
            Speed= -Speed;
        }else if(currentYPosition <= 0){
            Speed= -Speed;
        }

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    private void startRunUI(){
        temper = (BitmapDrawable) baseContext.getResources().getDrawable(R.drawable.demo);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWidth = w;
        viewHeight = h;
        Log.d("zhangrui","viewWidth="+viewWidth+"///viewHeight="+viewHeight);
    }
}
