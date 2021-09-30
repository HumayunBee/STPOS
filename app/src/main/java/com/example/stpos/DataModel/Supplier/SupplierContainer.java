package com.example.stpos.DataModel.Supplier;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SupplierContainer {

    @SerializedName("supplier_list")
    @Expose
    private List<Supplier> supplierList = null;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;

    public List<Supplier> getSupplierList() {
        return supplierList;
    }

    public void setSupplierList(List<Supplier> supplierList) {
        this.supplierList = supplierList;
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
