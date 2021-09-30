package com.example.stpos.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.stpos.Network.Api;
import com.example.stpos.Network.RetrofitClient;
import com.example.stpos.R;
import com.example.stpos.Util.Constant;
import com.example.stpos.databinding.FragmentUpdateSupplierBinding;
import com.google.gson.JsonObject;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UpdateSupplierFragment extends Fragment {

    public FragmentUpdateSupplierBinding binding;
    Api api;
    ProgressDialog progressDialog;
    String id;

    public UpdateSupplierFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static UpdateSupplierFragment newInstance(String param1, String param2) {
        UpdateSupplierFragment fragment = new UpdateSupplierFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_update_supplier, container, false);

        binding = FragmentUpdateSupplierBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        api = RetrofitClient.get(getContext()).create(Api.class);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please wait....");
        progressDialog.setCancelable(false);

        getdata();

        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datavalidation();
            }
        });
    }

    private void getdata() {
        Bundle mbundle = this.getArguments();
        String name = mbundle.getString(Constant.CATEGORYNAME);
        String company = mbundle.getString(Constant.COMPANY_NAME);
        String address = mbundle.getString(Constant.ADDRESS);
        String cellno = mbundle.getString(Constant.CELL_NO);
        id = mbundle.getString(Constant.ID);
       // Log.e("supplierid",id);
        binding.suppName.setText(name);
        binding.suppAddress.setText(address);
        binding.suppComName.setText(company);
        binding.suppCellNo.setText(cellno);

//        binding.CategoryName.setText(name);
    }

    public void datavalidation() {

        datasubmits();
//        if (!TextUtils.isEmpty(binding.CategoryName.getText().toString())) {
//
//            datasubmit();
//        } else {
//            binding.CategoryName.setError("Please fill out this field");
//            binding.CategoryName.requestFocus();
//        }
    }

    public void datasubmits() {
        progressDialog.show();
        api.updatesupplier(binding.suppName.getText().toString(), binding.suppComName.getText().toString(),
                binding.suppCellNo.getText().toString(), binding.suppAddress.getText().toString(), id).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                progressDialog.dismiss();
                if (response.isSuccessful() && response.body() != null) {
                    Navigation.findNavController(getView()).navigate(R.id.action_global_manageSupplierListFragment);

                    Toast.makeText(getContext(), "Category Update Successfully", Toast.LENGTH_SHORT).show();
                }else {
                    try {
                        Log.e("suppplierrr",response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });

    }
}