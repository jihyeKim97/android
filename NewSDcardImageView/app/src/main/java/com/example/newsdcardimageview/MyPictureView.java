package com.example.newsdcardimageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MyPictureView extends View {
    private String src;

    public MyPictureView(Context context) {
        super(context);
        src = "";
    }

    public MyPictureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(src!=null){
            //이미지를 비트맵으로 가져온다.
            Bitmap bitmap = BitmapFactory.decodeFile(src);
            //이미지를 2배로 확대하기 위해서 캔버스 도화지를 두 배로 늘린다.
            canvas.scale(2,2,0,0);
            //2배로 늘린 도화지에 이미지를 고정시킨다.
            canvas.drawBitmap(bitmap,0,0,null);
            //화면에 보여준다.
            bitmap.recycle();
        }
}


    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}