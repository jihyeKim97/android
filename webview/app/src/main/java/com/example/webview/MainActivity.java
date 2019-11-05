package com.example.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText edtURL;
    Button btnMove, btnBack;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtURL=(EditText)findViewById(R.id.edtURL);
        btnMove=(Button)findViewById(R.id.btnMove);
        btnBack=(Button)findViewById(R.id.btnBack);
        webView=(WebView)findViewById(R.id.webView);

        //1.웹뷰를 실행할려면 WebViewClient 클래스 상속을 받아서
        //shouldOverrideUrlLoading 을 진행 행댜 한다.
        webView.setWebViewClient(new MyWebViewClient());
        WebSettings webSettings =  webView.getSettings();
        webSettings.setBuiltInZoomControls(true);

        /*edtURL.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                edtURL.setText("http://");
                return false;
            }
        });*/
        btnMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl("http://-"+edtURL.getText().toString());
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                webView.goBack();
            }
        });

    }
    class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }

    }
}
