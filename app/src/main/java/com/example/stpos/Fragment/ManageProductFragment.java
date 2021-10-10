package com.example.stpos.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.stpos.Adaptar.PaginationScrollListener;
import com.example.stpos.Adaptar.ProductListAdaptar.AdaptarProduct;
import com.example.stpos.Adaptar.ProductListAdaptar.ProductListAdaptar;
import com.example.stpos.DataModel.Product.Product;
import com.example.stpos.DataModel.Product.ProductContainer;
import com.example.stpos.Network.Api;
import com.example.stpos.Network.RetrofitClient;
import com.example.stpos.R;
import com.example.stpos.databinding.FragmentManageCategoryBinding;
import com.example.stpos.databinding.FragmentManageProductBinding;
import com.google.gson.JsonObject;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageProductFragment extends Fragment {

    SearchableSpinner spinner, spinnerone;
    boolean isLoading = false;
    boolean isLastPage = false;
    int currentPage = 1;
    int TOTAL_PAGES = 0;

    ProductListAdaptar adaptar;
    public FragmentManageProductBinding binding;
    Api api;
    ProgressDialog progressDialog;

    public ManageProductFragment() {
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
        // return inflater.inflate(R.layout.fragment_manage_product, container, false);
        binding = FragmentManageProductBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        api = RetrofitClient.get(getContext()).create(Api.class);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please wait....");
        progressDialog.setCancelable(false);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
//                LinearLayoutManager.VERTICAL, false);
//
//        binding.productList.setHasFixedSize(true);
//        binding.productList.setLayoutManager(linearLayoutManager);
//        adaptar=new ProductListAdaptar(
//                getContext(), new ProductListAdaptar.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position, String id) {
//
//            }
//        });
//        binding.productList.setAdapter(adaptar);

//        binding.productList.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
//            @Override
//            protected void loadMoreItems() {
//                isLoading = true;
//                currentPage += 1;
//                loadNextPage();
//            }
//
//            @Override
//            public boolean isLastPage() {
//                return isLastPage;
//            }
//
//            @Override
//            public boolean isLoading() {
//                return isLoading;
//            }
//        });

        // loadFirstPage();
        datainitialize();

        binding.addNewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_global_addProductFragment);
            }
        });

    }

    private void datainitialize() {

        progressDialog.show();
        api.getproducts().enqueue(new Callback<ProductContainer>() {
            @Override
            public void onResponse(Call<ProductContainer> call, Response<ProductContainer> response) {
                progressDialog.dismiss();
                if (response.isSuccessful() && response.body() != null) {
                    binding.productList.setHasFixedSize(true);
                    binding.productList.setLayoutManager(new LinearLayoutManager(getContext(),
                            LinearLayoutManager.VERTICAL, false));
                    AdaptarProduct adaptar = new AdaptarProduct(response.body().getProducts(), getContext(), new AdaptarProduct.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position, String id) {
                            api.deleteproduct(id).enqueue(new Callback<JsonObject>() {
                                @Override
                                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                                    progressDialog.dismiss();
                                    if (response.isSuccessful() && response.body() != null) {
                                        datainitialize();
                                        Toast.makeText(getContext(), "Product Delete", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<JsonObject> call, Throwable t) {

                                }
                            });
                        }
                    });

                    binding.productList.setAdapter(adaptar);

                } else {
                    try {
                        Log.e("pro", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ProductContainer> call, Throwable t) {

            }
        });


    }


//    private void loadNextPage() {
//        api.getproducts(String.valueOf(currentPage)).enqueue(new Callback<ProductContainer>() {
//            @Override
//            public void onResponse(Call<ProductContainer> call, Response<ProductContainer> response) {
//                progressDialog.dismiss();
//                if (response.isSuccessful() && response.body() != null) {
//                    adaptar.removeLoadingFooter();
//                    isLoading = false;
//
//                    List<Product> results = response.body().getProducts();
//                    adaptar.addAll(results);
//
//                    if (currentPage != TOTAL_PAGES) adaptar.addLoadingFooter();
//                    else isLastPage = true;
//                }
//
//
//
//
////                else {
////                    try {
////                        Log.e("pro",response.errorBody().string());
////                    } catch (IOException e) {
////                        e.printStackTrace();
////                    }
////                }
//            }
//
//            @Override
//            public void onFailure(Call<ProductContainer> call, Throwable t) {
//
//            }
//        });
//
//
//    }
//    private void loadFirstPage() {
//        progressDialog.show();
//        api.getproducts(String.valueOf(currentPage)).enqueue(new Callback<ProductContainer>() {
//            @Override
//            public void onResponse(Call<ProductContainer> call, Response<ProductContainer> response) {
//                List<Product> results = response.body().getProducts();
//                progressDialog.dismiss();
//                adaptar.addAll(results);
//                TOTAL_PAGES=response.body().getTotalPage();
//                Log.d("tesst","from load first");
//                Log.d("tesst",String.valueOf(response.body().getCurrentPage()));
//                if (currentPage <= TOTAL_PAGES) adaptar.addLoadingFooter();
//                else isLastPage = true;
//            }
//
//            @Override
//            public void onFailure(Call<ProductContainer> call, Throwable t) {
//                 Log.d("tesst",t.toString());
//
//            }
//
//        });
//    }
}