package com.example.stpos.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.example.stpos.databinding.FragmentAddBrandBinding;
import com.example.stpos.databinding.FragmentAddNewSupplierBinding;
import com.google.gson.JsonObject;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNewSupplierFragment extends Fragment {


    public FragmentAddNewSupplierBinding binding;
    Api api;
    ProgressDialog progressDialog;

    public AddNewSupplierFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AddNewSupplierFragment newInstance(String param1, String param2) {
        AddNewSupplierFragment fragment = new AddNewSupplierFragment();

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
        // return inflater.inflate(R.layout.fragment_add_new_supplier, container, false);
        binding = FragmentAddNewSupplierBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        api = RetrofitClient.get(getContext()).create(Api.class);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please wait....");
        progressDialog.setCancelable(false);
        binding.addsupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datavalidation();

            }
        });
    }

    public void datavalidation() {
        if (!TextUtils.isEmpty(binding.addSuppName.getText().toString())) {
            if (!TextUtils.isEmpty(binding.addSuppAddress.getText().toString())) {
                if (!TextUtils.isEmpty(binding.addSuppCellNo.getText().toString())) {
                    if (!TextUtils.isEmpty(binding.addSuppCompnayName.getText().toString())){
                        datasubmits();
                    }else {
                        binding.addSuppCompnayName.setError("Please fill out this field");
                        binding.addSuppCompnayName.requestFocus();
                    }

                } else {
                    binding.addSuppCellNo.setError("Please fill out this field");
                    binding.addSuppCellNo.requestFocus();
                }

            } else {
                binding.addSuppAddress.setError("Please fill out this field");
                binding.addSuppAddress.requestFocus();
            }
        } else {
            binding.addSuppName.setError("Please fill out this field");
            binding.addSuppName.requestFocus();
        }
    }

    public void datasubmits(){
        progressDialog.show();
        api.addsupplier(binding.addSuppName.getText().toString(),binding.addSuppCompnayName.getText().toString(),
                binding.addSuppCellNo.getText().toString(),binding.addSuppAddress.getText().toString()).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()&& response.body()!=null){
                    Navigation.findNavController(getView()).navigate(R.id.action_global_manageSupplierListFragment);
                    Toast.makeText(getContext(), "Add Supplier Successfully", Toast.LENGTH_SHORT).show();
                }else {
                    try {
                        Log.e("addsupplier",response.errorBody().string());
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