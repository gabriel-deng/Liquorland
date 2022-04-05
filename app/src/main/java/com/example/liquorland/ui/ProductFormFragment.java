package com.example.liquorland.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.liquorland.Adapter.CategoriesAdapter;
import com.example.liquorland.Models.Category;
import com.example.liquorland.Models.Drink;
import com.example.liquorland.ObjectBox;
import com.example.liquorland.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;

public class ProductFormFragment extends Fragment {

    View root;
    Context context;
    Uri image_uri;

    TextInputEditText productname, productvolume, productprice;
    ImageView productimage, save;

    List<Category> categories;
    RecyclerView categoryrecyclerview;
    CategoriesAdapter categoriesAdapter;

    static final int GALLERY_REQUEST_CODE = 214;


    private Box<Drink> DrinksDetails;

    public ProductFormFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DrinksDetails = ObjectBox.get().boxFor(Drink.class);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root=inflater.inflate(R.layout.fragment_product_form, container, false);

        ArrayList<Category> categories= ActualCategories();

        save=root.findViewById(R.id.img_save);

        productname=root.findViewById(R.id.productname);
        productprice=root.findViewById(R.id.productprice);
        productvolume=root.findViewById(R.id.productvolume);
        productimage=root.findViewById(R.id.img_productimage);

        categoryrecyclerview=root.findViewById(R.id.categoryrecyclerview);
        categoryrecyclerview.setNestedScrollingEnabled(true);
        categoryrecyclerview.setHasFixedSize(true);
        categoryrecyclerview.setLayoutManager(new StaggeredGridLayoutManager(1, LinearLayoutManager.HORIZONTAL));

        categoriesAdapter= new CategoriesAdapter(categories, context);
        categoryrecyclerview.setAdapter(categoriesAdapter);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddProductsDetails();
                Toast.makeText(getContext(), "Item successfully saved", Toast.LENGTH_SHORT).show();
            }
        });

        productimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PickImageFromGallery();
                VerifyPermissions();
            }
        });

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
    public void PickImageFromGallery(){

        Intent pickFromGallery = new Intent(Intent.ACTION_PICK);
        pickFromGallery.setType("image/");
        String[] mimeTypes = { "image/jpeg", "image/png", "image/svg"};
        pickFromGallery.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);

            startActivityForResult(pickFromGallery, GALLERY_REQUEST_CODE);

    }

    public void VerifyPermissions(){
        String[] permissions= {
                Manifest.permission.READ_EXTERNAL_STORAGE
        };

        if(ContextCompat.checkSelfPermission(this.requireActivity(), permissions[0])
                == PackageManager.PERMISSION_GRANTED){

            PickImageFromGallery();
        }
        else{
            ActivityCompat.requestPermissions(requireActivity(), permissions, 214);
        }
    }

    public void AddProductsDetails(){

        Drink AddDrink= new Drink();

        if (image_uri == null){
            Toast.makeText(getContext(), "Please select an image from gallery", Toast.LENGTH_SHORT).show();
        }else {
            AddDrink.setDrinkimage(image_uri.toString());
        }

        if(productname.getText().toString().trim().isEmpty()){
            productname.setError("Enter the name of the drink");
        }else{
            AddDrink.setDrinkname(productname.getText().toString());
        }

        if(productvolume.getText().toString().trim().isEmpty()){
            productvolume.setError("Enter the volume of the bottle");
        }else{
            AddDrink.setDrinkvolume(productvolume.getText().toString());
        }

        if(productprice.getText().toString().trim().isEmpty()){
            productprice.setError("Enter the price of the drink");
        }else{
            AddDrink.setDrinkprice(productprice.getText().toString());
        }
            DrinksDetails.put(AddDrink);
    }

    private ArrayList<Category> ActualCategories(){
        ArrayList<Category> categories= new ArrayList<>();
        categories.add(new Category("CHAMPAGNE"));
        categories.add(new Category("BRANDY"));
        categories.add(new Category("BEER"));
        categories.add(new Category("CIDER"));
        categories.add(new Category("WINE"));
        categories.add(new Category("SPIRITS"));
        categories.add(new Category("WHISKY"));
        categories.add(new Category("COGNAC"));
        categories.add(new Category("VODKA"));
        categories.add(new Category("RUM"));
        categories.add(new Category("GIN"));
        categories.add(new Category("TEQUILA"));
        return categories;
    }

}