package com.example.kviz.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;

import com.example.kviz.Adapter.KvizAdapter;
import com.example.kviz.MVVM.KvizViewModel;
import com.example.kviz.Model.KvizModel;
import com.example.kviz.R;

import java.util.List;

public class ListFragment extends Fragment implements KvizAdapter.OnItemClicked {


    RecyclerView recyclerView;
    KvizAdapter adapter;
    NavController navController;
    ProgressBar progressBar;
    Animation fade_in, fade_out;
    KvizViewModel viewModel;

    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.listfragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        recyclerView = view.findViewById(R.id.recyclerview);
        fade_in = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        fade_out = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);

        adapter = new KvizAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        progressBar = view.findViewById(R.id.listprogressbar);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(KvizViewModel.class);
        viewModel.getLiveDatafromFireStore().observe(getViewLifecycleOwner(), new Observer<List<KvizModel>>() {
            @Override
            public void onChanged(List<KvizModel> kvizModels) {
                adapter.setKvizModelData(kvizModels);

                recyclerView.setAnimation(fade_in);
                progressBar.setAnimation(fade_out);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void somethingClicked(int position) {
       // ListFragmentDirections.ActionListFragmentToDetailFragment action = ListFragmentDirections.actionListFragmentToDetailFragment();
        //action.setPosition(position);
        //navController.navigate(action);
    }


}