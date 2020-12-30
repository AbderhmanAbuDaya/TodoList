package com.example.todollist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Details extends AppCompatActivity {
String taskId,taskTitle,taskDes,categoryId;
TextView title_task,description,remove;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Bundle extras = getIntent().getExtras();
        taskId = extras.getString("TASK_ID");
        taskTitle=extras.getString("TASK_TITLE");
        taskDes=extras.getString("TASK_DES");
        System.out.println(taskId+taskDes+taskTitle);
        description= findViewById(R.id.description);
        title_task= findViewById(R.id.title_task);
        remove= findViewById(R.id.remove);
        categoryId = extras.getString("CATEGORY_ID");
        title_task.setText(taskTitle);
        description.setText(taskDes);

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                mAuth = FirebaseAuth.getInstance();
                FirebaseUser user = mAuth.getCurrentUser();
                String uid = user.getUid();
                FirebaseDatabase.getInstance().getReference("Users").child(uid).child("category").child(categoryId).child("tasks").child(taskId).removeValue();

                finish();
            }
        });
    }
}