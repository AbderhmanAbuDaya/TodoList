package com.example.todollist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class Lists extends AppCompatActivity {
    RecyclerView tasks_show;
    TaskAdapterEx  taskAdapter;
    static java.util.List<TaskItem> task = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);
        task.add(new TaskItem(1,"Study"));
        task.add(new TaskItem(2,"Washing"));
        task.add(new TaskItem(5,"Playing"));
        tasks_show = findViewById(R.id.tasks_show);
        tasks_show.setLayoutManager(new LinearLayoutManager(this));
        taskAdapter = new TaskAdapterEx(task);
        tasks_show.setAdapter(taskAdapter);
    }
}