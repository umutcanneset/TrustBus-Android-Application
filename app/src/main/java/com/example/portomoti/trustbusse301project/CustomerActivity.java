package com.example.portomoti.trustbusse301project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class CustomerActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);


        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if( item.getItemId() == R.id.Logout) {
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
        setContentView(R.layout.activity_customer);
        TextView userEmail = findViewById(R.id.textView3);
        String parseUserEmail = ParseUser.getCurrentUser().getEmail();
        userEmail.setText( parseUserEmail,TextView.BufferType.EDITABLE);

    }

    public void manageAccount(View view){

        //go ManageAccount page
        Intent intent = new Intent(getApplicationContext(),ManageAccountActivity.class);
        startActivity(intent);
    }
    public void listMyTickets(View view){

        //go My Tickets
        Intent intent = new Intent(getApplicationContext(),ListMyTicketsActivity.class);
        startActivity(intent);
    }

    public void listTrips(View view){

        //go Buy Ticket
        Intent intent = new Intent(getApplicationContext(),ListTripsForCustomerActivity.class);
        startActivity(intent);
    }

}
