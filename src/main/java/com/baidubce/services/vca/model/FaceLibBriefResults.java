package com.baidubce.services.vca.model;

import com.baidubce.model.AbstractBceResponse;
import java.util.List;

public class FaceLibBriefResults extends AbstractBceResponse {

    private List<String> briefs;

    public void setBriefs(List<String> briefs) {
        this.briefs = briefs;
    }

    public List<String> getBriefs() {
        return this.briefs;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FaceLibBriefResults{");
        sb.append("briefs='").append(briefs).append('\'');
        sb.append('}');
        return sb.toString();
    }
    
}
