package com.example.stpos.DataModel.Expense;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExHead {
    @SerializedName("ex_head_id")
    @Expose
    private String exHeadId;
    @SerializedName("ex_head_name")
    @Expose
    private String exHeadName;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("modified")
    @Expose
    private String modified;

    public String getExHeadId() {
        return exHeadId;
    }

    public void setExHeadId(String exHeadId) {
        this.exHeadId = exHeadId;
    }

    public String getExHeadName() {
        return exHeadName;
    }

    public void setExHeadName(String exHeadName) {
        this.exHeadName = exHeadName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}
