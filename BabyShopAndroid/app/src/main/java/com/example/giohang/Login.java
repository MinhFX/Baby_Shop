package com.example.giohang;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.giohang.CallPHP.User;
import com.example.giohang.Link.MainLink;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    EditText txtUsernameLogin, txtPassLogin;
    Button btnLogin,btnTransSignUpMain;
//    String url = "https://fasttravel.pro.vn/BabyShop/login.php";
    String url = MainLink.GlobalLink + "login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        anhxa();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dangnhap(url);
            }
        });

        btnTransSignUpMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Signup.class);
                startActivityForResult(intent, 110);
            }
        });
    }

    private void anhxa()
    {
        txtUsernameLogin = (EditText)findViewById(R.id.txtUsernameLogin);
        txtPassLogin = (EditText)findViewById(R.id.txtPasswordLogin);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnTransSignUpMain = (Button)findViewById(R.id.btnTransSignUpMain);
    }

    private void dangnhap(String url)
    {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success"))
                        {
                            Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            new User(Login.this, 1).execute(txtUsernameLogin.getText().toString().trim(),txtPassLogin.getText().toString().trim());
                            startActivity(new Intent(Login.this, Home.class));
                            finish();
                        } else{
                            Toast.makeText(Login.this, "" + response, Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Login.this, "Đã xảy ra lỗi !!", Toast.LENGTH_SHORT).show();
                        Log.d("AAA", "Lỗi!\n" + error.toString());
                    }
                }

        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username",txtUsernameLogin.getText().toString().trim());
                params.put("password",txtPassLogin.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 110 && resultCode == RESULT_OK)
        {
            Toast.makeText(Login.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
        }
    }
}