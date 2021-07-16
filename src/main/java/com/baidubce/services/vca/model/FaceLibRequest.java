package com.baidubce.services.vca.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class FaceLibRequest extends AbstractBceRequest {

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FaceLibRequest{");
        sb.append('}');
        return sb.toString();
    }
}
