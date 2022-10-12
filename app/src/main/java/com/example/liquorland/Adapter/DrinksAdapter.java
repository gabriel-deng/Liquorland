package com.example.liquorland.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.liquorland.Models.Drink;
import com.example.liquorland.R;
import com.example.liquorland.ui.home.HomeFragment;

import java.util.List;

public class DrinksAdapter extends RecyclerView.Adapter<DrinksAdapter.ViewHolder> {


    List<Drink> drinks;
    Context context;
    HomeFragment homeFragment;

    public DrinksAdapter(List<Drink> drinks, Context context, HomeFragment homeFragment) {
        this.drinks = drinks;
        this.context = context;
        this.homeFragment = homeFragment;
    }



    @NonNull
    @Override
    public DrinksAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_drink, parent, false);

        return new ViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull DrinksAdapter.ViewHolder holder, int position) {



        Drink drink = drinks.get(position);

        holder.drinkname.setText(drink.getDrinkname());
        holder.drinkvolume.setText(drink.getDrinkvolume());
        holder.drinkprice.setText(drink.getDrinkprice());
        Glide.with(holder.itemView.getContext()).load(drink.getImageUrl()).into(holder.drinkimage);
        holder.id= drink.getId();


    }

    @Override
    public int getItemCount() {

        return drinks.size();

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView drinkname, drinkvolume, drinkprice;
        ImageView drinkimage, add;
        String  id;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);



            drinkname = itemView.findViewById(R.id.txt_drink_name);
            drinkvolume = itemView.findViewById(R.id.txt_drink_volume);
            drinkprice = itemView.findViewById(R.id.txt_drink_price);
            drinkimage = itemView.findViewById(R.id.ic_drink_image);
            add = itemView.findViewById(R.id.ic_add_tocart);

            add.setOnClickListener(view -> {
                    homeFragment.AddToCart(drinks.get(getAbsoluteAdapterPosition()));

            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                          homeFragment.goToDetails(drinks.get(getAbsoluteAdapterPosition()));
                }
            });

        }

    }
}



