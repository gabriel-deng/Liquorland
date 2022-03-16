package com.example.liquorland.Models;

import android.widget.ImageView;
import android.widget.TextView;

public class Drink {
    String drinkname, drinkvolume, drinkprice;
    String drinkimage;

    public Drink(String drinkname, String drinkvolume, String drinkprice, String drinkimage) {
        this.drinkname = drinkname;
        this.drinkvolume = drinkvolume;
        this.drinkprice = drinkprice;
        this.drinkimage = drinkimage;
    }

    public String getDrinkname() {
        return drinkname;
    }

    public void setDrinkname(String drinkname) {
        this.drinkname = drinkname;
    }

    public String getDrinkvolume() {
        return drinkvolume;
    }

    public void setDrinkvolume(String drinkvolume) {
        this.drinkvolume = drinkvolume;
    }

    public String getDrinkprice() {
        return drinkprice;
    }

    public void setDrinkprice(String drinkprice) {
        this.drinkprice = drinkprice;
    }

    public String getDrinkimage() {
        return drinkimage;
    }

    public void setDrinkimage(String drinkimage) {
        this.drinkimage = drinkimage;
    }
}