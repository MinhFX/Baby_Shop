package com.example.giohang;

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
import com.example.giohang.Link.MainLink;

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {
    EditText txtUsername, txtPassword, txtRePassword;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        txtUsername = (EditText)findViewById(R.id.txtUsername);
        txtPassword = (EditText)findViewById(R.id.txtPassword);
        txtRePassword = (EditText)findViewById(R.id.txtRePassword);
        btnSignUp = (Button)findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone_check = txtUsername.getText().toString().trim();
                String pass_check = txtPassword.getText().toString().trim();
                String repass_check = txtRePassword.getText().toString().trim();
                if(!phone_check.equals("") && !pass_check.equals("") && !repass_check.equals(""))
                {
                    if (pass_check.equals(repass_check))
                    {
                        DangKi(url);
                    }
                    else {
                        Toast.makeText(Signup.this, "Mật khẩu và mật khẩu nhập lại không khớp !", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(Signup.this, "Vui lòng điền đủ thông tin !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    String url = MainLink.GlobalLink + "signup.php";
    private void DangKi(String url)
    {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("Đăng ký thành công"))
                        {
                            Intent back = new Intent();
                            setResult(RESULT_OK, back);
                            finish();
                        } else{
                            Toast.makeText(Signup.this, "Tài khoản đã tồn tại" , Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Signup.this, "Đã xảy ra lỗi !!", Toast.LENGTH_SHORT).show();
                        Log.d("AAA", "Lỗi!\n" + error.toString());
                    }
                }

        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username",txtUsername.getText().toString().trim());
                params.put("password",txtPassword.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void gotoLogin(View view)
    {
        finish();
    }
}