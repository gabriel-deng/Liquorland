package com.example.liquorland.Models;


public class CartItem {

    String cartdrinkname, cartdrinkvolume, cartdrinkprice;
    String cartdrinkimage, date, time, cart_itemid;

    public CartItem(String cartdrinkname, String cartdrinkvolume, String cartdrinkprice, String cartdrinkimage, String date, String time, String cart_itemid) {
        this.cartdrinkname = cartdrinkname;
        this.cartdrinkvolume = cartdrinkvolume;
        this.cartdrinkprice = cartdrinkprice;
        this.cartdrinkimage = cartdrinkimage;
        this.date = date;
        this.time = time;
        this.cart_itemid = cart_itemid;
    }

    public CartItem() {
    }

    public String getCart_itemid() {
        return cart_itemid;
    }

    public void setCart_itemid(String cart_itemid) {
        this.cart_itemid = cart_itemid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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


    public String getCartdrinkimage() {
        return cartdrinkimage;
    }

    public void setCartdrinkimage(String cartdrinkimage) {
        this.cartdrinkimage = cartdrinkimage;
    }
}
