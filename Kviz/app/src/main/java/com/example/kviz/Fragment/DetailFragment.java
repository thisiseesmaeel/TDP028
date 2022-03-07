package com.example.kviz.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kviz.MVVM.KvizViewModel;
import com.example.kviz.Model.KvizModel;
import com.example.kviz.R;

import java.util.List;


public class DetailFragment extends Fragment implements View.OnClickListener {

    TextView kvizrubrik, description,question;
    ImageView imageView;
    Button takekviz;

    NavController navController;
    int position = 0;
    KvizViewModel viewModel;
    String kvizid, kvizname;
    long fragor = 0L;

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.detailfrag, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        kvizrubrik = view.findViewById(R.id.detailkviztitle);
        description = view.findViewById(R.id.detaillevel);
        question = view.findViewById(R.id.detailquestionstext);
        navController = Navigation.findNavController(view);
        //position = DetailFragmentArgs.fromBundle(getArguments()).getPosition();
        takekviz = view.findViewById(R.id.detailtakekvizbutton);
        takekviz.setOnClickListener(this);
        imageView = view.findViewById(R.id.detailimage);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel = new ViewModelProvider(getActivity()).get(KvizViewModel.class);
        viewModel.getLiveDatafromFireStore().observe(getViewLifecycleOwner(), new Observer<List<KvizModel>>() {
            @Override
            public void onChanged(List<KvizModel> kvizModels) {
                kvizrubrik.setText(kvizModels.get(position).getKvizname());
                description.setText(kvizModels.get(position).getDescription());
                question.setText(kvizModels.get(position).getQuestion()+"");

                Glide.with(getContext()).load(kvizModels.get(position).getImage())
                        .placeholder(R.drawable.placeholder_image)
                        .centerCrop().into(imageView);
                // Vi skickar de här två till kvizfragment
                kvizid = kvizModels.get(position).getKvizid();
                fragor = kvizModels.get(position).getQuestion();
                kvizname = kvizModels.get(position).getKvizname();
            }
        });
    }

    @Override
    public void onClick(View view) {
       // DetailFragmentDirections.ActionDetailFragmentToKvizFragment
         //       action = DetailFragmentDirections.actionDetailFragmentToKvizFragment();
        //action.setKvizid(kvizid);
        //action.setKvizname(kvizname);
        //action.setQuestions(fragor);
        //navController.navigate(action);
    }
}