package com.example.todollist;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

public class show_result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_result);
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean ed = ((CheckBox) view).isChecked();
        CheckBox resC1 = findViewById(R.id.CheckB1);
        CheckBox resC2 = findViewById(R.id.CheckB2);


        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.CheckB1:
                if (ed)
                    resC1.setPaintFlags(resC1.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                else
                    resC1.setPaintFlags(resC1.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));

                break;
            case R.id.CheckB2:
                if (ed)
                    resC2.setPaintFlags(resC2.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                else
                    resC2.setPaintFlags(resC2.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));

                break;

        }
    }
}