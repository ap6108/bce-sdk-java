package com.baidubce.services.vca.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class FaceLibCreate extends AbstractBceRequest {
    private String lib;
    private String description;

    public String getLib() {
        return lib;
    }

    public void setLib(String lib) {
        this.lib = lib;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FaceLibCreate{");
        sb.append("lib='").append(lib).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
 