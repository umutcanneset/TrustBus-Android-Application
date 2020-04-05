package com.example.portomoti.trustbusse301project;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.List;

/**
 * SignUp page, includes attirbutes that are taken from user in order to register.
 * At the end all informations are checked and push to ParseServer
 * If no exception occurs user get confirmation message.
 */
public class SignUpActivity extends AppCompatActivity {

    EditText emailText, passwordText;
    boolean freeze;
    CheckBox aautologin;

    /**
     * All attirbutes created if page is shown or another intent started.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

      //  ParseUser.getCurrentUser().logOut();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        emailText = findViewById(R.id.signUpActivityEmailText);
        passwordText = findViewById(R.id.signupActivityPasswordText);
        aautologin = findViewById(R.id.signUpActivityAutoLoginCheckBox);



            //Remember User
            ParseUser parseUser = ParseUser.getCurrentUser();



           // boolean booleanAuto = true;//parseUser.getBoolean("autologin");

            if (parseUser!= null) {
                boolean booleanauto= parseUser.getBoolean("autologin");
                int userType = parseUser.getInt("userType");
                if (userType == 0 && booleanauto==true )  {
                    Toast.makeText(getApplicationContext(), "User Logged-In Automaticly", Toast.LENGTH_LONG).show();
                    //intent
                    Intent intent = new Intent(getApplicationContext(), CustomerActivity.class);
                    startActivity(intent);

                }
                else if (userType == 2&& booleanauto==true) {
                    Toast.makeText(getApplicationContext(), "User Logged-In Automaticly", Toast.LENGTH_LONG).show();
                    //intent
                    Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                    startActivity(intent);

                }
                else if (userType == 1&& booleanauto==true) {
                    Toast.makeText(getApplicationContext(), "User Logged-In Automaticly", Toast.LENGTH_LONG).show();
                    //intent
                    Intent intent = new Intent(getApplicationContext(), ManagerActivity.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(getApplicationContext(), "Please Check For Autologin", Toast.LENGTH_LONG).show();
                }
            }






    }

    /**
     * Getting the user info match on Database, push query to ParseServer
     * @param view
     */
    public void signIn (View view) {



       //Parse Query
        final ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereEqualTo("email",emailText.getText().toString());
        query.findInBackground(new FindCallback<ParseUser>() {
            /**
             * Messages send from Parse to upper query.
             * @param objects
             * @param e
             */
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                  //if no error taken
                if (e==null){
                    if (objects.size()>0) {
                        freeze = objects.get(0).getBoolean("freeze");
                    }
                    //If exception occurs.
                }else{

                    Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                   //Toast.makeText(getApplicationContext(), "User Freezed", Toast.LENGTH_LONG).show();
                   // e.printStackTrace();

                }
            }
        });


                ParseUser.logInInBackground(emailText.getText().toString(), passwordText.getText().toString(), new LogInCallback() {
                    /**
                     * @param user
                     * @param e
                     */
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (e != null) {
                            Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        } else {


                            if (freeze==true){
                                Toast.makeText(getApplicationContext(), "User Freezed", Toast.LENGTH_LONG).show();
                                ParseUser.getCurrentUser().logOut();
                                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                                startActivity(intent);
                            }
                            else {
                                //check autologin
                                ParseUser usr = ParseUser.getCurrentUser();
                                if(aautologin.isChecked()){
                                    usr.put("autologin",true);
                                }else{
                                    usr.put("autologin",false);

                                }
                                usr.saveInBackground();


                                Toast.makeText(getApplicationContext(), "Welcome " + user.getUsername(), Toast.LENGTH_LONG).show();

                                int userType = user.getInt("userType");


                   //Checking the user type in order to start another View
                    if (userType == 0){

                        //intent for Customer Login
                        Intent intent = new Intent(getApplicationContext(),CustomerActivity.class);
                        startActivity(intent);

                    }

                    //intent for Manager Login
                    else if (userType == 1){
                        //intent
                        Intent intent = new Intent(getApplicationContext(),ManagerActivity.class);
                        startActivity(intent);

                    }


                    //intent for Admin Login
                    else if (userType == 2){
                        //intent
                        Intent intent = new Intent(getApplicationContext(),AdminActivity.class);
                        startActivity(intent);
                    }
                    else{
                        //intent for Guest without any Login
                        Intent intent = new Intent(getApplicationContext(),CustomerActivity.class);
                        startActivity(intent);

                    }

                            }
                        }

                    }
                });
         }

    /**
     * Connecting another view as SignUpSignUPActivity
     * @param view
     */
    public void signUp(View view) {


        Intent intent = new Intent(getApplicationContext(),SignUpSignUpActivity.class);
        startActivity(intent);

        /*
        ParseUser user = new ParseUser();

        user.setUsername(usernameTextFake.getText().toString());
        user.setEmail(emailText.getText().toString());
        user.setPassword(passwordText.getText().toString());
        user.put("freeze", false);
        user.put("userType",0);
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "User Created", Toast.LENGTH_LONG).show();
                    //intent
                    Intent intent = new Intent(getApplicationContext(),AdminActivity.class);
                    startActivity(intent);
                }
            }
        });
*/

    }

    /**
     * Guest user Button directs to another View.
     * @param view
     */
    public void asGuest(View view){
        Intent intent = new Intent(getApplicationContext(),GuestMenuActivity.class);
        startActivity(intent);
    }
}
