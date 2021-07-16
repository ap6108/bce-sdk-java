package com.baidubce.services.vca.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class FaceArtAdd extends AbstractBceRequest {

    private String image;
    private String brief;
   
   public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FaceArtAddRequest{");
        sb.append("image='").append(image).append('\'');
        sb.append(", brief='").append(brief).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
