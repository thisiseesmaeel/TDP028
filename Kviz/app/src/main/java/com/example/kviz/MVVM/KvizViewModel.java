package com.example.kviz.MVVM;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.kviz.Model.KvizModel;

import java.util.List;

public class KvizViewModel extends ViewModel implements FirebaseRepository.OnFireStoreDataAdded {

    MutableLiveData<List<KvizModel>> kvizModelListData = new MutableLiveData<>();

    FirebaseRepository firebaseRepository = new FirebaseRepository(this);

    public KvizViewModel(){
        firebaseRepository.getDataFromFirestore();
    }

    public LiveData<List<KvizModel>> getLiveDatafromFireStore() {
        return kvizModelListData;
    }

    @Override
    public void kvizDataAdded(List<KvizModel> kvizModelList) {
        kvizModelListData.setValue(kvizModelList);
    }

    @Override
    public void OnError(Exception e) {

    }
}
