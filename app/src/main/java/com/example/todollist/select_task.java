package com.example.todollist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.List;

public class select_task extends AppCompatActivity {

    RecyclerView choice_task;
    TaskAdpaterCheck  taskAdapter;
    static List<TaskCheck> CheckList = new ArrayList<TaskCheck>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_task);
        CheckList.add(new TaskCheck("2","Meeting with x" ,false));
        CheckList.add(new TaskCheck("3","Meeting with  y" ,false));
        //CheckList.add(new TaskCheck("1","Meeting with Castmer" ,false));

        choice_task = findViewById(R.id.choice_task);
        choice_task.setLayoutManager(new LinearLayoutManager(this));
        taskAdapter = new TaskAdpaterCheck (this,CheckList);
        choice_task.setAdapter(taskAdapter);

    }

}