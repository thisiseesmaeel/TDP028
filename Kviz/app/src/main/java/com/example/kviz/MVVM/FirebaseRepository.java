package com.example.kviz.MVVM;

import androidx.annotation.NonNull;

import com.example.kviz.Model.KvizModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class FirebaseRepository {

    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    Query kvizRef = firestore.collection("KvizList");

    OnFireStoreDataAdded fireStoreDataAdded;

    public FirebaseRepository(OnFireStoreDataAdded fireStoreDataAdded) {
        this.fireStoreDataAdded = fireStoreDataAdded;
    }

    public void getDataFromFirestore() {
        kvizRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
            if(task.isSuccessful()){
                fireStoreDataAdded.kvizDataAdded(task.getResult().toObjects(KvizModel.class));
            } else {
                fireStoreDataAdded.OnError(task.getException());
            }
            }
        });
    }

    public interface OnFireStoreDataAdded {
        void kvizDataAdded(List<KvizModel> kvizModelList);
        void OnError(Exception e);
    }
}
