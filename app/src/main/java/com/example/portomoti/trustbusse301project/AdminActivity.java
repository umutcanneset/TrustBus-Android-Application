package com.example.portomoti.trustbusse301project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class AdminActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if( item.getItemId() == R.id.Logout){
            ParseUser.logOutInBackground(new LogOutCallback() {
                @Override
                public void done(ParseException e) {
                    if (e != null){
                        Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }else{
                        Intent intent = new Intent(getApplicationContext(),SignUpActivity.class);
                        startActivity(intent);
                    }
                }
            });
        }



        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }

    public void addManager(View view){

            //go addManager page
            Intent intent = new Intent(getApplicationContext(),AddManagerActivity.class);
            startActivity(intent);
    }

    public void deleteManager(View view){

        //go addManager page
        Intent intent = new Intent(getApplicationContext(),DeleteManagerActivity.class);
        startActivity(intent);
    }
    public void manageBooking(View view){

        //go addManager page
        Intent intent = new Intent(getApplicationContext(),ManageBookingActivity.class);
        startActivity(intent);
    }

    public void editBus(View view){

        //go addManager page
        Intent intent = new Intent(getApplicationContext(),EditBusActivity.class);
        startActivity(intent);
    }

}
