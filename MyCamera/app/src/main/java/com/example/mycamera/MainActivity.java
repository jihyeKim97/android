package com.example.mycamera;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivResult;
    private Button btnCapture;
    private String imageFilePath;
    private Uri photoUri;
    private static final int REQUEST_IMAGE_CAPTURE = 672;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivResult = findViewById(R.id.ivResult);
        btnCapture = findViewById(R.id.btnCapture);

        TedPermission.with(getApplicationContext()).setPermissionListener(new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                toastDisplay("사용가능");
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                toastDisplay("시용 불가능");
            }
        }).setRationaleMessage("카메라권한필요").setDeniedMessage("거부하셨습니다.").setPermissions(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA).check();

        btnCapture.setOnClickListener(this);

    }//on Create

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnCapture) {
            // 1. 카메라를 띄워달라고 요청하는 문장임.(카메라와 저장파일을 공유함)
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            // 어느 액티비티든 인텐트를 수신하도록 하려면 Intent 객체의 resolveActivity()를 호출한다.
            // 결과가 null이 아닌 경우, 인텐트를 처리할 수 있는 앱이 최소한 하나는 있다는 뜻이며
            // startActivity()를 호출해도 안전합니다. 결과가 null이면, 해당 인텐트를 사용해서는
            // 안 되고 가능하면 해당 인텐트를 발생시키는 기능을 비활성화해야됨.

            if (intent.resolveActivity(getPackageManager()) != null) {
                File photoFile = null;
                try {
                    // 파일명을 만들어야 함 20191127103123_~랜덤~.jpg
                    photoFile = createImageFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (photoFile != null) {
                    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
                        photoUri = FileProvider.getUriForFile(getApplicationContext(), getPackageName(), photoFile);
                    }else{
                        photoUri = Uri.fromFile(photoFile);
                    }
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                    startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            Bitmap bitmap = BitmapFactory.decodeFile(imageFilePath);
            Log.d("MainActivity", "onActivityResult->imageFilePath = " + imageFilePath);
            // 이미지가 갖고 있는 상세정보를 추출할때 필요한객체임.= 속성체크
            ExifInterface exifInterface = null;
            try {
                exifInterface = new ExifInterface(imageFilePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            int exifOrientation;//방향 설정 값을 저장변수
            int exifDegree;//Degree 설정 값을 저장 변수
            if (exifInterface != null) {
                // 이미지에서 카메라가 찍은 각도 값을 구함
                exifOrientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
                exifDegree = exifOrientationToDegress(exifOrientation);
            } else {
                exifDegree = 0;
            }
            ivResult.setImageBitmap(rotate(bitmap,exifDegree));
        }

    }

    private Bitmap rotate(Bitmap bitmap, int exifDegree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(exifDegree);
        return Bitmap.createBitmap(bitmap, 0,0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }


    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_hhmmss").format(new Date());
        String imageFileName = "test_" + timeStamp + "_";
        // 외부 장치의 디렉토리 명을 파일로 가져옴
        // 외부저장소의 최상위경로를 반환. sdcard/Android/data/[패키지 이름]/files/Pictures
        //storage/emulated/0/Android/data/com.example.kdj.carmeratest/files/Pictures/test_20191127103123_~랜덤~.jpg
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        // 절대경로
        // image.getAbsolutePath =
        // storage/emulated/0/Android/data/com.example.kdj.carmeratest/files/Pictures/test_20191127103123_~랜덤~.jpg
        imageFilePath = image.getAbsolutePath();

        return image;
    }

    //카메라를 찍을때  카메라 각도에 따라 이미지를 회전시켜주는 함수
    private int exifOrientationToDegress(int exifOrientation) {
        switch (exifOrientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                return 90;
            case ExifInterface.ORIENTATION_ROTATE_180:
                return 180;
            case ExifInterface.ORIENTATION_ROTATE_270:
                return 270;
        }
        return 0;
    }
    private void toastDisplay(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}
