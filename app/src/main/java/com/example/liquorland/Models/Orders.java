package com.example.liquorland.Models;

public class Orders {
    String created_at, ref_number, delivery_status;
    String price;

    public Orders() {
    }

    public Orders(String created_at, String ref_number, String delivery_status, String price) {
        this.created_at = created_at;
        this.ref_number = ref_number;
        this.delivery_status = delivery_status;
        this.price = price;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getRef_number() {
        return ref_number;
    }

    public void setRef_number(String ref_number) {
        this.ref_number = ref_number;
    }


    public String getDelivery_status() {
        return delivery_status;
    }

    public void setDelivery_status(String delivery_status) {
        this.delivery_status = delivery_status;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
