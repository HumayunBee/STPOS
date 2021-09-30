package com.example.stpos.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.stpos.Adaptar.CategoryListAdaptar.CategoryListAdaptar;
import com.example.stpos.Adaptar.CustomerListAdaptar.CustomerListAdaptar;
import com.example.stpos.DataModel.Category.CategoryContainer;
import com.example.stpos.Network.Api;
import com.example.stpos.Network.RetrofitClient;
import com.example.stpos.R;
import com.example.stpos.databinding.FragmentManageCategoryBinding;
import com.google.gson.JsonObject;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ManageCategoryFragment extends Fragment {

    public FragmentManageCategoryBinding binding;
    Api api;
    ProgressDialog progressDialog;

    public ManageCategoryFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public static ManageCategoryFragment newInstance(String param1, String param2) {
        ManageCategoryFragment fragment = new ManageCategoryFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_manage_category, container, false);
        binding = FragmentManageCategoryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        api = RetrofitClient.get(getContext()).create(Api.class);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please wait....");
        progressDialog.setCancelable(false);

        //        observable();

        binding.addCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_global_addnewCategoryFragment);
            }
        });

        binding.swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                datainitialize();
                binding.swipe.setRefreshing(false);
            }
        });
        datainitialize();


    }

    private void datainitialize() {
        progressDialog.show();
        api.categorylist().enqueue(new Callback<CategoryContainer>() {
            @Override
            public void onResponse(Call<CategoryContainer> call, Response<CategoryContainer> response) {
                progressDialog.dismiss();
                if (response.isSuccessful() && response.body() != null) {
                    binding.categoryList.setHasFixedSize(true);
                    binding.categoryList.setLayoutManager(new LinearLayoutManager(getContext(),
                            LinearLayoutManager.VERTICAL, false));
                    CategoryListAdaptar adaptar= new CategoryListAdaptar(response.body().
                            getCategoryList(), getContext(), new CategoryListAdaptar.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position, String id) {
                            api.deletecategory(id).enqueue(new Callback<JsonObject>() {
                                @Override
                                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                                    progressDialog.dismiss();
                                    if (response.isSuccessful()&& response.body()!=null){
                                        datainitialize();
                                        Toast.makeText(getContext(), "Category Delete", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<JsonObject> call, Throwable t) {

                                }
                            });
                        }
                    });

                    binding.categoryList.setAdapter(adaptar);
                }
            }

            @Override
            public void onFailure(Call<CategoryContainer> call, Throwable t) {

            }
        });
    }
}