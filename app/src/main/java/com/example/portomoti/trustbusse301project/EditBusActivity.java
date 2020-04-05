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

public class EditBusActivity extends AppCompatActivity {

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.main_menu,menu);

            return super.onCreateOptionsMenu(menu);


        }
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            if (item.getItemId() == R.id.Logout) {
                ParseUser.logOutInBackground(new LogOutCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e != null) {
                            Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        } else {
                            Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
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
            setContentView(R.layout.activity_edit_bus_schedule);
        }


        public void addTrip(View view){

            //go addManager page
            Intent intent = new Intent(getApplicationContext(),AddTripActivity.class);
            startActivity(intent);
        }

        public void deleteTrip(View view){

            //go addManager page
            Intent intent = new Intent(getApplicationContext(),ListTripsForAdminActivity.class);
            startActivity(intent);
        }

        public void updateTrip(View view){

        //go addManager page
        Intent intent = new Intent(getApplicationContext(),ListTripsForAdminForUpdateActivity.class);
        startActivity(intent);
        }



}
