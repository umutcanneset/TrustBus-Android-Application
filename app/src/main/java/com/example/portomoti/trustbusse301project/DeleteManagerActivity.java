package com.example.portomoti.trustbusse301project;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class DeleteManagerActivity extends AppCompatActivity {

    EditText managerEmail;
    boolean freeze = true;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_delete_manager);

        managerEmail =findViewById(R.id.deleteManagerActivityManagerEmailText);


    }


    public void deleteManager(View view){



        final ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereEqualTo("email", managerEmail.getText().toString());
        query.findInBackground(new FindCallback<ParseUser>() {
            public void done(final List<ParseUser> objects, ParseException e) {
                    //Toast.makeText(getApplicationContext(), "2!", Toast.LENGTH_LONG).show();
                if (e == null) {

                    ParseUser.deleteAllInBackground(objects, new DeleteCallback() {
                        @Override
                        public void done(ParseException e) {

                            String firstItemId = objects.get(0).getObjectId();

                            Toast.makeText(DeleteManagerActivity.this, firstItemId , Toast.LENGTH_SHORT).show();
                            ParseUser usr = ParseUser.getCurrentUser();

                            int usrType = usr.getInt("userType");


                            //intent for Admin Login
                            if (usrType == 1) {
                                //intent
                                Intent intent = new Intent(getApplicationContext(), ManagerActivity.class);
                                startActivity(intent);

                            }
                            //intent for Manager Login
                            else if (usrType == 2) {
                                //intent
                                Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                                startActivity(intent);

                            } else {
                                Toast.makeText(getApplicationContext(), "User Type Undefined", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                                startActivity(intent);
                            }


                        }

                    });



                    //  Toast.makeText(getApplicationContext(), "OK!", Toast.LENGTH_LONG).show();



                }
                    // The query was successful.
                 else {
                    // Something went wrong.
                    Toast.makeText(getApplicationContext(), "Not!", Toast.LENGTH_LONG).show();
                }
            }
        });










/*
        final ParseQuery<ParseUser> query = ParseUser.getQuery();
        //ParseUser user = new ParseUser();
        query.whereEqualTo("email", "umut@gmail.com");
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> results, ParseException e) {
                   if (e == null) {
                       String firstItemId = results.get(0).getObjectId();
                       Toast.makeText(DeleteManagerActivity.this, firstItemId , Toast.LENGTH_SHORT).show();

                      //  for (ParseUser reply : results){
                  //      reply.deleteInBackground();
                  //      Toast.makeText(getApplicationContext(), "Deleted2!", Toast.LENGTH_LONG).show();
                 //  }

                 //   Toast.makeText(getApplicationContext(), "Deleted!", Toast.LENGTH_LONG).show();
                  //  Intent intent =new Intent(getApplicationContext(), AdminActivity.class);
                  //  startActivity(intent);
                 //   finish();


                }
             else {
                    Toast.makeText(getApplicationContext(), "Not deleted!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();

                        //for (ParseObject object : objects) {

                        //    object.deleteInBackground();
                            /*
                            String objectName = object.getString("username");
                            String objectCalories = object.getString("userSurname");
                            System.out.println("object name: " + objectName);
                            System.out.println("object calories" + objectCalories);
                            */
                        //}
       //         }
     //       }
   //     });

        }
    }

