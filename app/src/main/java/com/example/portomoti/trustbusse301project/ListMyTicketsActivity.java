package com.example.portomoti.trustbusse301project;

import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ListMyTicketsActivity extends AppCompatActivity {
    ListView listViewCustomerActivity;
    ArrayList<String> fromFromParse;
    ArrayList<String> whereFromParse;
    ArrayList<String> emailFormParse;
    ArrayList<String> dateFromParse; //String ---> DATE
    PostActivityForCustomerTickets postActivityCustomerForCustomerTicket ;

    ArrayAdapter<String> adapter;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

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
        setContentView(R.layout.activity_list_my_tickets);

        listViewCustomerActivity =  findViewById(R.id.activityListView331);

        emailFormParse = new ArrayList<>();
        fromFromParse= new ArrayList<>();
        whereFromParse=new ArrayList<>();
        dateFromParse= new ArrayList<>();





        postActivityCustomerForCustomerTicket= new PostActivityForCustomerTickets(emailFormParse,fromFromParse,whereFromParse,dateFromParse,this);

        listViewCustomerActivity.setAdapter(postActivityCustomerForCustomerTicket);


        download();

    }

    public void download(){
        ParseUser usr = ParseUser.getCurrentUser();

        ParseQuery<ParseObject> query= ParseQuery.getQuery("ticketUser");
        //  query.whereEqualTo(email12.toString(),usr.getEmail()); // diğerlerine de eklencek ---->>> 9:31Pm eklenince Silincek
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e!=null){
                    Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_LONG).show();

                }else{

                    if(objects.size()>0){
                        for(ParseObject object: objects){

                            emailFormParse.add(object.getString("email"));
                            fromFromParse.add(object.getString("from"));
                            whereFromParse.add(object.getString("destination"));
                            dateFromParse.add(object.getString("date")); // String ---->getDate

                            postActivityCustomerForCustomerTicket.notifyDataSetChanged();
                        }
                    }
                }
            }
        });
    }


}
