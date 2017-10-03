package com.lanciar.app.mobitrack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class spinnertest extends AppCompatActivity {

    ArrayAdapter arrayAdapter;
    Spinner sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinnertest);
        sp= (Spinner) findViewById(R.id.spin);
        String []s={"hai","hello"};
        arrayAdapter = new ArrayAdapter(getApplicationContext(),
                android.R.layout.simple_spinner_item,
                s);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp.setAdapter(arrayAdapter);
    }
}
