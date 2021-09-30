package com.example.stpos.DataModel.SubCategory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubCategoryContainer {

    @SerializedName("category_list")
    @Expose
    private List<SubCategory> categoryList = null;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;

    public List<SubCategory> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<SubCategory> categoryList) {
        this.categoryList = categoryList;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
