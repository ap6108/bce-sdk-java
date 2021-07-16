package com.baidubce.services.vca.model;

import java.util.Date;

public class FaceLibResultItem {
    private String userId;
    private String lib;
    private Date createTime;
    private String description;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLib() {
        return lib;
    }

    public void setLib(String lib) {
        this.lib = lib;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FaceLibsResult{");
        sb.append("userId='").append(userId).append('\'');
        sb.append(", lib='").append(lib).append('\'');
        sb.append(", createTime='").append(createTime).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
