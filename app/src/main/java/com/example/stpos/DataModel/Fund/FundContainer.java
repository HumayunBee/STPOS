package com.example.stpos.DataModel.Fund;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FundContainer {
    @SerializedName("fund_list")
    @Expose
    private List<Fund> fundList = null;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;

    public List<Fund> getFundList() {
        return fundList;
    }

    public void setFundList(List<Fund> fundList) {
        this.fundList = fundList;
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
