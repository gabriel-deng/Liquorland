package com.example.liquorland.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liquorland.Models.Drink;
import com.example.liquorland.R;
import com.example.liquorland.ui.home.HomeFragment;
import com.google.android.material.snackbar.Snackbar;

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
        View view = LayoutInflater
                .from(parent.getContext()).inflate( R.layout.item_drink, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull DrinksAdapter.ViewHolder holder, int position) {

        Drink drink= drinks.get(position);

        holder.drinkname.setText(drinks.get(position).getDrinkname());
        holder.drinkvolume.setText(drinks.get(position).getDrinkvolume());
        holder.drinkprice.setText(drinks.get(position).getDrinkprice());

        if(drink.getDrinkimage()!= null){
            holder.drinkimage.setImageURI(Uri.parse(drink.getDrinkimage()));
        }
        holder.position = holder.getAdapterPosition();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeFragment.goToDetails(drinks.get(holder.getAdapterPosition()), holder.getAdapterPosition());
            }
        });


    }

    @Override
    public int getItemCount() {
        return drinks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView drinkname, drinkvolume, drinkprice;
        ImageView drinkimage, add;
        Integer position;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            drinkname = itemView.findViewById(R.id.txt_cart_drink_name);
            drinkvolume = itemView.findViewById(R.id.txt_cart_drink_volume);
            drinkprice = itemView.findViewById(R.id.txt_cart_drink_price);
            drinkimage = itemView.findViewById(R.id.ic_cart_drink_image);
            add= itemView.findViewById(R.id.ic_remove_fromcart);

            add.setOnClickListener(view -> {
                Snackbar.make(view, "Item successfully added to cart", Snackbar.LENGTH_LONG).show();

            });

        }

        }

    }

