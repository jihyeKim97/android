package com.example.canvaslinecircletest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    final static int LINE = 1, CIRCLE = 2;
    public int curshape = LINE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphicView(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, 1, 0, "선그리기");
        menu.add(0, 2, 0, "원그리기");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                curshape = LINE;
                break;
            case 2:
                curshape = CIRCLE;
                break;
        }
        return true;

    }

    private class MyGraphicView extends View {

        public int startX = -1;
        public int startY = -1;
        public int stopX = -1;
        public int stopY = -1;

        public MyGraphicView(Context context) {
            super(context);
        }

        public MyGraphicView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
        }

        //화면에서 터치이벤트 정보를 받는 핸들러 함수
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    startX = (int) event.getX();
                    startY = (int) event.getY();
                    break;
                case MotionEvent.ACTION_UP:
                    stopX = (int) event.getX();
                    stopY = (int) event.getY();
                    //무효화 영역 처리
                    invalidate();
                    break;
                case MotionEvent.ACTION_MOVE:
                    break;
                case MotionEvent.ACTION_CANCEL:
                    break;
            }
            return true;
        }

        //선을 그릴지 원을 그릴지 메뉴에서 선택정보가 있어야 됨 (Defalut - LINE)
        //터치하는 좌표 정보를 제공 해야 그림을 그릴수 있다.(X,Y)
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            //체임트 속성만 정의
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStrokeWidth(5);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.RED);


            switch (curshape) {
                case LINE:
                    canvas.drawLine(startX,startY,stopX,stopY,paint);
                    break;
                case CIRCLE:
                    int radius = (int) Math.sqrt(Math.pow(stopX - startX, 2)
                            + Math.pow(stopY - startY, 2));
                    canvas.drawCircle(startX, startY, radius, paint);

                    break;
            }

        }
    }
}
