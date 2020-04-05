package com.example.portomoti.trustbusse301project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class GuestMenuActivity extends AppCompatActivity {






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_menu);
    }

    public void listTrips (View view){
        Intent intent = new Intent(getApplicationContext(),listTripsForGuest.class);
        startActivity(intent);
    }
    public void register(View view){
        Intent intent = new Intent(getApplicationContext(),SignUpSignUpActivity.class);
        startActivity(intent);
    }

}
