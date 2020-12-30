package com.example.todollist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    TextView NextBtn;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NextBtn=findViewById(R.id.NextBtn);
        NextBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent;
             if(null != FirebaseAuth.getInstance().getCurrentUser()){
                     intent = new Intent(MainActivity.this, Lists.class);

                }else {
                     intent = new Intent(MainActivity.this, Login.class);
                }
                startActivity(intent);
            }
        });

    }
}