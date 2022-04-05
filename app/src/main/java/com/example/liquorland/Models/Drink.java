package com.example.liquorland.Models;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Drink {

  @Id public long id;

    String drinkname, drinkvolume, drinkprice, category;
    String drinkimage;

    public Drink(String drinkname, String drinkvolume, String drinkprice, String category, String drinkimage, String category_name) {
        this.drinkname = drinkname;
        this.drinkvolume = drinkvolume;
        this.drinkprice = drinkprice;
        this.category = category;
        this.drinkimage = drinkimage;

    }

    public Drink(String drinkname) {
        this.drinkname = drinkname;
    }

    public Drink() {
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDrinkimage() {
        return drinkimage;
    }

    public void setDrinkimage(String drinkimage) {
        this.drinkimage = drinkimage;
    }

}