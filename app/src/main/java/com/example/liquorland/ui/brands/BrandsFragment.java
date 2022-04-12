package com.example.liquorland.ui.brands;


import static android.content.Context.LAYOUT_INFLATER_SERVICE;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liquorland.Adapter.BrandsAdapter;
import com.example.liquorland.Models.Drink;
import com.example.liquorland.ObjectBox;
import com.example.liquorland.R;
import com.example.liquorland.databinding.BrandsFragmentBinding;
import com.example.liquorland.ui.ItemDetailFragment;

import java.util.ArrayList;

import io.objectbox.Box;

public class BrandsFragment extends Fragment  {

    private BrandsViewModel brandsViewModel;
    private BrandsFragmentBinding binding;

    Context context= getContext();


    ArrayList<Drink> drinks;
    BrandsAdapter brandsAdapter;
    RecyclerView brandsrecyclerview;

    Box<Drink> productname;

    TextView  champagne, brandy, tequila, beer, cider, rum, cognac, wines, spirits, whisky, vodka, gin;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        brandsViewModel= new ViewModelProvider(this).get(BrandsViewModel.class);

        binding= BrandsFragmentBinding.inflate(inflater, container, false);
        View root= binding.getRoot();

        productname= ObjectBox.get().boxFor(Drink.class);

        vodka=root.findViewById(R.id.txt_vodka);
        vodka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowVodkaList();

            }
        });
        whisky=root.findViewById(R.id.txt_whisky);

        whisky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowWhiskyList();
            }
        });
        spirits=root.findViewById(R.id.txt_spirits);
        spirits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowSpiritsList();
            }
        });
        wines=root.findViewById(R.id.txt_wine);
        wines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowWineList();
            }
        });
        cognac=root.findViewById(R.id.txt_cognac);
        cognac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowCognacList();
            }
        });
        rum=root.findViewById(R.id.txt_rum);
        rum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowRumList();
            }
        });
        tequila=root.findViewById(R.id.txt_tequila);
        tequila.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowTequilaList();
            }
        });
        cider=root.findViewById(R.id.txt_cider);
        cider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowCiderList();
            }
        });
        gin=root.findViewById(R.id.txt_gin);
        gin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowGinList();
            }
        });
        brandy=root.findViewById(R.id.txt_brandy);
        brandy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowBrandyList();
            }
        });

        beer=root.findViewById(R.id.txt_beer);
        beer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowBeerList();
            }
        });

        champagne=root.findViewById(R.id.txt_champagne);

        champagne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Showchampagne();
            }
        });
        return root;
    }

   public void Showchampagne(){
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.brands_list, null);

        drinks= (ArrayList<Drink>) productname.getAll();
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.showAtLocation(champagne, Gravity.START, 0, 0);
        popupWindow.update(champagne, width, height);

        brandsrecyclerview=popupView.findViewById(R.id.brands_recyclerview);
        brandsrecyclerview.setNestedScrollingEnabled(true);
        brandsrecyclerview.setLayoutManager(new LinearLayoutManager(context));
        brandsAdapter= new BrandsAdapter(drinks,context, this);
        brandsrecyclerview.setAdapter(brandsAdapter);



    }
    public void ShowBeerList(){
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.brands_list, null);


        drinks= (ArrayList<Drink>) productname.getAll();
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.showAtLocation(popupView, Gravity.NO_GRAVITY, 0, 0);


        brandsrecyclerview=popupView.findViewById(R.id.brands_recyclerview);
        brandsrecyclerview.setNestedScrollingEnabled(true);
        brandsrecyclerview.setLayoutManager(new GridLayoutManager(getContext(), LinearLayoutManager.VERTICAL));
        brandsAdapter= new BrandsAdapter(drinks, context, this);
        brandsrecyclerview.setAdapter(brandsAdapter);


    }
    public void ShowBrandyList() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.brands_list, null);

        drinks= (ArrayList<Drink>) productname.getAll();

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.showAtLocation(brandy, Gravity.END, 0, 0);


        brandsrecyclerview = popupView.findViewById(R.id.brands_recyclerview);
        brandsrecyclerview.setNestedScrollingEnabled(true);
        brandsrecyclerview.setLayoutManager(new GridLayoutManager(getContext(), LinearLayoutManager.VERTICAL));
        brandsAdapter = new BrandsAdapter(drinks, context, this);
        brandsrecyclerview.setAdapter(brandsAdapter);


    }
    public void ShowCiderList() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.brands_list, null);


        drinks= (ArrayList<Drink>) productname.getAll();
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.showAtLocation(cider, Gravity.END, 0, 0);


        brandsrecyclerview = popupView.findViewById(R.id.brands_recyclerview);
        brandsrecyclerview.setNestedScrollingEnabled(true);
        brandsrecyclerview.setLayoutManager(new GridLayoutManager(getContext(), LinearLayoutManager.VERTICAL));
        brandsAdapter = new BrandsAdapter(drinks, context, this);
        brandsrecyclerview.setAdapter(brandsAdapter);



    }
    public void ShowTequilaList() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.brands_list, null);

        drinks= (ArrayList<Drink>) productname.getAll();

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.showAtLocation(tequila, Gravity.END, 0, 0);


        brandsrecyclerview = popupView.findViewById(R.id.brands_recyclerview);
        brandsrecyclerview.setNestedScrollingEnabled(true);
        brandsrecyclerview.setLayoutManager(new GridLayoutManager(getContext(), LinearLayoutManager.VERTICAL));
        brandsAdapter = new BrandsAdapter(drinks, context, this);
        brandsrecyclerview.setAdapter(brandsAdapter);



    }
    public void ShowRumList() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.brands_list, null);

        drinks= (ArrayList<Drink>) productname.getAll();

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.showAtLocation(rum, Gravity.END, 0, 0);


        brandsrecyclerview = popupView.findViewById(R.id.brands_recyclerview);
        brandsrecyclerview.setNestedScrollingEnabled(true);
        brandsrecyclerview.setLayoutManager(new GridLayoutManager(getContext(), LinearLayoutManager.VERTICAL));
        brandsAdapter = new BrandsAdapter(drinks, context, this);
        brandsrecyclerview.setAdapter(brandsAdapter);


    }
    public void ShowCognacList() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.brands_list, null);

        drinks= (ArrayList<Drink>) productname.getAll();

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.showAtLocation(cognac, Gravity.END, 0, 0);


        brandsrecyclerview = popupView.findViewById(R.id.brands_recyclerview);
        brandsrecyclerview.setNestedScrollingEnabled(true);
        brandsrecyclerview.setLayoutManager(new GridLayoutManager(getContext(), LinearLayoutManager.VERTICAL));
        brandsAdapter = new BrandsAdapter(drinks, context, this);
        brandsrecyclerview.setAdapter(brandsAdapter);



    }
    public void ShowWineList() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.brands_list, null);


        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.showAtLocation(wines, Gravity.START, 0, 0);

        drinks= (ArrayList<Drink>) productname.getAll();

        brandsrecyclerview = popupView.findViewById(R.id.brands_recyclerview);
        brandsrecyclerview.setNestedScrollingEnabled(true);
        brandsrecyclerview.setLayoutManager(new GridLayoutManager(getContext(), LinearLayoutManager.VERTICAL));
        brandsAdapter = new BrandsAdapter(drinks, context, this);
        brandsrecyclerview.setAdapter(brandsAdapter);


    }
    public void ShowSpiritsList() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.brands_list, null);

        drinks= (ArrayList<Drink>) productname.getAll();
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.showAtLocation(spirits, Gravity.END, 0, 0);


        brandsrecyclerview = popupView.findViewById(R.id.brands_recyclerview);
        brandsrecyclerview.setNestedScrollingEnabled(true);
        brandsrecyclerview.setLayoutManager(new GridLayoutManager(getContext(), LinearLayoutManager.VERTICAL));
        brandsAdapter = new BrandsAdapter(drinks, context, this);
        brandsrecyclerview.setAdapter(brandsAdapter);


    }
    public void ShowWhiskyList() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.brands_list, null);

        drinks= (ArrayList<Drink>) productname.getAll();

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.showAtLocation(whisky, Gravity.START, 0, 0);


        brandsrecyclerview = popupView.findViewById(R.id.brands_recyclerview);
        brandsrecyclerview.setNestedScrollingEnabled(true);
        brandsrecyclerview.setLayoutManager(new GridLayoutManager(getContext(), LinearLayoutManager.VERTICAL));
        brandsAdapter = new BrandsAdapter(drinks, context, this);
        brandsrecyclerview.setAdapter(brandsAdapter);


    }
    public void ShowGinList() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.brands_list, null);

        drinks= (ArrayList<Drink>) productname.getAll();

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.showAtLocation(gin, Gravity.START, 0, 0);

        brandsrecyclerview = popupView.findViewById(R.id.brands_recyclerview);
        brandsrecyclerview.setNestedScrollingEnabled(true);
        brandsrecyclerview.setLayoutManager(new GridLayoutManager(getContext(), LinearLayoutManager.VERTICAL));
        brandsAdapter = new BrandsAdapter(drinks, context, this);
        brandsrecyclerview.setAdapter(brandsAdapter);


    }
    public void ShowVodkaList() {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.brands_list, null);

        drinks= (ArrayList<Drink>) productname.getAll();

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.showAtLocation(vodka, Gravity.START, 0, 0);


        brandsrecyclerview = popupView.findViewById(R.id.brands_recyclerview);
        brandsrecyclerview.setNestedScrollingEnabled(true);
        brandsrecyclerview.setLayoutManager(new GridLayoutManager(getContext(), LinearLayoutManager.VERTICAL));
        brandsAdapter = new BrandsAdapter(drinks, context, this);
        brandsrecyclerview.setAdapter(brandsAdapter);
    }
    
    public  void loaddrinksdetails(Drink drink, Integer position){
        Bundle bundle= new Bundle();
        bundle.putString("drink name", drink.getDrinkname());
        bundle.putString("drink price", drink.getDrinkprice());
        bundle.putString("drink volume", drink.getDrinkvolume());
        bundle.putString("drink image", drink.getDrinkimage());
        ItemDetailFragment detailFragment = new ItemDetailFragment();
        detailFragment.setArguments(bundle);
        Navigation.findNavController(requireView()).navigate(R.id.itemDetailFragment, bundle);


    }
}