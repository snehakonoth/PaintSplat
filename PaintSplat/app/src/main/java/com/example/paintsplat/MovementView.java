package com.example.paintsplat;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

public class MovementView extends SurfaceView implements SurfaceHolder.Callback {
    private int xPos;
    private int yPos;

    private int xVel;
    private int yVel;

    private int width;
    private int height;

    private int squareWidth;
    private Paint squarePaint;

    private int circleRadius;
    private Paint circlePaint;

    private Paint mPaint;
    private Path mPath;
    private UpdateCount updateCount;

    private List<Point> points = new ArrayList<Point>();


    Rect rectangle;

    UpdateThread updateThread;

    int count = 0;


    public MovementView(Context context,UpdateCount updateCount) {
        super(context);
        getHolder().addCallback(this);
        this.updateCount = updateCount;
        count = 0;
        squareWidth = 300;
        squarePaint = new Paint();
        squarePaint.setColor(Color.WHITE);

        circleRadius = 50;
        circlePaint = new Paint();
        circlePaint.setColor(Color.BLUE);


        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(30);
        mPath = new Path();

        xVel = 20;
        yVel = 30;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (canvas != null) {
            double squareSideHalf = 1 / Math.sqrt(2);
            canvas.drawColor(Color.BLACK);
            rectangle = new Rect((int) (xPos - (squareSideHalf * squareWidth)), (int) (yPos - (squareSideHalf * squareWidth)), (int) (xPos + (squareSideHalf * squareWidth)), (int) (yPos + ((squareSideHalf * squareWidth))));
            canvas.drawRect(rectangle, squarePaint);

            for (Point point : points) {
                if (rectangle.contains(point.x, point.y)) {
                    Float x_dist_from_center = new Float(rectangle.exactCenterX() - point.x);
                    Float y_dist_from_center = new Float(rectangle.exactCenterY() - point.y);
                    Float new_x = rectangle.exactCenterX() + x_dist_from_center;
                    Float new_y = rectangle.exactCenterY() + y_dist_from_center;
                    System.out.println(x_dist_from_center + " " + new_x + " " + y_dist_from_center + " " + new_y);
                    canvas.drawCircle(new_x, new_y, circleRadius, circlePaint);
                }
            }
            invalidate();
        }
        super.onDraw(canvas);
    }


    public void updatePhysics() {

        xPos += xVel;
        yPos += yVel;

        if (yPos - squareWidth < 0 || yPos + squareWidth > height) {


            if (yPos - squareWidth < 0) {

                yPos = squareWidth;
            } else {

                yPos = height - squareWidth;
            }


            yVel *= -1;
        }

        if (xPos - squareWidth < 0 || xPos + squareWidth > width) {

            if (xPos - squareWidth < 0) {

                xPos = squareWidth;
            } else {

                xPos = width - squareWidth;
            }


            xVel *= -1;
        }
    }


    public void surfaceCreated(SurfaceHolder holder) {

        Rect surfaceFrame = holder.getSurfaceFrame();
        width = surfaceFrame.width();
        height = surfaceFrame.height();

        xPos = width / 2;
        yPos = squareWidth;

        updateThread = new UpdateThread(this);
        updateThread.setRunning(true);
        updateThread.start();
    }


    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }


    public void surfaceDestroyed(SurfaceHolder holder) {

        boolean retry = true;

        updateThread.setRunning(false);
        while (retry) {
            try {
                updateThread.join();
                retry = false;
            } catch (InterruptedException e) {

            }
        }
    }

   /* @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(event.getX(), event.getY());
                break;

            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(event.getX(), event.getY());
                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                break;
        }

        return true;
    }*/


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Point p = new Point();
            p.x = (int) event.getX();
            p.y = (int) event.getY();
            points.add(p);
            count++;
            setUpdateCount(count);
            invalidate();
        }
        return false;
    }

    private void setUpdateCount(int count){
        if(updateCount!=null){
            updateCount.updateCount(count);
        }
    }


}