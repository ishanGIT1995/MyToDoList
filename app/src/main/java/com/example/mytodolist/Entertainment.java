package com.example.mytodolist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mytodolist.Adapter.ToDoAdapter;
import com.example.mytodolist.Model.ToDoModel;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Entertainment extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button taskAdd;
    private FirebaseFirestore firestore;
    private ToDoAdapter adapter ;
    private List<ToDoModel> mEntertainmentList ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entertainment);

        recyclerView= findViewById(R.id.recyclerview);
        taskAdd = findViewById(R.id.addTask);
        firestore = FirebaseFirestore.getInstance();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(Entertainment.this));

        taskAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewTask.newInstance().show(getSupportFragmentManager(),AddNewTask.TAG);

            }
        });

/*
        mEntertainmentList = new ArrayList<>();
        adapter = new ToDoAdapter(Entertainment.this , mEntertainmentList);
        recyclerView.setAdapter(adapter);
        showData();

 */




    }

  /*  private void showData() {

        firestore.collection("task").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                for (DocumentChange documentChange :value.getDocumentChanges()) {

                    if (documentChange.getType() == DocumentChange.Type.ADDED) {

                        String id =documentChange.getDocument().getId();
                        ToDoModel toDoModel =documentChange.getDocument().toObject(ToDoModel.class).withId(id);

                        mEntertainmentList.add(toDoModel);
                        adapter.notifyDataSetChanged();
                    }

                }
                Collections.reverse(mEntertainmentList);

            }
        });


    }

   */


}