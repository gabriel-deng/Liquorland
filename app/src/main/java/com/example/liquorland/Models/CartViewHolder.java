package com.example.liquorland.Models;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liquorland.R;

public class CartViewHolder extends RecyclerView.ViewHolder  {
  public   TextView drinkname, drinkvolume, drinkprice;
  public   ImageView drinkimage, remove;


    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
        drinkname = itemView.findViewById(R.id.txt_cart_drinkname);
        drinkvolume = itemView.findViewById(R.id.txt_cart_drinkvolume);
        drinkprice = itemView.findViewById(R.id.txt_cart_drinkprice);
        drinkimage = itemView.findViewById(R.id.ic_cart_image);
        remove = itemView.findViewById(R.id.ic_remove);

    }

}
