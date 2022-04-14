package com.example.kundvagn.MVVM;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.kundvagn.Model.Product;

import java.util.List;

public class ProductViewModel extends ViewModel implements ProductRepository.OnProductInter {

    MutableLiveData<List<Product>> mutableLiveData = new MutableLiveData<>();
    ProductRepository productRepository = new ProductRepository(this);

    public ProductViewModel(){
        productRepository.getAllPro();
    }

    public LiveData<List<Product>> getAllProducts() {
        return mutableLiveData;





    }

    @Override
    public void Products(List<Product> products) {
        mutableLiveData.setValue(products);
    }
}
