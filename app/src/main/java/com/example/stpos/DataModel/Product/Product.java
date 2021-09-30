
package com.example.stpos.DataModel.Product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("sub_category_id")
    @Expose
    private String subCategoryId;
    @SerializedName("supplier_id")
    @Expose
    private String supplierId;
    @SerializedName("brand_id")
    @Expose
    private String brandId;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("product_details")
    @Expose
    private String productDetails;
    @SerializedName("main_image")
    @Expose
    private Object mainImage;
    @SerializedName("size_model")
    @Expose
    private String sizeModel;
    @SerializedName("product_type")
    @Expose
    private String productType;
    @SerializedName("product_unit")
    @Expose
    private String productUnit;
    @SerializedName("stock_qty")
    @Expose
    private String stockQty;
    @SerializedName("product_rate")
    @Expose
    private String productRate;
    @SerializedName("purchase_rate")
    @Expose
    private String purchaseRate;
    @SerializedName("unit_rate")
    @Expose
    private String unitRate;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("modified")
    @Expose
    private String modified;
    @SerializedName("sub_category_name")
    @Expose
    private String subCategoryName;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("brand_name")
    @Expose
    private String brandName;
    @SerializedName("supplier_name")
    @Expose
    private String supplierName;
    @SerializedName("company_name")
    @Expose
    private String companyName;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(String productDetails) {
        this.productDetails = productDetails;
    }

    public Object getMainImage() {
        return mainImage;
    }

    public void setMainImage(Object mainImage) {
        this.mainImage = mainImage;
    }

    public String getSizeModel() {
        return sizeModel;
    }

    public void setSizeModel(String sizeModel) {
        this.sizeModel = sizeModel;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public String getStockQty() {
        return stockQty;
    }

    public void setStockQty(String stockQty) {
        this.stockQty = stockQty;
    }

    public String getProductRate() {
        return productRate;
    }

    public void setProductRate(String productRate) {
        this.productRate = productRate;
    }

    public String getPurchaseRate() {
        return purchaseRate;
    }

    public void setPurchaseRate(String purchaseRate) {
        this.purchaseRate = purchaseRate;
    }

    public String getUnitRate() {
        return unitRate;
    }

    public void setUnitRate(String unitRate) {
        this.unitRate = unitRate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

}
