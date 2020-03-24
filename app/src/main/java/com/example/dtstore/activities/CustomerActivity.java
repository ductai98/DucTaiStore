package com.example.dtstore.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dtstore.R;
import com.example.dtstore.utilities.CheckNetworkConnection;
import com.example.dtstore.utilities.Server;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

public class CustomerActivity extends AppCompatActivity {
    TextInputEditText edtCustomerName, edtCustomerPhone, edtCustomerEmail;
    MaterialButton btnCustomerConfirm, btnCustomerBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        MappingView();
        HandleEvent();
    }

    private void HandleEvent() {
        //Button back on click
        btnCustomerBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Button Confirm on click
        if(CheckNetworkConnection.haveNetworkConnection(this)){
            btnCustomerConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String customerName = edtCustomerName.getText().toString().trim();
                    final String customerPhone = edtCustomerPhone.getText().toString().trim();
                    final String customerEmail = edtCustomerEmail.getText().toString().trim();

                    if (customerName.isEmpty() || customerPhone.isEmpty() || customerEmail.isEmpty()) { //Verify data
                        String message = "Dữ liệu không được trống";
                        ShowAlert(CustomerActivity.this, message, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                    } else {
                        RequestQueue requestQueue = Volley.newRequestQueue(CustomerActivity.this);
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.ReceiptURL, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d("ReceitID: ", response);
                                //Show Alert Confirm
                                ShowAlert(CustomerActivity.this, "Đã gửi thông báo mua", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                });
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }) {
                            //Add params to use POST method
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                HashMap<String, String> params = new HashMap<>();
                                params.put("name", customerName);
                                params.put("phone", customerPhone);
                                params.put("email", customerEmail);
                                return params;
                            }
                        };

                        requestQueue.add(stringRequest);
                    }
                }
            });
        }else{
            CheckNetworkConnection.ShowMessage_Short(this, "No internet connection!");
        }
    }

    private void ShowAlert(Context pContext, String pMessage, DialogInterface.OnClickListener onOKListener){
        AlertDialog.Builder alert = new AlertDialog.Builder(pContext);
        alert.setMessage(pMessage);
        alert.setPositiveButton("Xác nhận", onOKListener);
        alert.show();
    }

    private void MappingView() {
        edtCustomerName = findViewById(R.id.edt_customer_name);
        edtCustomerPhone = findViewById(R.id.edt_customer_phone);
        edtCustomerEmail = findViewById(R.id.edt_customer_email);
        btnCustomerConfirm = findViewById(R.id.btn_customer_confirm);
        btnCustomerBack = findViewById(R.id.btn_customer_back);
    }
}
