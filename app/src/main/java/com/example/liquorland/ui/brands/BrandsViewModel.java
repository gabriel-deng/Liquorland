package com.example.liquorland.ui.brands;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BrandsViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public BrandsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Brands fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
    // TODO: Implement the ViewModel
}