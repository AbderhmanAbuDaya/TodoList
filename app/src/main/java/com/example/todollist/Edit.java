package com.example.todollist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Edit extends AppCompatActivity {
    FirebaseAuth mAuth;
    EditText edtiTitle, editDes;
    TextView catEdit;
    String taskId, catId,catTitle,taskDes,taskName;
    Button edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Bundle extras = getIntent().getExtras();
        taskId = extras.getString("TASK_ID");
        catId = extras.getString("CATEGORY_ID");
        catTitle=extras.getString("CATEGORY_NAME");
        taskDes=extras.getString("TASK_DES");
        taskName=extras.getString("TASK_TITLE");
        edtiTitle=findViewById(R.id.edtiTitle);
        editDes=findViewById(R.id.editDes);
        catEdit=findViewById(R.id.catEdit);
        catEdit.setText(catTitle);
        editDes.setText(taskDes);
        edtiTitle.setText(taskName);
        findViewById(R.id.edit_Tasks).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("ahmed");
                mAuth = FirebaseAuth.getInstance();
                FirebaseUser user = mAuth.getCurrentUser();
                String uid = user.getUid();
                FirebaseDatabase.getInstance().getReference("Users").child(uid).child("category").child(catId).child("tasks").child(taskId).child("title").setValue(edtiTitle.getText().toString());
                FirebaseDatabase.getInstance().getReference("Users").child(uid).child("category").child(catId).child("tasks").child(taskId).child("description").setValue(editDes.getText().toString());
                System.out.println("ahmed1");
                Toast.makeText(Edit.this,"Modified done", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Edit.this, Lists.class);
                startActivity(intent);
                System.out.println("ahmed2");
            }
        });

    }
}