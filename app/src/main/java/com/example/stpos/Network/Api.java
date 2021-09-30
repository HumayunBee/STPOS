package com.example.stpos.Network;


import com.example.stpos.DataModel.Brand.BrandContainer;
import com.example.stpos.DataModel.Category.CategoryContainer;
import com.example.stpos.DataModel.Customer.Customer_Model_Container;
import com.example.stpos.DataModel.Expense.ExpenseContainer;
import com.example.stpos.DataModel.Fund.FundContainer;
import com.example.stpos.DataModel.Product.ProductContainer;
import com.example.stpos.DataModel.SubCategory.SubCategoryContainer;
import com.example.stpos.DataModel.Supplier.SupplierContainer;
import com.google.gson.JsonObject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {

    @FormUrlEncoded
    @POST("api/login")
    Call<JsonObject> login(@Field("email") String email, @Field("password") String password);


    @GET("api/customer_list")
    Call<Customer_Model_Container> getcustomer();

    //    @GET("api/product_list")
//    Call<ProductContainer> getproducts(@Query("page") String page);
    @GET("api/product_list")
    Call<ProductContainer> getproducts();

    @FormUrlEncoded
    @POST("api/delete_product")
    Call<JsonObject> deleteproduct(@Field("product_id") String product_id);


    @FormUrlEncoded
    @POST("api/add_customer")
    Call<JsonObject> addcustomer(@Field("membership_no") String membership_no,
                                 @Field("customer_name") String customer_name,
                                 @Field("cell_no") String cell_no,
                                 @Field("address") String address,
                                 @Field("type") String type
    );

    @FormUrlEncoded
    @POST("api/add_category")
    Call<JsonObject> addcategory(@Field("category_name") String category_name
    );

    @FormUrlEncoded
    @POST("api/update_category")
    Call<JsonObject> updatecategory(@Field("category_name") String category_name,
                                    @Field("category_id") String category_id
    );

    @FormUrlEncoded
    @POST("api/delete_category")
    Call<JsonObject> deletecategory(@Field("category_id") String category_id);


    @POST("api/category_list")
    Call<CategoryContainer> categorylist();

    @POST("api/sub_category_list")
    Call<SubCategoryContainer> subcategorylist();

    @FormUrlEncoded
    @POST("api/update_sub_category")
    Call<JsonObject> updatesubcategory(@Field("sub_category_name") String sub_category_name,
                                       @Field("sub_category_id") String sub_category_id,
                                       @Field("category_id") String category_id

    );

    @FormUrlEncoded
    @POST("api/delete_sub_category")
    Call<JsonObject> deletesubcategory(@Field("sub_category_id") String sub_category_id);

    @FormUrlEncoded
    @POST("api/add_sub_category")
    Call<JsonObject> addsubcategorylist(@Field("sub_category_name") String sub_category_name);

    @FormUrlEncoded
    @POST("api/add_brand")
    Call<JsonObject> addbrand(@Field("brand_name") String brand_name);

    @FormUrlEncoded
    @POST("api/update_brand")
    Call<JsonObject> updatebrand(@Field("brand_name") String brand_name,
                                 @Field("brand_id") String brand_id
    );

    @FormUrlEncoded
    @POST("api/delete_brand")
    Call<JsonObject> deletebrand(@Field("brand_id") String brand_id);

    @POST("api/brand_list")
    Call<BrandContainer> brandlist();

    @POST("api/fund_list")
    Call<FundContainer> fundlistshow();

    @FormUrlEncoded
    @POST("api/add_fund")
    Call<JsonObject> addfunds(@Field("fund_name") String fund_name);

    @FormUrlEncoded
    @POST("api/update_fund")
    Call<JsonObject> updatefund(@Field("fund_name") String fund_name,
                                @Field("fund_id") String fund_id
    );


    @FormUrlEncoded
    @POST("api/delete_fund")
    Call<JsonObject> deletefund(@Field("fund_id") String fund_id);


    @POST("api/ex_head_list")
    Call<ExpenseContainer> exheadlistshow();

    @FormUrlEncoded
    @POST("api/add_ex_head")
    Call<JsonObject> addexhead(@Field("ex_head_name") String ex_head_name);

    @FormUrlEncoded
    @POST("api/update_ex_head")
    Call<JsonObject> updateexpense(@Field("ex_head_name") String ex_head_name,
                                   @Field("ex_head_id") String ex_head_id
    );

    @FormUrlEncoded
    @POST("api/delete_ex_head")
    Call<JsonObject> deleteexpensehead(@Field("ex_head_id") String ex_head_id);


    @POST("api/supplier_list")
    Call<SupplierContainer> supplierlist();

    @FormUrlEncoded
    @POST("api/update_supplier")
    Call<JsonObject> updatesupplier(@Field("name") String name,
                                    @Field("company_name") String company_name,
                                    @Field("cell_no") String cell_no,
                                    @Field("address") String address,
                                    @Field("supplier_id") String supplier_id
    );


    @FormUrlEncoded
    @POST("api/delete_supplier")
    Call<JsonObject> deletesupplier(@Field("supplier_id") String supplier_id);

    @FormUrlEncoded
    @POST("api/add_supplier")
    Call<JsonObject> addsupplier(@Field("name") String name, @Field("company_name") String company_name,
                                 @Field("cell_no") String cell_no, @Field("address") String address
    );


    @POST("api/add_product")
    Call<JsonObject> productadd(@Body RequestBody params);

}
