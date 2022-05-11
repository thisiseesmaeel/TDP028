package com.example.kundvagn;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kundvagn.Adapter.CartAdapter;
import com.example.kundvagn.MVVM.CartViewModel;
import com.example.kundvagn.Model.Cart;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class VagnFragment extends Fragment {


    RecyclerView recyclerView;
    CartViewModel viewModel;
    CartAdapter mAdapter;
    TextView displayTotalPrice;
    int totalPrice = 0;
    NavController navController;
    FirebaseFirestore firestore;
    FirebaseAuth firebaseAuth;

    Button checkOut;
    String userid;
    List<Integer> saveTotalPrice = new ArrayList<>();


    public VagnFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vagn, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        userid = firebaseAuth.getCurrentUser().getUid();
        recyclerView = view.findViewById(R.id.recyclerviewcart);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new CartAdapter();
        viewModel = new ViewModelProvider(getActivity()).get(CartViewModel.class);
        displayTotalPrice = view.findViewById(R.id.totalPriceordercart);
        checkOut = view.findViewById(R.id.buttoncheckout);
        navController = Navigation.findNavController(view);

        viewModel.cartLiveDataShit().observe(getViewLifecycleOwner(), new Observer<List<Cart>>() {
            @Override
            public void onChanged(List<Cart> carts) {
                mAdapter.setCartList(carts);
                recyclerView.setAdapter(mAdapter);



            }
        });

        firestore.collection("Cart" + userid).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                for(DocumentSnapshot dsn:  value.getDocuments()){
                    Cart cart = dsn.toObject(Cart.class);
                    int total = cart.getPrice();
                    saveTotalPrice.add(total);

                }
                for(int i = 0; i < saveTotalPrice.size(); i++){
                    totalPrice += Integer.parseInt(String.valueOf(saveTotalPrice.get(i)));
                    displayTotalPrice.setText(String.valueOf(totalPrice));
                }


                saveTotalPrice.clear();
                totalPrice = 0;

            }
        });

        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firestore.collection("Products").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if(task.isSuccessful()) {
                            QuerySnapshot shit = task.getResult();

                            for(DocumentSnapshot shs : shit.getDocuments()) {
                                shs.getReference().update("quantity", 0);

                            }
                        }

                    }
                });

                firestore.collection("Cart" + userid).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if(task.isSuccessful()) {
                            QuerySnapshot tasks = task.getResult();

                            for(DocumentSnapshot ds : tasks.getDocuments()) {
                                ds.getReference().delete();
                            }
                        }

                    }
                });

                navController.navigate(R.id.action_vagnFragment_to_productFragment);
                Toast.makeText(getContext(), "Order inlagd", Toast.LENGTH_LONG).show();


            }
        });
    }
}