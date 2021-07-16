package com.baidubce.services.vca.model;

import com.baidubce.model.AbstractBceResponse;
import java.util.List;

public class FaceLibsResults extends AbstractBceResponse {

    private List<FaceLibResultItem> libs;

    public void setLibs(List<FaceLibResultItem> libs) {
        this.libs = libs;
    }

    public List<FaceLibResultItem> getLibs() {
        return this.libs;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FaceLibsResults{");
        sb.append("libs='").append(libs).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
