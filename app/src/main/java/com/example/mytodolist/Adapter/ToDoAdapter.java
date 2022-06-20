package com.example.mytodolist.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytodolist.Entertainment;
import com.example.mytodolist.Model.ToDoModel;
import com.example.mytodolist.R;
import com.example.mytodolist.Studies;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.MyViewHolder> {


     List<ToDoModel> todolist;
     Studies activityStudies;
     Entertainment activityEntertainment;
     FirebaseFirestore firestore;

    public ToDoAdapter(Studies mainActivity , List<ToDoModel> todolist) {

        this.todolist = todolist;
        activityStudies =mainActivity;
    }
   /* public ToDoAdapter(Entertainment mainActivity , List<ToDoModel> todolist) {

        this.todolist = todolist;
        activityEntertainment =mainActivity;
    }

    */





    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activityStudies).inflate(R.layout.each_task,parent,false);
        firestore =FirebaseFirestore.getInstance();
        return new MyViewHolder(view);
    }




    @Override
    public void onBindViewHolder(@NonNull  ToDoAdapter.MyViewHolder holder, int position) {

        ToDoModel toDoModel = todolist.get(position);
        holder.mCheckBox.setText(toDoModel.getTask());
        holder.mDueDateTv.setText("Due on "+toDoModel.getDue());

        holder.mCheckBox.setChecked(toBoolean(toDoModel.getStatus()));
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    firestore.collection("task").document(toDoModel.TaskId).update("status" , 1);

                }
                else {

                    firestore.collection("task").document(toDoModel.TaskId).update("status" , 0);


                }
            }
        });

    }

    private boolean toBoolean (int status) {

        return status != 0 ;
    }

    @Override
    public int getItemCount() {
        return todolist.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mDueDateTv;
        CheckBox mCheckBox;


        public MyViewHolder(@NonNull  View itemView) {
            super(itemView);

            mDueDateTv = itemView.findViewById(R.id.due_date_tv);
            mCheckBox = itemView.findViewById(R.id.mcheckbox);


        }
    }



}
