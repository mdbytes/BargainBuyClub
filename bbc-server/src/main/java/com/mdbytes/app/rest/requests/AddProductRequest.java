package com.mdbytes.app.rest.requests;

import lombok.Data;

@Data
public class AddProductRequest {

    private String productUrl;
    private Integer storeId;

    public AddProductRequest() {
    }

    public AddProductRequest(String productUrl, Integer storeId) {
        this.productUrl = productUrl;
        this.storeId = storeId;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }
}
