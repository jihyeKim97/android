package com.example.miniphotoshoptest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton ibZoomIn,ibZoomOut,ibRotate,ibBrightn,ibDark,ibGray;
    LinearLayout llBitMap;
    float scaleX = 1.0f;
    float scaleY = 1.0f;
    float color = 1.0f;
    float satur = 1.0f;
    float angle = 0.0f;
    MyGraphicView myGraphicView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("미니포코샵");
        ibZoomIn = (ImageButton) findViewById(R.id.ibZoomIn);
        ibZoomOut = (ImageButton)findViewById(R.id.ibZoomOut);
        ibRotate =(ImageButton) findViewById(R.id.ibRotate);
        ibBrightn = (ImageButton)findViewById(R.id.ibBrightn);
        ibDark = (ImageButton)findViewById(R.id.ibDark);
        ibGray = (ImageButton)findViewById(R.id.ibGray);
        llBitMap = (LinearLayout)findViewById(R.id.llBitMap);

        myGraphicView = new MyGraphicView(this);
        //Main Activity  - LinearLayOut 에 뷰로 적용한다.
        llBitMap.addView(myGraphicView);

        //이벤트 설정
        ibZoomIn.setOnClickListener(this);
        ibZoomOut.setOnClickListener(this);
        ibRotate.setOnClickListener(this);
        ibBrightn.setOnClickListener(this);
        ibDark.setOnClickListener(this);
        ibGray.setOnClickListener(this);
        ibZoomIn.callOnClick();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ibZoomIn:
                scaleX += 0.2f;
                scaleY += 0.2f;
                break;
            case R.id.ibZoomOut:
                scaleX -= 0.2f;
                scaleY -= 0.2f;
                break;
            case R.id.ibRotate:
                angle +=20;
                break;
            case R.id.ibBrightn:
                color +=0.2f;
                break;
            case R.id.ibDark:
                color -=0.2f;
                break;
            case R.id.ibGray:
                satur = (satur ==0)?(1):(0);
                break;

        }
        //무효화 영역 처리 요청하는 것임
        myGraphicView.invalidate();
    }

    private class MyGraphicView extends View{

        public MyGraphicView(Context context) {
        super(context);

        }

        public MyGraphicView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);


            //MyGraphicView의 중심점 구함
            int centX = this.getWidth()/2;
            int centY = this.getHeight()/2;
            canvas.scale(scaleX, scaleY, centX, centY);
            canvas.rotate(angle, centX, centY);



            //스케일을 정한다(ZoomI, ZoomOut멤버 변수 참조)
            canvas.scale(scaleX,scaleY,centX,centY);

            //붓을 결정한다
            Paint paint = new Paint();

            //Bright, Dark 값을 적용하기 위한 내용
            float[] array = {color,0,0,0,0, 0,color,0,0,0,0,0,color,0,0, 0,0,0,1,0};

            ColorMatrix colorMatrix = new ColorMatrix(array);


            //회색영상 값 설정 코드
            if(satur == 0.0 ){
                colorMatrix.setSaturation(0.0f);
            }
            paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));

            //이미지를 비트맵으로 가져왔음
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.nikik);

            //비트맴을 캔버스 중앙에 그리기 위햐서 좌표계산
            int x=(this.getWidth() - bitmap.getWidth())/2;
            int y=(this.getHeight() - bitmap.getHeight())/2;

            //캔버스에 비트맵을 그린다.
            canvas.drawBitmap(bitmap,x,y,paint);

            //비트맵을 메모리에 로딩(ㅁㅔ모리 버퍼기능을 부여함)
            bitmap.recycle();
            //무효화 영역 처리를 요청하는 것이기 때문에 자동으로 OnDraw를 콜한다.
            invalidate();
        }
    }
}
