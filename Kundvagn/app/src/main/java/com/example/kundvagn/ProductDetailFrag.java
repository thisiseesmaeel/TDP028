package com.example.kundvagn;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

public class ProductDetailFrag extends Fragment {

    TextView quantitydisplay, titleview, descview, priceview;
    int quantity = 0;
    Button addquantity, subquantity, addtocart;
    FirebaseFirestore firestore;
    ImageView imageView;
    String title, imageUrl, desc;
    int position = 0;
    int price = 0;

    public ProductDetailFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_detail, container, false);
    }
}