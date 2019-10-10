package com.example.beveragecostcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    /*private static final String[] provinces = new String[]{
            "Quebec", "Nova Scotia", "New Brunswick", "Alberta", "Manitoba", "Newfoundland and Labrador", "Ontario", "Prince Edward Island", "Saskatchewan"
    };*/

    private RadioGroup  radioGroupBeverages;
    private RadioButton radioButtonTea;
    private RadioButton radioButtonCoffee;
    private RadioButton radioButtonNone;
    private RadioButton radioButtonVanilla;
    private RadioButton radioButtonChocolate;
    private RadioButton radioButtonMint;
    private RadioButton radioButtonLemon;

    private EditText name;
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

        radioButtonCoffee =  findViewById(R.id.radioCoffee);
        radioButtonTea = findViewById(R.id.radioTea);
        radioGroupBeverages = findViewById(R.id.radioGroupBeverage);
        radioButtonNone =  findViewById(R.id.radioNone);
        radioButtonVanilla = findViewById(R.id.radioVanilla);
        radioButtonChocolate = findViewById(R.id.radioChocolate);
        radioButtonLemon = findViewById(R.id.radioLemon);
        radioButtonMint = findViewById(R.id.radioMint);

        radioGroupBeverages.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.radioCoffee:
                        Toast.makeText(getApplicationContext(),"coffee is selected",Toast.LENGTH_LONG).show();
                        radioButtonLemon.setEnabled(false);
                        radioButtonMint.setEnabled(false);
                        radioButtonNone.setEnabled(true);
                        radioButtonVanilla.setEnabled(true);
                        radioButtonChocolate.setEnabled(true);
                        break;

                    case  R.id.radioTea:
                        Toast.makeText(getApplicationContext(),"tea is selected",Toast.LENGTH_LONG).show();
                        radioButtonChocolate.setEnabled(false);
                        radioButtonVanilla.setEnabled(false);
                        radioButtonNone.setEnabled(true);
                        radioButtonLemon.setEnabled(true);
                        radioButtonMint.setEnabled(true);
                        break;

                    default:
                        Toast.makeText(getApplicationContext(),"chongu is selected",Toast.LENGTH_LONG).show();
                }
            }
        });


    }

}

