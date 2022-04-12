package com.example.liquorland.Models;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class CartItem {
    @Id public long id;
    String cartdrinkname, cartdrinkvolume, cartdrinkprice, cartcategory;
    String cartdrinkimage;

    public CartItem(String cartdrinkname, String cartdrinkvolume, String cartdrinkprice, String cartcategory, String cartdrinkimage) {
        this.cartdrinkname = cartdrinkname;
        this.cartdrinkvolume = cartdrinkvolume;
        this.cartdrinkprice = cartdrinkprice;
        this.cartcategory = cartcategory;
        this.cartdrinkimage = cartdrinkimage;
    }

    public CartItem() {
    }


    public String getCartdrinkname() {
        return cartdrinkname;
    }

    public void setCartdrinkname(String cartdrinkname) {
        this.cartdrinkname = cartdrinkname;
    }

    public String getCartdrinkvolume() {
        return cartdrinkvolume;
    }

    public void setCartdrinkvolume(String cartdrinkvolume) {
        this.cartdrinkvolume = cartdrinkvolume;
    }

    public String getCartdrinkprice() {
        return cartdrinkprice;
    }

    public void setCartdrinkprice(String cartdrinkprice) {
        this.cartdrinkprice = cartdrinkprice;
    }

    public String getCartcategory() {
        return cartcategory;
    }

    public void setCartcategory(String cartcategory) {
        this.cartcategory = cartcategory;
    }

    public String getCartdrinkimage() {
        return cartdrinkimage;
    }

    public void setCartdrinkimage(String cartdrinkimage) {
        this.cartdrinkimage = cartdrinkimage;
    }
}
