package com.example.liquorland.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liquorland.Models.Drink;
import com.example.liquorland.R;
import com.example.liquorland.ui.brands.BrandsFragment;
import com.example.liquorland.ui.home.HomeFragment;

import java.util.List;

public class DrinksAdapter extends RecyclerView.Adapter<DrinksAdapter.ViewHolder> {

    List<Drink> drinks;
    Context context;


    public DrinksAdapter() {
    }

    public DrinksAdapter(Context context, List<Drink> drinks) {
        this.drinks = drinks;
        this.context = this.context;

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

        holder.drinkname.setText(drinks.get(position).getDrinkname());
        holder.drinkvolume.setText(drinks.get(position).getDrinkvolume());
        holder.drinkprice.setText(drinks.get(position).getDrinkprice());
        holder.position = holder.getAdapterPosition();




    }

    @Override
    public int getItemCount() {
        return drinks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView drinkname, drinkvolume, drinkprice;
        ImageView drinkimage;
        Integer position;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            drinkname = itemView.findViewById(R.id.txt_drink_name);
            drinkvolume = itemView.findViewById(R.id.txt_drink_volume);
            drinkprice = itemView.findViewById(R.id.txt_drink_price);
            drinkimage = itemView.findViewById(R.id.ic_drink_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Bundle bundle= new Bundle();

                    Navigation.findNavController(itemView).navigate(R.id.itemDetailFragment);

                }
            });
        }

        }
    }

