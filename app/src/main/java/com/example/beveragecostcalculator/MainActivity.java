package com.example.beveragecostcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    /*private static final String[] provinces = new String[]{
            "Quebec", "Nova Scotia", "New Brunswick", "Alberta", "Manitoba", "Newfoundland and Labrador", "Ontario", "Prince Edward Island", "Saskatchewan"
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spin = findViewById(R.id.spinnerSize);
        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(this,R.array.size,android.R.layout.simple_spinner_item);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapterSpinner);

        String[] provinces = getResources().getStringArray(R.array.provinces);
        AutoCompleteTextView act = findViewById(R.id.autoTextViewProvinces);
        ArrayAdapter<String> adapterAutoComplete = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, provinces);
        act.setAdapter(adapterAutoComplete);


    }
}

