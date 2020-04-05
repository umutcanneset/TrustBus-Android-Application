package com.example.portomoti.trustbusse301project;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.Calendar;

public class SignUpSignUpActivity extends AppCompatActivity {

    EditText usernameTextFake, surnameText, emailText, passwordText ,dateofBirthText, ssnText,usernameText  ;
    Button dateButton;
    Context context=this;
    RadioButton male, female, acceptTerms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

      //  ParseUser.getCurrentUser().logOut();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_sign_up_page);

        usernameText =  findViewById(R.id.signUpSignUpActivityNameText);
        usernameTextFake = findViewById(R.id.signUpSignUpActivityEmailText);
        surnameText = findViewById(R.id.signUpSignUpActivityActivitySurnameText);
        emailText = findViewById(R.id.signUpSignUpActivityEmailText);
        dateofBirthText = findViewById(R.id.signUpSignUpActivityDateOfBirthText);
        ssnText = findViewById(R.id.signUpSignUpActivitySSNText);
        male = findViewById(R.id.signUpSignUpAccountActivityMaleRadioButton);
        female = findViewById(R.id.signUpSignUpAccountActivityFemaleRadioButton);
        passwordText = findViewById(R.id.signUpSignUpActivityPasswordText);
        dateButton=findViewById(R.id.signUpDateButton);



        /*
            //Remember User
            ParseUser parseUser = ParseUser.getCurrentUser();

            if (parseUser!= null){
                Toast.makeText(getApplicationContext(), "User Logged-In Automaticly", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),AdminActivity.class);
                startActivity(intent);
            }
        */


        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar= Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                final DatePickerDialog dpd= new DatePickerDialog(context,new DatePickerDialog.OnDateSetListener(){

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month += 1;

                        dateofBirthText.setText(day + "/" + month + "/" + year);

                    }

                },year,month,day);
                dpd.setButton(DatePickerDialog.BUTTON_POSITIVE,"Select",dpd);
                dpd.setButton(DatePickerDialog.BUTTON_NEGATIVE,"Cancel",dpd);
                dpd.getDatePicker().setMaxDate(calendar.getTimeInMillis());




                if(!((Activity) SignUpSignUpActivity.this).isFinishing())
                {
                    dpd.show();
                }
            }


        });
    }


    public void signUpSignUp(View view) {
        ParseUser user = new ParseUser();
        user.put("name",usernameText.getText().toString());
        user.setUsername(usernameTextFake.getText().toString());
        user.put("birthDay",dateofBirthText.getText().toString());
        user.setEmail(emailText.getText().toString());
        user.setPassword(passwordText.getText().toString());
        user.put("userSurname", surnameText.getText().toString());
        user.put("freeze", false);
        user.put("userType", 0);

        if (male != null) {
            user.put("sex", "Male");
        }
        if ((female != null)) {
            user.put("sex", "Female");
        }

     //   if (acceptTerms != null){
            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e != null) {
                        Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "successfully Signed Up", Toast.LENGTH_LONG).show();
                        //intent
                        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                        startActivity(intent);
                    }
                }
            });
       // }

    }
}
