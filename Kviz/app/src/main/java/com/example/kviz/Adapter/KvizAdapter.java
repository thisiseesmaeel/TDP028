package com.example.kviz.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kviz.Model.KvizModel;
import com.example.kviz.R;

import java.util.List;

public class KvizAdapter extends RecyclerView.Adapter<KvizAdapter.MyHolder>{


    List<KvizModel> kvizModelList;
    OnItemClicked itemClicked;

    public void setKvizModelData(List<KvizModel> kvizModelData) {
        this.kvizModelList = kvizModelData;
    }

    public KvizAdapter(OnItemClicked itemClicked) {
        this.itemClicked = itemClicked;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.startlayout, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {


        holder.kvizrubrik.setText(kvizModelList.get(position).getKvizname());
        holder.description.setText(kvizModelList.get(position).getDescription());

        Glide.with(holder.itemView.getContext()).load(kvizModelList.get(position).getImage())
                .placeholder(R.drawable.placeholder_image).centerCrop().into(holder.image);
    }

    @Override
    // Checking if List is null
    public int getItemCount() {
        if (kvizModelList == null){
            return 0;
        } else {
            return kvizModelList.size();
        }
    }

    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView description, kvizrubrik;
        ImageView image;
        Button button;
        public MyHolder(@NonNull View itemView) {
            super(itemView);

            description = itemView.findViewById(R.id.listkvizdesc);
            kvizrubrik = itemView.findViewById(R.id.layoutkviztitle);
            image = itemView.findViewById(R.id.listimage);
            button = itemView.findViewById(R.id.takekvizBtn);

            button.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            itemClicked.somethingClicked(getAdapterPosition());
        }

    }
    public interface OnItemClicked {
        void somethingClicked(int position);
    }
}
