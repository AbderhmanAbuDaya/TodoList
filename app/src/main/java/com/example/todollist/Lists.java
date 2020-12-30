package com.example.todollist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class Lists extends AppCompatActivity implements TaskAdapterEx.ListItemClickListener{
    RecyclerView tasks_show;
    TaskAdapterEx  taskAdapter;
    TextView titleList,logout;
    Button addList;
    FirebaseAuth mAuth;
    ImageView back1;

    static java.util.List<TaskItem> task = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);
//        task.add(new TaskItem(1,"Study"));
//        task.add(new TaskItem(2,"Washing"));
//        task.add(new TaskItem(5,"Playing"));
        tasks_show = findViewById(R.id.tasks_show);
        titleList=findViewById(R.id.titleList);
        addList=findViewById(R.id.addList);
        addList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mAuth = FirebaseAuth.getInstance();
                FirebaseUser user = mAuth.getCurrentUser();
                String uid = user.getUid();
                TaskItem newTask=new TaskItem();
                newTask.setTitle(titleList.getText().toString());
                newTask.setCount(0);
                String categoryId = FirebaseDatabase.getInstance().getReference("Users").child(uid).child("category").push().getKey();
                newTask.setId(categoryId);
                FirebaseDatabase.getInstance().getReference("Users").child(uid).child("category").child(categoryId).setValue(newTask);
                Toast.makeText(Lists.this,"Category has been added successfully", Toast.LENGTH_SHORT).show();
                titleList.setText("");
            }
        });
        mAuth= FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String uid = user.getUid();
        FirebaseDatabase.getInstance().getReference("Users").child(uid).child("category")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        task.clear();
                        for(DataSnapshot snapshot: dataSnapshot.getChildren() ){
                            TaskItem item =  snapshot.getValue(TaskItem.class);
                            task.add(item);

                        }
                        taskAdapter.notifyDataSetChanged();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
        tasks_show.setLayoutManager(new LinearLayoutManager(this));
        taskAdapter = new TaskAdapterEx(task,this);
        tasks_show.setAdapter(taskAdapter);

        //log out

        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(Lists.this,Login.class);
                startActivity(intent);
            }
        });
        back1= findViewById(R.id.back1);
        back1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Lists.this,MainActivity.class);
                startActivity(intent);

            }
        });

        //end log out
    }

    @Override
    public void onListItemClick(int position) {
        Intent intent = new Intent(Lists.this, select_task.class);
        intent.putExtra("CATEGORY_ID", task.get(position).getId());
        intent.putExtra("CATEGORY_TITLE", task.get(position).getTitle());
        startActivity(intent);
    }
}