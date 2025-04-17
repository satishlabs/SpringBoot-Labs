package com.satishlabs.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "orders")
public class Order {
    @Id
    private String id;  // No @GeneratedValue for MongoDB
    private String userId;
    private String product;

    public Order() {}

    public Order(String id, String userId, String product) {
        this.id = id;
        this.userId = userId;
        this.product = product;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", userId=" + userId + ", product=" + product + "]";
    }
}
