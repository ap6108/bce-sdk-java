package com.baidubce.services.vca.model;

import com.baidubce.model.AbstractBceResponse;
import java.util.List;

public class FaceLibImageResults extends AbstractBceResponse {

    private List<String> images;

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getImages() {
        return this.images;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FaceLibImageResults{");
        sb.append("images='").append(images).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
