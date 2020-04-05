package com.example.portomoti.trustbusse301project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class ManageAccountActivity extends AppCompatActivity {

    EditText usernameTextFake, surnameText, emailText, passwordText ,dateofBirthText, ssnText,usernameText  ;
    RadioButton male, female;
    Switch freezeSwitch;
    boolean freeze;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_manage_account);

        usernameText=findViewById(R.id.ManageAccountActivityNameText);
        usernameTextFake= findViewById(R.id.ManageAccountActivityEmailText);
        surnameText = findViewById(R.id.signUpSignUpActivityActivitySurnameText);
        emailText = findViewById(R.id.ManageAccountActivityEmailText);
        dateofBirthText = findViewById(R.id.ManageAccountActivityDateOfBirth);
        ssnText = findViewById(R.id.ManageAccountActivitySSNText);
        male = findViewById(R.id.ManageAccountActivityMaleRadioButton);
        female = findViewById(R.id.ManageAccountActivityFemailRadioButton);
        passwordText = findViewById(R.id.ManageAccountActivityPasswordText);
        freezeSwitch = findViewById(R.id.ManageAccountactivityFreezeSwitch);
    }



    public void saveChanges(View view){
        ParseUser user = ParseUser.getCurrentUser();

        if(usernameTextFake != null) {
            user.setUsername(usernameTextFake.getText().toString());
        }
        if (usernameText!= null) {
            user.put("name", usernameText.getText().toString());
        }

        if (surnameText != null){
            user.put("userSurname", surnameText.getText().toString());
        }
        if (passwordText!=null) {
            user.setPassword(passwordText.getText().toString());
        }
        if (emailText != null) {
            user.setEmail(emailText.getText().toString());
        }
        if (freezeSwitch.isChecked()==true){
            user.put("freeze" , true);
        }

        /*
        if (dateofBirthText != null){
            user.put("dateOfBirth",dateofBirthText.getText());
        }
        */
        if (male.isChecked() == true){
            user.put("sex","Male");
        }
        if ((female.isChecked() == true)){
            user.put("sex","Female");
        }


//        user.put("ssn", ssnText.getText().toString());

        user.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Changes Saved", Toast.LENGTH_LONG).show();
                    //intent
                    Intent intent = new Intent(getApplicationContext(),CustomerActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}
