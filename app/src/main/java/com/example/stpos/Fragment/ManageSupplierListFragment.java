package com.example.stpos.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.stpos.Adaptar.SupllierList.SupplierListAdaptar;
import com.example.stpos.DataModel.Supplier.SupplierContainer;
import com.example.stpos.Network.Api;
import com.example.stpos.Network.RetrofitClient;
import com.example.stpos.R;
import com.example.stpos.databinding.FragmentManageCategoryBinding;
import com.example.stpos.databinding.FragmentManageSupplierListBinding;
import com.google.gson.JsonObject;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ManageSupplierListFragment extends Fragment {

    Api api;
    ProgressDialog progressDialog;
    public FragmentManageSupplierListBinding binding;


    public ManageSupplierListFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ManageSupplierListFragment newInstance(String param1, String param2) {
        ManageSupplierListFragment fragment = new ManageSupplierListFragment();

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
        // return inflater.inflate(R.layout.fragment_manage_supplier_list, container, false);
        binding = FragmentManageSupplierListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        api = RetrofitClient.get(getContext()).create(Api.class);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please wait....");
        progressDialog.setCancelable(false);

        datainitialize();
    }

    private void datainitialize() {
        progressDialog.show();
        api.supplierlist().enqueue(new Callback<SupplierContainer>() {
            @Override
            public void onResponse(Call<SupplierContainer> call, Response<SupplierContainer> response) {
                progressDialog.dismiss();
                if (response.isSuccessful() && response.body() != null) {
                    binding.supplierlist.setHasFixedSize(true);
                    binding.supplierlist.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,
                            false));
                    SupplierListAdaptar adaptar=new SupplierListAdaptar(response.body().getSupplierList(),
                            getContext(), new SupplierListAdaptar.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position, String id) {
                            api.deletesupplier(id).enqueue(new Callback<JsonObject>() {
                                @Override
                                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                                    progressDialog.dismiss();
                                    if (response.isSuccessful()&& response.body()!=null){
                                        datainitialize();
                                        Toast.makeText(getContext(), "Supplier Delete", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        try {
                                            Log.e("suppp",response.errorBody().string());
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
                    });
                    binding.supplierlist.setAdapter(adaptar);
                }
            }

            @Override
            public void onFailure(Call<SupplierContainer> call, Throwable t) {

            }
        });
    }
}