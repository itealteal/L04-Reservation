package com.example.l04_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name;
    EditText number;
    EditText pax;
    DatePicker date;
    TimePicker time;
    CheckBox smoking;
    CheckBox baby;
    Button submit;
    Button reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.inputName);
        number = findViewById(R.id.inputNumber);
        pax = findViewById(R.id.inputPax);
        date = findViewById(R.id.datePicker);
        time = findViewById(R.id.timePicker);
        smoking = findViewById(R.id.smoking);
        baby = findViewById(R.id.baby);
        submit = findViewById(R.id.submit);
        reset = findViewById(R.id.reset);

        date.updateDate(2021,5,1);
        time.setCurrentMinute(30);
        time.setCurrentHour(19);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message="";
                if(name.getText().toString().isEmpty()){
                    message = "Please enter name";
                }else if(number.getText().toString().isEmpty()){
                    message = "Please enter mobile number";
                }else if(pax.getText().toString().isEmpty()) {
                    message = "Please enter number of pax";
                }else if(time.getCurrentHour() < 8) {
                    message = "We open at 8am";
                }else if(time.getCurrentHour() >= 22)    {
                    message = "We close at 10pm";
                }else {
                    message = String.format("Name: %s \nNumber: %s\nPax: %s\nDate: %02d/%02d/%d\nTime: %02d:%02d\n", name.getText().toString(), number.getText().toString(), pax.getText().toString(), date.getDayOfMonth(), date.getMonth() + 1, date.getYear(), time.getCurrentHour(), time.getCurrentMinute());
                    if (smoking.isChecked() && baby.isChecked()) {
                        message += "Smoking Area: Yes\nBaby Chair: Yes";
                    } else if (smoking.isChecked()) {
                        message += "Smoking Area: Yes\nBaby Chair: No";
                    } else if (baby.isChecked()) {
                        message += "Smoking Area: No\nBaby Chair: Yes";
                    } else {
                        message += "Smoking Area: No\nBaby Chair: No";
                    }
                }
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name.setText("");
                number.setText("");
                pax.setText("");
                date.updateDate(2021,5,1);
                time.setCurrentMinute(30);
                time.setCurrentHour(19);
                smoking.setChecked(false);
                baby.setChecked(false);


            }
        });

    }
}