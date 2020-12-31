package com.example.todollist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class select_task extends AppCompatActivity implements TaskAdpaterCheck.ListItemClickListener{
    RecyclerView choice_task;
    TaskAdpaterCheck  taskAdapter;
    static List<TaskCheck> ListCheck = new ArrayList<>();
    Button addTasks;
    EditText taskDes, taskTitle;
    String categoryId,categoryTitle,count;
    TextView categoryName,delete;
    ImageView back2;
    private FirebaseAuth mAuth;
    boolean flag;
    int countTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_task);
        Bundle extras = getIntent().getExtras();
        categoryId = extras.getString("CATEGORY_ID");
        categoryTitle=extras.getString("CATEGORY_TITLE");
        categoryName=findViewById(R.id.categoryName);
        categoryName.setText(categoryTitle);
        delete=findViewById(R.id.delete);
        addTasks=findViewById(R.id.addTasks);
        taskDes = findViewById(R.id.taskDes);
        taskTitle = findViewById(R.id.taskTitle);
        choice_task = findViewById(R.id.choice_task);
        addTasks.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mAuth = FirebaseAuth.getInstance();
                FirebaseUser user = mAuth.getCurrentUser();
                String uid = user.getUid();
                TaskCheck newTask = new TaskCheck();
                newTask.setTitle(taskTitle.getText().toString());
                newTask.setDescription(taskDes.getText().toString());
                newTask.setIsChecked(false);
                String taskId = FirebaseDatabase.getInstance().getReference("Users").child(uid).child("category").child(categoryId).child("tasks").push().getKey();
                System.out.print("****"+taskId);
                newTask.setId(taskId);
                FirebaseDatabase.getInstance().getReference("Users").child(uid).child("category").child(categoryId).child("tasks").child(taskId).setValue(newTask);
                FirebaseDatabase.getInstance().getReference("Users").child(uid).child("category")
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for(DataSnapshot snapshot: dataSnapshot.getChildren() ){
                                    TaskItem task =  snapshot.getValue(TaskItem.class);
                                    if(task.getId().compareTo(categoryId) == 0 && flag){
                                        countTask = task.getCount()+1 ;
                                        FirebaseDatabase.getInstance().getReference("Users").child(uid).child("category").child(categoryId).child("count").setValue(countTask);
                                        flag = false;
//                                        Intent intent = new Intent(TaskChoices.this, com.example.todo.List.class);
//                                        startActivity(intent);
                                        break;
                                    }

                                }

                            }

                            @Override
                            public void onCancelled(DatabaseError error) {
                                // Failed to read value
                            }
                        });
                flag=true;
                Toast.makeText(select_task.this,"added successfully", Toast.LENGTH_SHORT).show();
                taskTitle.setText("");
                taskDes.setText("");

            }
        });
        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mAuth = FirebaseAuth.getInstance();
                FirebaseUser user = mAuth.getCurrentUser();
                String uid = user.getUid();
                FirebaseDatabase.getInstance().getReference("Users").child(uid).child("category").child(categoryId).removeValue();
                finish();
                System.out.println(uid);
            }
        });
        back2= findViewById(R.id.backDetsels);
        back2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
              finish();

            }
        });

        mAuth= FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String uid = user.getUid();

        FirebaseDatabase.getInstance().getReference("Users").child(uid).child("category").child(categoryId).child("tasks")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ListCheck.clear();
                        for(DataSnapshot snapshot: dataSnapshot.getChildren() ){
                            TaskCheck item =  snapshot.getValue(TaskCheck.class);
                            ListCheck.add(item);
                        }
                        taskAdapter.notifyDataSetChanged();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
        choice_task.setLayoutManager(new LinearLayoutManager(this));
        taskAdapter = new TaskAdpaterCheck (this,ListCheck,this);
        choice_task.setAdapter(taskAdapter);

    }

    @Override
    public void onListItemClick(int position) {
        Intent intent = new Intent(select_task.this, Details.class);
        intent.putExtra("TASK_ID", ListCheck.get(position).getId());
        intent.putExtra("TASK_TITLE", ListCheck.get(position).getTitle());
        intent.putExtra("TASK_DES", ListCheck.get(position).getDescription());
        intent.putExtra("CATEGORY_ID", categoryId);
        intent.putExtra("CATEGORY_TITLE", categoryTitle);
        startActivity(intent);
    }
}