package com.example.stpos.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.stpos.Network.Api;
import com.example.stpos.Network.RetrofitClient;
import com.example.stpos.R;
import com.example.stpos.databinding.FragmentAddNewCustomerBinding;
import com.example.stpos.databinding.FragmentCustomerListBinding;
import com.google.gson.JsonObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddNewCustomerFragment extends Fragment {

    public FragmentAddNewCustomerBinding binding;
    Api api;
    ProgressDialog progressDialog;

    public AddNewCustomerFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //  return inflater.inflate(R.layout.fragment_add_new_customer, container, false);

        binding = FragmentAddNewCustomerBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       // super.onViewCreated(view, savedInstanceState);
        api = RetrofitClient.get(getContext()).create(Api.class);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please wait....");
        progressDialog.setCancelable(false);
//
//        observable();

        binding.submitCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datavalidation();
               // Navigation.findNavController(v).navigate(R.id.action_global_customerListFragment);
            }
        });

    }

    private void datavalidation() {
        if (!TextUtils.isEmpty(binding.CustomerName.getText().toString())) {
            if (!TextUtils.isEmpty(binding.CellNo.getText().toString())) {
               datasubmit();
            } else {
                binding.CellNo.setError("Please fill out this field");
                binding.CellNo.requestFocus();
            }
        } else {
            binding.CustomerName.setError("Please fill out this field");
            binding.CustomerName.requestFocus();
        }

    }

    private void datasubmit() {
        progressDialog.show();
        api.addcustomer(binding.CustomerMembarshipNo.getText().toString(), binding.CustomerName.getText().toString(),
                binding.CellNo.getText().toString(), binding.CustomerAddress.getText().toString(),"1").enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                progressDialog.dismiss();
                if (response.isSuccessful() && response.body() != null) {
                    Navigation.findNavController(getView()).navigate(R.id.action_global_customerListFragment);

                    Toast.makeText(getContext(), "Add Customer Successfully", Toast.LENGTH_SHORT).show();
                }
                else {
                    try {
                        Log.e("test",response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), "Something is worng", Toast.LENGTH_LONG).show();
            }
        });


    }


}