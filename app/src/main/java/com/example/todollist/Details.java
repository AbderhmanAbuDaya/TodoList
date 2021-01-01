package com.example.todollist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Details extends AppCompatActivity {
String taskId,taskTitle,taskDes,categoryId,categoryValue;
TextView title_task,description,remove,categoryDet;
    FirebaseAuth mAuth;
   // count counts;
    boolean flag;
    int counts;

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
        categoryValue = extras.getString("CATEGORY_TITLE");
        categoryDet=findViewById(R.id.categoryDet);
        categoryDet.setText(categoryValue);

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                mAuth = FirebaseAuth.getInstance();
                FirebaseUser user = mAuth.getCurrentUser();
                String uid = user.getUid();
                FirebaseDatabase.getInstance().getReference("Users").child(uid).child("category").child(categoryId).child("tasks").child(taskId).removeValue();
                FirebaseDatabase.getInstance().getReference("Users").child(uid).child("task")
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                // This method is called once with the initial value and again
                                // whenever data at this location is updated.
                                for(DataSnapshot snapshot: dataSnapshot.getChildren() ){
                                    TaskItem task =  snapshot.getValue(TaskItem.class);
                                    if(task.getId().compareTo(categoryId) == 0 && flag){
                                        counts = task.getCount() -1;
                                        FirebaseDatabase.getInstance().getReference("Users").child(uid).child("category").child(categoryId).setValue(counts);
                                        flag = false;
                                        break;
                                    }

                                }

                            }

                            @Override
                            public void onCancelled(DatabaseError error) {
                                // Failed to read value
                            }
                        });
                finish();
            }
        });

        findViewById(R.id.edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent intent = new Intent(Details.this, Edit.class);
                intent.putExtra("TASK_ID", taskId);
                intent.putExtra("TASK_TITLE", taskTitle);
                intent.putExtra("TASK_DES", taskDes);
                intent.putExtra("CATEGORY_NAME", categoryValue);
                intent.putExtra("CATEGORY_ID", categoryId);
                startActivity(intent);
            }
        });



    }
}