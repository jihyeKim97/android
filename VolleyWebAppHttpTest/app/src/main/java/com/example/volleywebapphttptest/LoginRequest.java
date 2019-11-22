package com.example.volleywebapphttptest;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {

    final static private String URL = "http://yoojoo300.dothome.co.kr/Login.php";
    private Map<String,String>map;
    private String userID;
    private String userPassword;


    public LoginRequest(String userID, String userPassword,
                        Response.Listener<String> listener) {
        super(Method.POST,URL,listener, null);
        map = new HashMap<>();
        this.userID= userID;
        this.userPassword = userPassword;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        map.put("userID",this.userID);
        map.put("userPassword",this.userPassword);
        return map;
    }
}
