package com.example.todollist;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

public class select_task extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_task);
    }
    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean ed = ((CheckBox) view).isChecked();
        CheckBox CheckB1 = findViewById(R.id.CheckB1);
        CheckBox CheckB2 = findViewById(R.id.CheckB2);
        CheckBox CheckB3 = findViewById(R.id.CheckB3);

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.CheckB1:
                if (ed)
                    CheckB1.setPaintFlags(CheckB1.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                else
                    CheckB1.setPaintFlags(CheckB1.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));

                break;
            case R.id.CheckB2:
                if (ed)
                    CheckB2.setPaintFlags(CheckB2.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                else
                    CheckB2.setPaintFlags(CheckB2.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));

                break;
            case R.id.CheckB3:
                if (ed)
                    CheckB3.setPaintFlags(CheckB3.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                else
                    CheckB3.setPaintFlags(CheckB3.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));

                break;
        }
    }
}