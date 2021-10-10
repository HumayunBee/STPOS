package com.example.stpos.Fragment;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.stpos.Adaptar.CustomerLedgerSpinner.CustomerLedgerSpinnerAdaptar;
import com.example.stpos.Adaptar.ProductAddAdaptar.BrandSpinnerAdaptar;
import com.example.stpos.Adaptar.ProductAddAdaptar.CategorySpinnerAdaptar;
import com.example.stpos.Adaptar.ProductAddAdaptar.SubcategorySpinnerAdaptar;
import com.example.stpos.Adaptar.ProductAddAdaptar.SupplierSpinnerAdaptar;
import com.example.stpos.DataModel.Brand.Brand;
import com.example.stpos.DataModel.Brand.BrandContainer;
import com.example.stpos.DataModel.Category.Category;
import com.example.stpos.DataModel.Category.CategoryContainer;
import com.example.stpos.DataModel.SubCategory.SubCategory;
import com.example.stpos.DataModel.SubCategory.SubCategoryContainer;
import com.example.stpos.DataModel.Supplier.Supplier;
import com.example.stpos.DataModel.Supplier.SupplierContainer;
import com.example.stpos.Network.Api;
import com.example.stpos.Network.RetrofitClient;
import com.example.stpos.R;
import com.example.stpos.Util.Constant;
import com.example.stpos.databinding.FragmentAddBrandBinding;
import com.example.stpos.databinding.FragmentAddProductBinding;
import com.google.gson.JsonObject;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;


public class AddProductFragment extends Fragment {

    public FragmentAddProductBinding binding;
    Api api;
    ProgressDialog progressDialog;
    List<Brand> brandpro;
    List<Category> categoryspin;
    List<SubCategory> subsp;
    List<Supplier> supplierpro;
    Bitmap bitmap;
    File mainimge, detailimge;
    boolean mainimage = false, detailsimage = false;
    Uri imageUrimain = null, imageUridetails = null;
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;


    public AddProductFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static AddProductFragment newInstance(String param1, String param2) {
        AddProductFragment fragment = new AddProductFragment();
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
        // return inflater.inflate(R.layout.fragment_add_product, container, false);
        binding = FragmentAddProductBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        api = RetrofitClient.get(getContext()).create(Api.class);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please wait....");
        progressDialog.setCancelable(false);
        progressDialog.show();


        binding.mainOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                permison(Constant.PICK_PHOTO_ONE);
            }
        });

        binding.detailsUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                permison(Constant.PICK_PHOTO_TWO);
            }
        });

        ///////category spinner

        api.categorylist().enqueue(new Callback<CategoryContainer>() {
            @Override
            public void onResponse(Call<CategoryContainer> call, Response<CategoryContainer> response) {
                progressDialog.dismiss();
                if (response.isSuccessful() && response.body() != null) {
                    categoryspin = response.body().getCategoryList();
                    CategorySpinnerAdaptar adaptar = new CategorySpinnerAdaptar(response.body().getCategoryList(), getLayoutInflater());
                    binding.proCategorySpin.setAdapter(adaptar);

                }
//                else {
//                    try {
//                        Log.e("rpod",response.errorBody().string());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
            }

            @Override
            public void onFailure(Call<CategoryContainer> call, Throwable t) {

            }
        });


        ////subcategory spinner
        api.subcategorylist().enqueue(new Callback<SubCategoryContainer>() {
            @Override
            public void onResponse(Call<SubCategoryContainer> call, Response<SubCategoryContainer> response) {
                progressDialog.dismiss();
                if (response.isSuccessful() && response.body() != null) {
                    subsp = response.body().
                            getCategoryList();
                    SubcategorySpinnerAdaptar adaptar = new SubcategorySpinnerAdaptar(response.body().
                            getCategoryList(), getLayoutInflater());
                    binding.proSubcategorySpin.setAdapter(adaptar);
                }
            }

            @Override
            public void onFailure(Call<SubCategoryContainer> call, Throwable t) {
                progressDialog.dismiss();
            }
        });

        ////supplier spinner

        api.supplierlist().enqueue(new Callback<SupplierContainer>() {
            @Override
            public void onResponse(Call<SupplierContainer> call, Response<SupplierContainer> response) {
                if (response.isSuccessful() && response.body() != null) {
                    progressDialog.dismiss();
                    supplierpro = response.body().getSupplierList();
                    SupplierSpinnerAdaptar sadaptar = new SupplierSpinnerAdaptar(response.body().getSupplierList(), getLayoutInflater());
                    binding.proSupplierSpin.setAdapter(sadaptar);
                }
            }

            @Override
            public void onFailure(Call<SupplierContainer> call, Throwable t) {

            }
        });

        //////add brand sppiner

        api.brandlist().enqueue(new Callback<BrandContainer>() {
            @Override
            public void onResponse(Call<BrandContainer> call, Response<BrandContainer> response) {
                if (response.isSuccessful() && response.body() != null) {

                    brandpro = response.body().getBrandList();
                    BrandSpinnerAdaptar badaptar = new BrandSpinnerAdaptar(response.body().getBrandList(),
                            getLayoutInflater());
                    binding.proBrandSpin.setAdapter(badaptar);
                }
            }

            @Override
            public void onFailure(Call<BrandContainer> call, Throwable t) {

            }
        });

        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datavalidation();
            }
        });


    }


    public void datavalidation() {
        if (!TextUtils.isEmpty(binding.proId.getText().toString())) {
            if (!TextUtils.isEmpty(binding.proName.getText().toString())) {
                if (!TextUtils.isEmpty(binding.proPurchaseRate.getText().toString())) {
                    if (!TextUtils.isEmpty(binding.proSalesRate.getText().toString())) {
                        datainitialization();
                    }
                    binding.proSalesRate.setError("Please fill out this field");
                    binding.proSalesRate.requestFocus();

                } else {
                    binding.proPurchaseRate.setError("Please fill out this field");
                    binding.proPurchaseRate.requestFocus();
                }
            } else {
                binding.proName.setError("Please fill out this field");
                binding.proName.requestFocus();
            }
        } else {
            binding.proId.setError("Please fill out this field");
            binding.proId.requestFocus();
        }
    }

    private void datainitialization() {
        progressDialog.show();
        RequestBody requestBody;

        String mainpic = "";
        String detailspic = "";

        RequestBody requestmain = null;
        RequestBody requestdetails = null;
        RequestBody attachmentEmpty = RequestBody.create(MediaType.parse("text/plain"), "");

        if (imageUrimain != null) {
            File mainfile = new File(imageUrimain.getLastPathSegment().toString());
            mainpic = mainimge.getName();
            requestmain =
                    RequestBody.create(MediaType.parse("multipart/form-data"), mainimge);
        }

        if (imageUridetails != null) {
            File detailfile = new File(imageUridetails.getLastPathSegment().toString());
            detailspic = detailimge.getName();
            requestdetails =
                    RequestBody.create(MediaType.parse("multipart/form-data"), detailimge);
        }

        requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("product_id", binding.proId.getText().toString())
                .addFormDataPart("product_name", binding.proName.getText().toString())
                .addFormDataPart("category_id", String.valueOf(categoryspin.get(binding.proCategorySpin.getSelectedItemPosition()).getCategoryId()))
                .addFormDataPart("sub_category_id", String.valueOf(subsp.get(binding.proSubcategorySpin.getSelectedItemPosition()).getSubCategoryId()))
                .addFormDataPart("supplier_id", String.valueOf(supplierpro.get(binding.proSupplierSpin.getSelectedItemPosition()).getSupplierId()))
                .addFormDataPart("brand_id", String.valueOf(brandpro.get(binding.proBrandSpin.getSelectedItemPosition()).getBrandId()))
                .addFormDataPart("size_model", binding.proSize.getText().toString())
                .addFormDataPart("product_unit", binding.proUnit.getText().toString())
                .addFormDataPart("product_rate", binding.proSalesRate.getText().toString())
                .addFormDataPart("purchase_rate", binding.proPurchaseRate.getText().toString())
                .addFormDataPart("product_details", binding.proDetails.getText().toString())
                .addFormDataPart("product_type", binding.proType.getText().toString())
                .addFormDataPart("main_image", mainpic, requestmain != null ? requestmain : attachmentEmpty)
                .addFormDataPart("details_image[]", detailspic, requestdetails != null ? requestdetails : attachmentEmpty)
                .build();
        api.productadd(requestBody).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                progressDialog.dismiss();
                if (response.isSuccessful() && response.body() != null) {
                    Navigation.findNavController(getView()).navigate(R.id.action_global_manageProductFragment);
                    Toast.makeText(getContext(), "Product Add Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        Log.e("proadd", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap b;
        if (resultCode == RESULT_OK && requestCode == Constant.PICK_PHOTO_ONE && data != null) {

            imageUrimain = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), imageUrimain);

                mainimge = new File(getContext().getCacheDir(), "Image1");
                bitmap = getResizedBitmap(bitmap, 800);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();

                bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos);
                // bitmap.compress(Bitmap.CompressFormat.JPEG, 0 /*ignored for PNG*/, bos);
                byte[] bitmapdata = bos.toByteArray();


                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(mainimge);

                } catch (FileNotFoundException e) {
                    Log.e("REQ", e.toString());
                }
                try {
                    fos.write(bitmapdata);
                    fos.flush();
                    fos.close();
                    mainimage = true;
                } catch (IOException e) {
                    Log.e("REQ", e.toString());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            if (bitmap != null) {
                binding.mainGonePic.setVisibility(View.VISIBLE);
                binding.proMainPic.setImageBitmap(bitmap);
            } else {
                Log.e("REQ", "Bitmap null");
            }


        } else if (resultCode == RESULT_OK && requestCode == Constant.PICK_PHOTO_THREE && data != null) {
            imageUridetails = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), imageUridetails);
                detailimge = new File(getContext().getCacheDir(), "Image3");
                bitmap = getResizedBitmap(bitmap, 800);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();

                bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos);
                // bitmap.compress(Bitmap.CompressFormat.JPEG, 0 /*ignored for PNG*/, bos);
                byte[] bitmapdata = bos.toByteArray();


                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(detailimge);

                } catch (FileNotFoundException e) {
                    Log.e("REQ", e.toString());
                }
                try {
                    fos.write(bitmapdata);
                    fos.flush();
                    fos.close();
                    detailsimage = true;
                } catch (IOException e) {
                    Log.e("REQ", e.toString());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            if (bitmap != null) {
                binding.detailsImage.setImageBitmap(bitmap);
                binding.goneDetailsImage.setVisibility(View.VISIBLE);
            } else {
                Log.e("REQ", "Bitmap null");
            }

        }

    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    void permison(int requestcode) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 201);

        } else {
            Log.e("REQ", "Inside");
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            /*Intent intent = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);*/
            //intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
            startActivityForResult(intent, requestcode);
        }
    }
}