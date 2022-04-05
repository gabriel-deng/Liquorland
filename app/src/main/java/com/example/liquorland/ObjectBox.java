package com.example.liquorland;


import com.example.liquorland.Models.MyObjectBox;

import io.objectbox.BoxStore;

public class ObjectBox {
    private static BoxStore boxstore;

    static void init(App context){
        boxstore = MyObjectBox.builder()
                .androidContext(context.getApplicationContext())
                .build();
    }

    public static BoxStore get(){
        return boxstore;
    }
}
