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
import com.example.stpos.databinding.FragmentUpdateSubCategoryBinding;
import com.google.gson.JsonObject;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UpdateSubCategoryFragment extends Fragment {

    public FragmentUpdateSubCategoryBinding binding;
    Api api;
    ProgressDialog progressDialog;
    String id;
    String subid;

    public UpdateSubCategoryFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static UpdateSubCategoryFragment newInstance(String param1, String param2) {
        UpdateSubCategoryFragment fragment = new UpdateSubCategoryFragment();
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
        // return inflater.inflate(R.layout.fragment_update_sub_category, container, false);
        binding = FragmentUpdateSubCategoryBinding.inflate(inflater, container, false);
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
        binding.submitCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datavalidation();
                // Navigation.findNavController(v).navigate(R.id.action_global_customerListFragment);
            }
        });
    }

    private void datavalidation() {
//        if (!TextUtils.isEmpty(binding.subcategoryName.getText().toString())) {

        datasubmit();
//        } else {
//            binding.subcategoryName.setError("Please fill out this field");
//            binding.subcategoryName.requestFocus();
//        }
    }

    private void datasubmit() {
        progressDialog.show();
        api.updatesubcategory(binding.subcategoryName.getText().toString(), subid,id).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                progressDialog.dismiss();
                if (response.isSuccessful() && response.body() != null) {
                    Navigation.findNavController(getView()).navigate(R.id.action_global_manageSubCategoryFragment);
                   // Log.e("subname", response.body().toString());
                    Toast.makeText(getContext(), "SubCategory Update Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        Log.e("testssssss", response.errorBody().string());
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


    private void getdata() {
        Bundle mbundle = this.getArguments();
        String name = mbundle.getString(Constant.CATEGORYNAME);
        id = mbundle.getString(Constant.CATEGORYID);
        subid = mbundle.getString(Constant.SUBCATEGORYID);
        // binding.brandName.setText(name);
        binding.subcategoryName.setText(name);
       // Log.d("update",subid);
    }
}