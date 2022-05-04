package com.example.kundvagn;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.example.kundvagn.Model.Product;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;

import javax.annotation.Nullable;

public class ProductDetailFrag extends Fragment {

    TextView quantitydisplay, titleview, descview, priceview;
    int quantity = 0;
    Button addquantity, subquantity, addtocart;
    FirebaseFirestore firestore;
    FirebaseAuth firebaseAuth;
    ImageView imageView;
    String title, imageUrl, desc, productid, userid;
    int position = 0;
    int finalprice = 0;
    int price = 0;

    NavController navController;

    public ProductDetailFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_detail, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        // här initierar vi views
        firestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        userid = firebaseAuth.getCurrentUser().getUid();
        quantitydisplay = view.findViewById(R.id.quantitycounterproductdetail);
        addquantity = view.findViewById(R.id.addquantity);
        subquantity = view.findViewById(R.id.subquantity);
        addtocart = view.findViewById(R.id.buttonaddtocart);
        titleview = view.findViewById(R.id.productdetailtitle);
        descview = view.findViewById(R.id.productdetaildetail);
        priceview = view.findViewById(R.id.totalPriceproductdetail);
        imageView = view.findViewById(R.id.productdetailimage);
        navController = Navigation.findNavController(view);

        // här hämtar vi argumenten eller värdena
        imageUrl = ProductDetailFragArgs.fromBundle(getArguments()).getImageUrl();
        title = ProductDetailFragArgs.fromBundle(getArguments()).getTitle();
        desc = ProductDetailFragArgs.fromBundle(getArguments()).getDescription();
        position = ProductDetailFragArgs.fromBundle(getArguments()).getPosition();
        price = ProductDetailFragArgs.fromBundle(getArguments()).getPrice();
        productid = ProductDetailFragArgs.fromBundle(getArguments()).getProductid();



        titleview.setText(title);
        descview.setText(desc);
        Glide.with(getActivity()).load(imageUrl).centerCrop().into(imageView);
        priceview.setText("Pris för 1 vara är: " + price);

        firestore.collection("Products").document(productid).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                Product product = value.toObject(Product.class);
                String latestquantity = String.valueOf(product.getQuantity());

                // här visar vi antal varor
                quantitydisplay.setText(latestquantity);

            }
        });


        // här uppdaterar vi våra produkters antal utifrån deras "productid"

        addquantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                quantity = Integer.parseInt(quantitydisplay.getText().toString());
                quantity++;

                firestore.collection("Products").document(productid).update("quantity", quantity).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {


                    }
                });

                finalprice = quantity * price;
                priceview.setText("Total kostnad är " + finalprice + " x " + quantity);

            }
        });


        subquantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                quantity = Integer.parseInt(quantitydisplay.getText().toString());

                if(quantity <= 0) {
                    quantity = 0;
                    finalprice = 0;
                } else {
                    quantity--;

                    firestore.collection("Products").document(productid).update("quantity", quantity).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {


                        }
                    });

                    finalprice = quantity * price;
                    priceview.setText("Total kostnad är " + finalprice+ " x " + quantity);
                }

            }
        });

        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(quantity == 0){

                    navController.navigate(R.id.action_productDetailFrag_to_productFragment);
                    Toast.makeText(getContext(), "Ingenting lades till kundvagnen", Toast.LENGTH_LONG).show();
                }
                else {

                    AddedInCart();
                    ProductDetailFragDirections.ActionProductDetailFragToProductFragment
                            actions = ProductDetailFragDirections.actionProductDetailFragToProductFragment();
                    navController.navigate(actions);
                    Toast.makeText(getContext(), "Lades till kundvagnen", Toast.LENGTH_LONG).show();

                    

                }

            }
        });


    }

    private void AddedInCart() {

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("quantity", quantity);
        hashMap.put("price", finalprice);
        hashMap.put("title", title);
        hashMap.put("imageUrl", imageUrl);
        hashMap.put("productid", productid);

        firestore.collection("Cart" + userid).document(title).set(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });

    }
}