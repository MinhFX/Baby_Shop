package com.example.giohang;

import androidx.appcompat.app.AppCompatActivity;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
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
import com.example.giohang.User.UserHolder;
import com.example.giohang.User.UserItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountEdit extends AppCompatActivity {
    Spinner spGender;
    ArrayList<UserItem> UserList;
    List<String> list;
    ArrayAdapter adapter;
    String User;
    String User1;
    String Gender;
    TextView txtBirtday, txtName, txtPhone, txtAdd, txtEmail;
    Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accountedit);
        String url = MainLink.GlobalLink + "updateuser.php";
        AnhXa();

        UserList = UserHolder.loadUserItem(AccountEdit.this);
        UserItem currentUser = UserList.get(0);
        User = currentUser.getUsername();
        User1 = currentUser.getPassword();

        txtName.setText(currentUser.getFullName());
        txtEmail.setText(currentUser.getEmail());
        if (currentUser.getPhone() != 0)
            txtPhone.setText(String.valueOf(currentUser.getPhone()));
        txtAdd.setText(currentUser.getAddress());
        txtBirtday.setText(currentUser.getBirthday());


        try {
            list = new ArrayList<>();
            list.add("Nữ");
            list.add("Nam");
            adapter = new ArrayAdapter(AccountEdit.this, android.R.layout.simple_spinner_item, list);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spGender.setAdapter(adapter);
            spGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    if(parent.getItemAtPosition(position).toString().equals("Nam"))
                    {
                        Gender = "1";
                    }
                    else {
                        Gender = "0";
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        catch (Exception e)
        {
            Toast.makeText(AccountEdit.this, e.toString(), Toast.LENGTH_LONG).show();
        }
        spGender.setSelection(currentUser.getGender());


        txtBirtday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogDate();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtPhone.getText().toString().length() == 9)
                {
                    update(url);
                    finish();
                }
                else
                {
                    Toast.makeText(AccountEdit.this, "Số điện thoại không hợp lệ", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void AnhXa()
    {
        txtBirtday = findViewById(R.id.txtBirtday);
        spGender = findViewById(R.id.spGender);
        txtEmail = findViewById(R.id.txtEmail);
        txtName = findViewById(R.id.txtName);
        txtAdd = findViewById(R.id.txtAdd);
        txtPhone = findViewById(R.id.txtPhone);
        btnSave = findViewById(R.id.btnSave);
    }

    private void update(String url)
    {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success"))
                        {
                            new User(AccountEdit.this, 1).execute(User,User1);
                            Toast.makeText(AccountEdit.this, "Lưu thông tin thành công", Toast.LENGTH_SHORT).show();
                        } else{
                            Toast.makeText(AccountEdit.this, "Lưu thất bại, đã xảy ra lỗi" + response, Toast.LENGTH_SHORT).show();
                            Log.e("TAGG", ""+response);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AccountEdit.this, "Đã xảy ra lỗi !!", Toast.LENGTH_SHORT).show();
                        Log.d("AAA", "Lỗi!\n" + error.toString());
                    }
                }

        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Username",User);
                params.put("Password",User1);
                params.put("Email",txtEmail.getText().toString().trim());
                params.put("Fullname",txtName.getText().toString().trim());
                params.put("Birthday",txtBirtday.getText().toString().trim());
                params.put("Gender",Gender);
                params.put("Phone",txtPhone.getText().toString().trim());
                params.put("Address",txtAdd.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }


    private void openDialogDate()
    {
        DatePickerDialog dialog = new DatePickerDialog(AccountEdit.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                txtBirtday.setText(year + "-" + (month+1) + "-" + day);
            }
        }, 2023, 0, 1);
        dialog.show();
    }

    public void clickSignout(View view)
    {
       Intent intent = new Intent();
       setResult(RESULT_OK, intent);
       finish();
    }

    public void clickGoback(View view)
    {
        finish();
    }
}