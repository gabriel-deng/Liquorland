package com.example.liquorland.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liquorland.Checkout;
import com.example.liquorland.ItemDetail;
import com.example.liquorland.MainActivity;
import com.example.liquorland.Models.Drink;
import com.example.liquorland.R;
import com.example.liquorland.ui.brands.BrandsFragment;
import com.example.liquorland.ui.home.HomeFragment;

import java.util.List;

public class DrinksAdapter extends RecyclerView.Adapter<DrinksAdapter.ViewHolder> {

    List<Drink> drinks;
    Context context;
    HomeFragment homeFragment;
    BrandsFragment brandsFragment;
    FragmentTransaction transaction;
    ItemDetail itemDetail;

    public DrinksAdapter() {
    }


    public DrinksAdapter(ItemDetail itemDetail) {
        this.itemDetail = itemDetail;
    }

    public DrinksAdapter(Context context, List<Drink> drinks) {
        this.drinks = drinks;
        this.context = this.context;
        this.homeFragment = homeFragment;
        this.brandsFragment = brandsFragment;

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

//                        FragmentManager fragmentManager = itemDetail.getActivity().getSupportFragmentManager();
//                         transaction = fragmentManager.beginTransaction();
//                         transaction.replace(R.id.nav_host_fragment_activity_main, itemDetail);
//                         transaction.commit();
               //     Toast.makeText(context, "Should move to Itemdetail", Toast.LENGTH_SHORT).show();
                }
            });
        }

        }
    }

