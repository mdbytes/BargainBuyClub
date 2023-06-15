package com.mdbytes.app.rest.requests;

public class GetProductByUrlRequest {
    private String productUrl;

    public GetProductByUrlRequest() {
    }

    public GetProductByUrlRequest(String productUrl) {
        this.productUrl = productUrl;

    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }
}
