package com.example.portomoti.trustbusse301project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

/**
 *
 */
public class AddManagerActivity  extends AppCompatActivity {

    EditText managerName, managerSurname, managerEmail, managerPassword, managerSsn, managerGsm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_manager);

        managerName = findViewById(R.id.addManagerActivityNameText);
        managerSurname = findViewById(R.id.addManagerActivitySurnameText);
        managerEmail =findViewById(R.id.addManagerActivityEmailText);
        managerPassword = findViewById(R.id.addManagerActivityPasswordText);
        managerSsn = findViewById(R.id.addManagerActivitySsnText);
        managerGsm = findViewById(R.id.addManagerActivityGsmText);

        //ParseUser parseUser = ParseUser.getCurrentUser();

    }

    public void addToDatabase(View view){



       // ParseUser.getCurrentUser().logOut();

        ParseUser user = new ParseUser();

        user.setUsername(managerName.getText().toString());
        user.put("userSurname", managerSurname.getText().toString());
        user.setEmail( managerEmail.getText().toString());
        user.setPassword(managerPassword.getText().toString());
        user.put("ssn" , managerSsn.getText().toString());
        user.put("gsm", managerGsm.getText().toString());
        user.put("freeze", false);
        user.put("userType",2);


        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if(e!= null){
                    Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Manager Added!", Toast.LENGTH_LONG).show();

                    //intent
                    Intent intent = new Intent(getApplicationContext(),AdminActivity.class);
                    startActivity(intent);
                }
            }
        });

        //old Version
/*
        user.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e!= null){
                    Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Manager Added!", Toast.LENGTH_LONG).show();

                    //intent
                    Intent intent = new Intent(getApplicationContext(),AdminActivity.class);
                    startActivity(intent);
                }
            }
        });
*/

    }
}
