package com.example.kundvagn.MVVM;

import androidx.annotation.Nullable;

import com.example.kundvagn.Model.Product;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    List<Product> productList = new ArrayList<>();
    OnProductInter interfaceproducts;

    public ProductRepository(OnProductInter interfaceproducts) {
        this.interfaceproducts = interfaceproducts;
    }

    public void getAllPro() {

        firestore.collection("Products").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                productList.clear();
                for(DocumentSnapshot ds: value.getDocuments()) {
                    Product product = ds.toObject(Product.class);
                    productList.add(product);
                    interfaceproducts.Products(productList);
                }

            }
        });


    }

    public interface OnProductInter {
        void Products(List<Product> products);
    }
}
