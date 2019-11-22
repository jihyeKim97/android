package com.example.example_9_1_drawingcanvas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    final  static int LINE = 1;
    final  static int CIRCLE = 2;
    final  static int RECTANGLE = 3;
    static int curShape = LINE;
    static int curColor = Color.BLACK;

    static List<MyShape> myShape = new ArrayList<MyShape>();
    static boolean isFinish = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphicView(this));
        setTitle("그림판 만들기");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0,1,0,"선그리기");
        menu.add(0,2,0,"원그리기");
        menu.add(0,3,0,"사각형그리기");
        SubMenu subMenu =menu.addSubMenu("색상변경 >>>>");
        subMenu.add(0,4,0,"사과");
        subMenu.add(0,5,0,"귤");
        subMenu.add(0,6,0,"포도");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       switch (item.getItemId()){
           case 1: curShape=LINE; return true;
        case 2: curShape=CIRCLE; return true;
        case 3: curShape=RECTANGLE; return true;
        case 4: curColor=Color.RED; return true;
        case 5: curColor=Color.YELLOW; return true;
        case 6: curColor=Color.MAGENTA; return true;
    }
        return super.onOptionsItemSelected(item);
    }



    private class MyGraphicView extends View {
        int startX  = -1,startY = -1,stopX = -1,stopY = -1;
        public MyGraphicView(Context context) {
            super(context);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
           switch (event.getAction()){
               case MotionEvent.ACTION_DOWN:
                   startX = (int)event.getX();
                   startY = (int)event.getY();
                   isFinish = false;
                   break;

               case MotionEvent.ACTION_MOVE:
                   stopX = (int)event.getX();
                   stopY = (int)event.getY();
                   isFinish = false;
                   this.invalidate();
                   break;

               case MotionEvent.ACTION_UP:
                 MyShape shape = new MyShape();
                 shape.shapeType = curShape;
                 shape.startX = startX;
                 shape.startY = startY;
                 shape.stopX = stopX;
                 shape.stopY = stopY;
                 shape.color = curColor;

                 myShape.add(shape);
                 isFinish = true;
                 this.invalidate();
                   break;
           }
           return  true;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStrokeWidth(3);
            paint.setStyle(Paint.Style.STROKE);

            for(int i =0;i<myShape.size();i++){
                MyShape shape = myShape.get(i);
                paint.setColor(shape.color);
                switch (shape.shapeType){
                    case LINE:
                        canvas.drawLine(shape.startX,shape.startY,shape.stopX,shape.stopY,paint);
                        break;
                    // pow 는 특정숫자의 거듭제곱 값을 계산해주는 함수이며 sqrt 는 특정숫자의 제곱근값 계산해주는 함수입니다.
                    case CIRCLE:
                        int redis = (int)Math.sqrt(Math.pow(shape.stopX - shape.startX,2)+Math.pow(shape.stopY-shape.startY,2));
                        canvas.drawCircle(shape.startX,shape.startY,redis,paint);break;
                    case RECTANGLE :
                        Rect rect = new Rect(shape.startX,shape.startY,shape.stopX,shape.stopY);
                        canvas.drawRect(rect,paint); break;
                }
            }
            if (isFinish == false) {

                paint.setColor(curColor);

                switch (curShape) {
                    case LINE:
                        canvas.drawLine(startX, startY, stopX, stopY, paint);
                        break;
                    case CIRCLE:
                        int radius = (int) Math.sqrt(Math.pow(stopX - startX, 2)
                                + Math.pow(stopY - startY, 2));
                        canvas.drawCircle(startX, startY, radius, paint);
                        break;
                    case RECTANGLE:
                        Rect rect = new Rect(startX, startY, stopX, stopY);
                        canvas.drawRect(rect, paint);
                        break;
                }
            }

        }
    }
    //도형의 클래스
    private static class MyShape {
        int shapeType;
        int startX,startY,stopX, stopY ;
        int color;
    }

}
