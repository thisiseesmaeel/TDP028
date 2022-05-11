package com.example.kundvagn.MVVM;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.kundvagn.Model.Cart;

import java.util.List;

public class CartViewModel extends ViewModel implements CartRepository.CartINTERFACEShit {


    MutableLiveData<List<Cart>> mutableLiveData = new MutableLiveData<>();
    CartRepository rep = new CartRepository(this);

    public CartViewModel() {
        rep.getCartShit();
    }

    public LiveData<List<Cart>> cartLiveDataShit() {
        return mutableLiveData;
    }


    @Override
    public void CartlIST(List<Cart> carts) {
        mutableLiveData.setValue(carts);
    }
}