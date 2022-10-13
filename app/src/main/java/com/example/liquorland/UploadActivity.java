package com.example.liquorland;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.liquorland.Models.Category;
import com.example.liquorland.Models.Drink;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class UploadActivity extends AppCompatActivity {

    // I created this class for the sole purpose of uploading the products to my
    // realtime database. It serves no other functions hence you should ignore it

    private static final int GALLERY_REQUEST_CODE = 35;
    private Uri filePath;


    TextInputEditText name, price, volume, category, uploadcat;
    ImageView drinkImage;
    Button addtodb, catupload;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference, mydatabase;

    FirebaseStorage storage;
    StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        name = findViewById(R.id.txt_drink_name_upload);
        price = findViewById(R.id.txt_drink_price_upload);
        volume = findViewById(R.id.txt_drink_volume_upload);
        category = findViewById(R.id.txt_drink_category_upload);
        drinkImage = findViewById(R.id.ic_drink_image_upload);
        addtodb = findViewById(R.id.btn_add_database);

        catupload= findViewById(R.id.btn_uploadcat);
        uploadcat= findViewById(R.id.txt_upload_cat);

        catupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendcatinfo();
            }
        });

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Drinks");

        mydatabase= firebaseDatabase.getReference("category").push();

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();




        drinkImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImage();
                veryfyPermissons();
            }
        });


        addtodb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnterDrinkInfoToDb();
            }
        });

    }

    private void sendcatinfo() {
        String catName= uploadcat.getText().toString();

        Category category= new Category();
        category.setCategory_name(catName);

        mydatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mydatabase.setValue(category);
                Toast.makeText(UploadActivity.this, "Category added into the database", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void pickImage() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(
                        intent,
                        "Select Image from here..."),
                GALLERY_REQUEST_CODE);
    }

    // Override onActivityResult method
    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {

        super.onActivityResult(requestCode,
                resultCode,
                data);

        // checking request code and result code
        // if request code is PICK_IMAGE_REQUEST and
        // resultCode is RESULT_OK
        // then set image in the image view
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            // Get the Uri of data
            filePath = data.getData();
            try {

                // Setting image on image view using Bitmap
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                drinkImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                // Log the exception
                e.printStackTrace();
            }
        }
    }

    private void veryfyPermissons() {
        String[] permissions = {
                Manifest.permission.READ_EXTERNAL_STORAGE
        };

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), permissions[0])
                == PackageManager.PERMISSION_GRANTED) {

            pickImage();
        } else {
            ActivityCompat.requestPermissions(this, permissions, 211);
        }
    }

    private String EnterDrinkInfoToDb() {

        String imageUrl = null;

        if (filePath != null) {

            // Defining the child of storageReference
            StorageReference ref = storageReference.child("images/" + UUID.randomUUID().toString());

            // adding listeners on upload
            // or failure of image
            UploadTask uploadTask;
            uploadTask = ref.putFile(filePath);
            Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }

                    // Continue with the task to get the download URL
                    return ref.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        databaseReference = firebaseDatabase.getReference("Drinks");
                        String pushkey= databaseReference.push().getKey();

                        Uri downloadUri = task.getResult();
                        String imageUrl = downloadUri.toString();
                        Log.i("Download url", imageUrl);


                        String drinkName= name.getText().toString();
                        String drinkPrice= price.getText().toString();
                        String drinkVolume= volume.getText().toString();
                        String drinkCategory= category.getText().toString();

                        Drink drink= new Drink();
                        drink.setDrinkname(drinkName);
                        drink.setDrinkprice(drinkPrice);
                        drink.setDrinkvolume(drinkVolume);
                        drink.setCategory(drinkCategory);
                        drink.setImageUrl(imageUrl);
                        drink.setId(pushkey);


                        databaseReference.child(pushkey).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                databaseReference.push().setValue(drink);
                                Toast.makeText(UploadActivity.this, "Drink added into the database", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(UploadActivity.this, "Fail to add data " + error, Toast.LENGTH_LONG).show();
                            }
                        });


                    }
                    else {  Toast.makeText(UploadActivity.this, "Failed " + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        return imageUrl;
        }

        }


