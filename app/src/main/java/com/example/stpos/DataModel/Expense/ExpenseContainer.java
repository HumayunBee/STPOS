package com.example.stpos.DataModel.Expense;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ExpenseContainer {

    @SerializedName("ex_head_list")
    @Expose
    private List<ExHead> exHeadList = null;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;

    public List<ExHead> getExHeadList() {
        return exHeadList;
    }

    public void setExHeadList(List<ExHead> exHeadList) {
        this.exHeadList = exHeadList;
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
