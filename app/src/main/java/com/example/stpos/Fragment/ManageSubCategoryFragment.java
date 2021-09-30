package com.example.stpos.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.stpos.Adaptar.CategoryListAdaptar.CategoryListAdaptar;
import com.example.stpos.Adaptar.Subcategory.SubCategoryAdaptar;
import com.example.stpos.DataModel.SubCategory.SubCategoryContainer;
import com.example.stpos.Network.Api;
import com.example.stpos.Network.RetrofitClient;
import com.example.stpos.R;
import com.example.stpos.databinding.FragmentManageCategoryBinding;
import com.example.stpos.databinding.FragmentManageSubCategoryBinding;
import com.google.gson.JsonObject;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageSubCategoryFragment extends Fragment {

    public FragmentManageSubCategoryBinding binding;
    Api api;
    ProgressDialog progressDialog;

    public ManageSubCategoryFragment() {
        // Required empty public constructor
    }


    public static ManageSubCategoryFragment newInstance(String param1, String param2) {
        ManageSubCategoryFragment fragment = new ManageSubCategoryFragment();

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
        // return inflater.inflate(R.layout.fragment_manage_sub_category, container, false);
        binding = FragmentManageSubCategoryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        api = RetrofitClient.get(getContext()).create(Api.class);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please wait....");
        progressDialog.setCancelable(false);

        binding.addCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_global_addNewSubCategoryFragment);
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
        api.subcategorylist().enqueue(new Callback<SubCategoryContainer>() {
            @Override
            public void onResponse(Call<SubCategoryContainer> call, Response<SubCategoryContainer> response) {
                progressDialog.dismiss();
                if (response.isSuccessful() && response.body() != null) {
                    binding.subCat.setHasFixedSize(true);
                    binding.subCat.setLayoutManager(new LinearLayoutManager(getContext(),
                            LinearLayoutManager.VERTICAL, false));
                    SubCategoryAdaptar adaptar = new SubCategoryAdaptar(response.body().getCategoryList(),
                            getContext(), new SubCategoryAdaptar.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position, String id) {
                            api.deletesubcategory(id).enqueue(new Callback<JsonObject>() {
                                @Override
                                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                                    progressDialog.dismiss();
                                    if (response.isSuccessful()&& response.body()!=null){
                                        datainitialize();
                                        Toast.makeText(getContext(), "Sub Category Delete", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<JsonObject> call, Throwable t) {

                                }
                            });
                        }
                    });
                    binding.subCat.setAdapter(adaptar);
                }
            }

            @Override
            public void onFailure(Call<SubCategoryContainer> call, Throwable t) {

            }
        });
    }
}