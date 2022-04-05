package com.example.liquorland.Models;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Category {

  @Id public long id;
  String category_name;

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
