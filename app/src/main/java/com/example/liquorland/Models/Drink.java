package com.example.liquorland.Models;


public class Drink  {



public String id;

    String drinkname, drinkvolume, drinkprice, category, imageUrl;
    String drinkimage;

    public Drink(String id, String drinkname, String drinkvolume, String drinkprice, String category, String imageUrl, String drinkimage) {
        this.id = id;
        this.drinkname = drinkname;
        this.drinkvolume = drinkvolume;
        this.drinkprice = drinkprice;
        this.category = category;
        this.imageUrl = imageUrl;
        this.drinkimage = drinkimage;
    }
    public Drink() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Drink(String drinkname) {
        this.drinkname = drinkname;
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


}