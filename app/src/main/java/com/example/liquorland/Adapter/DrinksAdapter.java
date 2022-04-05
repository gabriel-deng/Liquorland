package com.example.liquorland.Adapter;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liquorland.Models.Drink;
import com.example.liquorland.R;

import java.util.List;

public class DrinksAdapter extends RecyclerView.Adapter<DrinksAdapter.ViewHolder> {

    List<Drink> drinks;
    private final Context context;



    public DrinksAdapter(Context context) {
        this.context = context;
    }


    public DrinksAdapter(List<Drink> drinks, Context context) {
    this.drinks = drinks;
        this.context = context;
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


    }

    @Override
    public int getItemCount() {
        if(drinks != null){
           return drinks.size();
        }
        return 0;
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
                private int Id;

                @Override
                public void onClick(View v) {
                    Bundle bundle= new Bundle();
                    bundle.putString("Drink_name", String.valueOf(drinkname));
                    bundle.putString("Drink_volume", String.valueOf(drinkvolume));
                    bundle.putString("Drink_price", String.valueOf(drinkprice));
                    Navigation.findNavController(itemView).navigate(R.id.itemDetailFragment);
                    Toast.makeText(v.getContext(),"Element " + getAbsoluteAdapterPosition() + " clicked", Toast.LENGTH_SHORT).show();
                }
            });
        }

        }

    }

