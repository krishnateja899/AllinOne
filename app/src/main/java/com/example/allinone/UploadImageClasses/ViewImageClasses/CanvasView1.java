package com.example.allinone.UploadImageClasses.ViewImageClasses;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;

public class CanvasView1 extends androidx.appcompat.widget.AppCompatImageView {
    public Boolean Write;
    private Paint mPenPainter;
    public int width;
    public int height;

    private Bitmap mBitmap;
    private Canvas mCanvas;

    private Path mPath;

    Context context;

    private Paint mPaint;

    private float mX, mY;

    private static final float TOLERANCE = 5;
    private ArrayList<Path> paths = new ArrayList<Path>();
    private ArrayList<Path> undonePaths = new ArrayList<Path>();

    private boolean isErasemode = false;

    // private int paintColor = 0xFF000000;
    private int paintColor;

    public CanvasView1(Context c, AttributeSet attrs) {
        super(c, attrs);
        context = c;
        // we set a new Path
        mPath = new Path();

        // and we set a new Paint with the desired attributes
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(paintColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(4f);
        //float mEraserWidth = getResources().getDimension(R.dimen.eraser_size);
        mPenPainter = new Paint();
        mPenPainter.setColor(Color.BLUE);

    }

    public CanvasView1(Context c) {
        super(c);
        context = c;
        // we set a new Path
        mPath = new Path();
        // and we set a new Paint with the desired attributes
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(paintColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(4f);
        //float mEraserWidth = getResources().getDimension(R.dimen.eraser_size);
        mPenPainter = new Paint();
        mPenPainter.setColor(Color.BLUE);

    }

    public void setBrushColor(int color) {
        this.paintColor = color;
        mPaint.setColor(paintColor);
    }

    public void resetPaths() {

        if (paths.size() > 0) {
            paths.clear();
            invalidate();
        } else {
        }
    }

    // override onSizeChanged
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // your Canvas will draw onto the defined Bitmap
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);

    }


    // override onDraw

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // draw the mPath with the mPaint on the canvas when onDraw

        for (Path p : paths) {
            canvas.drawPath(p, mPaint);
        }
        Log.v("okkadu","abc");
        canvas.drawPath(mPath, mPaint);

    }

    private void startTouch(float x, float y) {

        undonePaths.clear();
        mPath.reset();
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }

    public void onClickUndo() {
        if (paths.size() > 0) {
            undonePaths.add(paths.remove(paths.size() - 1));
            invalidate();
        } else {

        }

    }

    public void onEraser() {
        if (!isErasemode) {
            isErasemode = true;
        } else {
            isErasemode = false;
        }
    }

    private void remove1(int index) {
        paths.remove(index);
        invalidate();
    }

    public void onClickRedo() {
        if (undonePaths.size() > 0) {
            paths.add(undonePaths.remove(undonePaths.size() - 1));
            invalidate();
        } else {

        }

    }

    // when ACTION_MOVE move touch according to the x,y values

    private void moveTouch(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOLERANCE || dy >= TOLERANCE) {
            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;

        }
    }


    private void upTouch() {
        mPath.lineTo(mX, mY);
        mPath.lineTo(mX, mY);
        // commit the path to our offscreen
        mCanvas.drawPath(mPath, mPaint);
        // kill this so we don't double draw
        paths.add(mPath);
        mPath = new Path();
    }


    //override the onTouchEvent

    @Override

    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        if (isErasemode) {

            for (int i = 0; i < paths.size(); i++) {
                RectF r = new RectF();
                Point pComp = new Point((int) (event.getX()), (int) (event.getY()));

                Path mPath = paths.get(i);
                mPath.computeBounds(r, true);
                if (r.contains(pComp.x, pComp.y)) {
                    Log.i("need to remove", "need to remove");
                    remove1(i);
                    break;
                }
            }
            return false;
        } else {

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (Write) {
                        mX = event.getX();
                        mY = event.getY();
                        startTouch(x, y);
                    }
                    invalidate();
                    break;

                case MotionEvent.ACTION_MOVE:
                    if (Write) {
                        moveTouch(x, y);
                    }
                    invalidate();
                    break;

                case MotionEvent.ACTION_UP:
                    if (Write) {
                        upTouch();
                    }
                    invalidate();

                    break;
            }

            return true;
        }

    }


}

