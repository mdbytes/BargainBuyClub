package com.mdbytes.app.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "stores")
public class Store {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "store_id")
    private int storeId;
    @Basic
    @Column(name = "store_name")
    private String storeName;
    @Basic
    @Column(name = "store_url")
    private String storeUrl;
    @Basic
    @Column(name = "price_query")
    private String priceQuery;
    @Basic
    @Column(name = "product_name_query")
    private String productNameQuery;

    @OneToMany(mappedBy = "store")
    private List<Product> products;

    public Store() {
    }

    public Store(String storeName, String storeUrl, String priceQuery, String productNameQuery) {
        this.storeName = storeName;
        this.storeUrl = storeUrl;
        this.priceQuery = priceQuery;
        this.productNameQuery = productNameQuery;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreUrl() {
        return storeUrl;
    }

    public void setStoreUrl(String storeUrl) {
        this.storeUrl = storeUrl;
    }

    public String getPriceQuery() {
        return priceQuery;
    }

    public void setPriceQuery(String priceQuery) {
        this.priceQuery = priceQuery;
    }

    public String getProductNameQuery() {
        return productNameQuery;
    }

    public void setProductNameQuery(String productNameQuery) {
        this.productNameQuery = productNameQuery;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return storeId == store.storeId && Objects.equals(storeName, store.storeName) && Objects.equals(storeUrl, store.storeUrl) && Objects.equals(priceQuery, store.priceQuery) && Objects.equals(productNameQuery, store.productNameQuery);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeId, storeName, storeUrl, priceQuery, productNameQuery);
    }

    @Override
    public String toString() {
        return "Store{" +
                "storeId=" + storeId +
                ", storeName='" + storeName + '\'' +
                ", storeUrl='" + storeUrl + '\'' +
                ", priceQuery='" + priceQuery + '\'' +
                ", productNameQuery='" + productNameQuery + '\'' +
                '}';
    }
}