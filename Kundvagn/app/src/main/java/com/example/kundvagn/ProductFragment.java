package com.example.kundvagn;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kundvagn.Adapter.ProductAdapter;
import com.example.kundvagn.MVVM.ProductViewModel;
import com.example.kundvagn.Model.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class ProductFragment extends Fragment implements ProductAdapter.onClickedProduct {


    RecyclerView recyclerView;
    ProductViewModel viewModel;
    ProductAdapter mAdapter;
    FloatingActionButton fab;
    TextView quantityinvagn;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;
    String userid;

    NavController navController;


    public ProductFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        userid = firebaseAuth.getCurrentUser().getUid();


        mAdapter = new ProductAdapter(this);
        fab = view.findViewById(R.id.fabMainProductPage);
        quantityinvagn = view.findViewById(R.id.cartquantity);
        navController = Navigation.findNavController(view);

        recyclerView = view.findViewById(R.id.recyclerviewproduct);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        viewModel = new ViewModelProvider(getActivity()).get(ProductViewModel.class);
        viewModel.getAllProducts().observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                mAdapter.setProductList(products);
                recyclerView.setAdapter(mAdapter);
            }
        });


    }



    // For going to next fragment product detail.
    @Override
    public void OnProClicked(List<Product> productList, int position) {
        ProductFragmentDirections.ActionProductFragmentToProductDetailFrag
                actions = ProductFragmentDirections.actionProductFragmentToProductDetailFrag();

        actions.setTitle(productList.get(position).getRubrik());
        actions.setDescription(productList.get(position).getBeskrivning());
        actions.setProductid(productList.get(position).getProductid());
        actions.setPosition(position);
        actions.setImageUrl(productList.get(position).getBildUrl());
        actions.setPrice(productList.get(position).getPris());

        navController.navigate(actions);




    }
}