package com.example.stpos.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.Toast;

import com.example.stpos.Network.Api;
import com.example.stpos.Network.RetrofitClient;
import com.example.stpos.Util.Constant;
import com.example.stpos.Util.MySharedPreference;
import com.example.stpos.databinding.ActivityLoginBinding;
import com.google.gson.JsonObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    public ActivityLoginBinding binding;
    Api api;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_login);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String Token = MySharedPreference.getInstance(getApplicationContext()).getString(Constant.TOKEN, "not found");
        if (!Token.equals(new String("not found"))) {
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();
        }
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage("Please Wait......");
        progressDialog.setCancelable(false);

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(binding.etUserName.getText().toString())) {
                    if (!TextUtils.isEmpty(binding.tvForgotPassword.getText().toString())) {
                        progressDialog.show();
                        api = RetrofitClient.noInterceptor().create(Api.class);
                        Call<JsonObject> call = api.login(binding.etUserName.getText().toString(),
                                binding.etPassword.getText().toString());
                        call.enqueue(new Callback<JsonObject>() {
                            @Override
                            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                                //  Log.e("tesst",response.toString());
                                progressDialog.dismiss();
                                if (response.isSuccessful() && response.body() != null) {
//                                    String token = response.body().get("token_info");
                                    JsonObject token_info = response.body().getAsJsonObject("token_info");
                                    String token = token_info.get("token").getAsString();
                                    JsonObject user_info = response.body().getAsJsonObject("user_info");
                                    Log.e("tesst", token);
                                    String name = user_info.get("name").getAsString();
                                    String phn = user_info.get("email").getAsString();
                                    String MercentId = user_info.get("user_id").getAsString();
                                    Log.e("tesst", response.body().toString());

                                    MySharedPreference.getInstance(LoginActivity.this).edit()
                                            .putString(Constant.TOKEN, token).putString(Constant.NAME, name).putString(Constant.PHONE, phn).apply();
//                                            .putString(Constant.MERCENTID, MercentId).apply();
                                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                                    finish();

                                } else {
                                    progressDialog.dismiss();
                                    try {
                                        Log.e("tesst", response.errorBody().string());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    Toast.makeText(LoginActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<JsonObject> call, Throwable t) {
                                progressDialog.dismiss();
                                Log.e("tesst", t.toString());
                                Toast.makeText(getApplicationContext(), "Network or Server Error", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        binding.etPassword.setError("Please Input Valid email!!");
                        binding.etPassword.requestFocus();
                    }
                } else {
                    binding.etUserName.setError("Please Input Valid password!!");
                    binding.etUserName.requestFocus();
                }

            }
        });
    }


}