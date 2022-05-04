package com.example.kundvagn.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.bumptech.glide.Glide;
import com.example.kundvagn.Model.Product;
import com.example.kundvagn.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {

        List<Product> productList;
        onClickedProduct interfaceClickedOnProduct;

    public ProductAdapter(onClickedProduct interfaceClickedOnProduct) {
        this.interfaceClickedOnProduct = interfaceClickedOnProduct;
    }

    public void setProductList(List<Product> productList) {
            this.productList = productList;
        }

        @NonNull
        @Override
        public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.productstyleslist, parent, false);
            return new ProductHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductHolder holder, int position) {

            holder.titleofproduct.setText(productList.get(position).getTitle());
            holder.descriptionofproduct.setText(productList.get(position).getDesc());
            Glide.with(holder.itemView.getContext()).load(productList.get(position).getImageUrl()).centerCrop().into(holder.circleImageView);


        }

        @Override
        public int getItemCount() {
            if (productList == null) {
                return 0;
            } else {
                return productList.size();
            }
        }

    class ProductHolder extends ViewHolder implements View.OnClickListener {

        TextView titleofproduct, descriptionofproduct;
        CircleImageView circleImageView;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            titleofproduct = itemView.findViewById(R.id.producttitle);
            descriptionofproduct = itemView.findViewById(R.id.productdescriptionmain);
            circleImageView = itemView.findViewById(R.id.productmainimage);

            titleofproduct.setOnClickListener(this);
            descriptionofproduct.setOnClickListener(this);
            circleImageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            interfaceClickedOnProduct.OnProClicked(productList, getAdapterPosition());

        }
    }


    public interface onClickedProduct{
        void OnProClicked(List<Product> productList, int position);
    }
}


