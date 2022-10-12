package com.example.liquorland.Models;


public class Category {

   public Integer id;
  String category_name;

  public Category(int id, String category_name) {
    this.id=id;
    this.category_name = category_name;
  }

  public Category() {
  }

  public Category(String category_name) {
    this.category_name = category_name;
  }

  public String getCategory_name() {
    return category_name;
  }

  public void setCategory_name(String category_name) {
    this.category_name = category_name;
  }
}
