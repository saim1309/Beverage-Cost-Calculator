package com.example.beveragecostcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import  java.lang.String;

public class MainActivity extends AppCompatActivity {
    /*private static final String[] provinces = new String[]{
            "Quebec", "Nova Scotia", "New Brunswick", "Alberta", "Manitoba", "Newfoundland and Labrador", "Ontario", "Prince Edward Island", "Saskatchewan"
    };*/

    //declaring the variables
    private RadioGroup  radioGroupBeverages;
    private RadioGroup radioGroupFlavor;
    private RadioButton radioButtonNone;
    private RadioButton radioButtonVanilla;
    private RadioButton radioButtonChocolate;
    private RadioButton radioButtonMint;
    private RadioButton radioButtonLemon;
    //private RadioButton selectedFlavor;
    private  EditText name;
    private CheckBox milk;
    private CheckBox sugar;
    private  Spinner size;
    private  AutoCompleteTextView province;
    private Button calculate;
    String cupSize = "";
    String flavor = "";
    String selectedBeverages = "";
    String selectedFlavor = "";
    boolean needMilk = false;
    boolean needSugar =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setting up the value for the Spinner using Array Adapter and using dropdown menu
        Spinner spin = findViewById(R.id.spinnerSize);
        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(this,R.array.size,android.R.layout.simple_spinner_item);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapterSpinner);


        //setting up value for AutoComplete Text view using Array Adapter
        String[] provinces = getResources().getStringArray(R.array.provinces);
        AutoCompleteTextView act = findViewById(R.id.autoTextViewProvinces);
        ArrayAdapter<String> adapterAutoComplete = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, provinces);
        act.setAdapter(adapterAutoComplete);

        //mapping of all UI elements to variables
        radioGroupBeverages = findViewById(R.id.radioGroupBeverage);
        radioGroupFlavor = findViewById(R.id.radioGroupFlavors);
        radioButtonNone =  findViewById(R.id.radioNone);
        radioButtonVanilla = findViewById(R.id.radioVanilla);
        radioButtonChocolate = findViewById(R.id.radioChocolate);
        radioButtonLemon = findViewById(R.id.radioLemon);
        radioButtonMint = findViewById(R.id.radioMint);
        size = findViewById(R.id.spinnerSize);
        province = findViewById(R.id.autoTextViewProvinces);
        calculate = findViewById(R.id.btnCalculate);
        name = findViewById(R.id.editTextName);
        milk = findViewById(R.id.checkboxMilk);
        sugar = findViewById(R.id.checkboxSugar);

        //Disabling the radio buttons according to beverage type
        radioGroupBeverages.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            //function to check which radio button was selected in beverages
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.radioCoffee:     //if coffee radio button was selected, disable mint n lemon option
                        radioButtonLemon.setEnabled(false);
                        radioButtonMint.setEnabled(false);
                        radioButtonNone.setEnabled(true);
                        radioButtonVanilla.setEnabled(true);
                        radioButtonChocolate.setEnabled(true);
                        selectedBeverages = "Coffee";
                        break;

                    case  R.id.radioTea:    //if tea radio button was selected, disable vanilla n chocolate option
                        radioButtonChocolate.setEnabled(false);
                        radioButtonVanilla.setEnabled(false);
                        radioButtonNone.setEnabled(true);
                        radioButtonLemon.setEnabled(true);
                        radioButtonMint.setEnabled(true);
                        selectedBeverages = "Tea";
                        break;

                    default:
                        Toast.makeText(getApplicationContext(),"Beverage radio selection not working",Toast.LENGTH_LONG).show();
                }//end of switch
            } //end of onCheckedChanged function
        }); //end of listener for beverageRadio


        //checking which cup size is selected by user
        size.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cupSize = size.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //this will get the id of the radio button in flavor group which was selected

        //setting value of selectedFlavor chosen by user
        radioGroupFlavor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.radioChocolate:
                        selectedFlavor = "Chocolate";
                        break;
                    case  R.id.radioVanilla:
                        selectedFlavor = "Vanilla";
                        break;
                    case  R.id.radioLemon:
                        selectedFlavor = "Lemon";
                        break;
                    case  R.id.radioMint:
                        selectedFlavor = "Mint";
                        break;
                    case  R.id.radioNone:
                        selectedFlavor = "None";
                        break;
                }//end of switch
            }
        });

        //this setOnClickListener method will be called when calculate button is called
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //checking and setting up value if the checkbox for milk n sugar was checked
                if(milk.isChecked())
                    needMilk =true;
                if(sugar.isChecked())
                    needSugar=true;

                //calculation starts only iff all fields are filled
                if(name.getText().toString().isEmpty() || selectedBeverages.isEmpty()|| selectedFlavor.isEmpty()|| cupSize.isEmpty()||province.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Please Enter all details",Toast.LENGTH_LONG).show();
                }
                else{
                    //calling this function will compute the price of beverage
                    //this 'calculateBill' function has all the parameters required for computation
                    calculateBill(name.getText().toString(),selectedBeverages,selectedFlavor,cupSize,needMilk,needSugar,province.getText().toString());

                }

            }
        });

    }// end of onCreate

    //calculateBill function is defined and total cost is calculated
    public void calculateBill(String name,String selectedBeverage ,String selectedFlavor,String cupSize,boolean needMilk,boolean needSugar, String province)
    {

            double cost = 0.0;
            String milky ="without milk";
            String sugary ="without sugar";
            //adding the cost depending on the size of the cup
            if(size.equals("Small"))
            {
                cost = 1.50;
            }
            else if (size.equals("Medium"))
            {
                cost= 2.50;
            }
            else
                cost= 3.25;

            ////adding the cost depending on the flavor selected
            if(selectedFlavor.equals("Vanilla"))
                cost += 0.25;
            else if (selectedFlavor.equals("Chocolate"))
                cost+= 0.75;
            else if (selectedFlavor.equals("Lemon"))
                cost+= 0.25;
            else if (selectedFlavor.equals("Mint"))
                cost+=0.50;

            //adding the cost if user wants milk and sugar
            if(needMilk){
                cost+= 1.25;
                milky = "with milk";
            }
            if(needSugar){
                cost+=1.00;
                sugary = "with sugar";
            }


            //adding 13% tax to the cost of beverage
            double totalAmount = cost + (0.13*cost);
            double finalAmount = Math.round(totalAmount*100.0)/100.0; //rounding it 2 decimal places

             String output = "For "+name+" from "+province+", a "+cupSize+" "+selectedBeverage+","+milky+" and "+sugary+", "+selectedFlavor+" cost: $"+finalAmount;

            Toast.makeText(getApplicationContext(),output,Toast.LENGTH_LONG).show();






    }





}

