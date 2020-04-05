package com.example.portomoti.trustbusse301project;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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
import java.util.List;

public class ListTripsForCustomerActivity extends AppCompatActivity {

    ListView listViewCustomer;
    ArrayList<String> objectId;
    ArrayList<String> fromFromParse;
    ArrayList<String> whereFromParse;
    ArrayList<String> dateFromParse;//String ---> DATE
    String selected=null;

    PostActivityForCustomer postActivityForCustomer ;
    Button buyTrip;
    EditText objectIdText;

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
        setContentView(R.layout.activity_list_trips_for_customer);

        objectIdText = findViewById(R.id.activityListTripForCustomerObjectIdTextUpdateTrip);
        buyTrip = findViewById(R.id.activityListTripsForCustomerBuyButton);

        listViewCustomer = findViewById(R.id.listViewCustomerBuyTrip);

        objectId= new ArrayList<>();
        fromFromParse= new ArrayList<>();
        whereFromParse=new ArrayList<>();
        dateFromParse= new ArrayList<>();



        postActivityForCustomer = new PostActivityForCustomer(objectId,fromFromParse,whereFromParse,dateFromParse,this);

        listViewCustomer.setAdapter(postActivityForCustomer);
        listViewCustomer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                
                 selected = (listViewCustomer.getItemAtPosition(position).toString());
              //  objectIdText.setText(selected,TextView.BufferType.EDITABLE);

                Intent i = new Intent(getApplicationContext(),SeatSelectionActivity.class);
                //String example;
                i.putExtra("STRING_I_NEED",selected.toString());
                startActivity(i);


            }
        });




        download();

    }

    public void download(){
        ParseQuery<ParseObject> query= ParseQuery.getQuery("Trips");
        query.whereEqualTo("deleted",false); // diğerlerine de eklencek ---->>> 9:31Pm eklenince Silincek
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e!=null){
                    Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_LONG).show();

                }else{

                    if(objects.size()>0){
                        for(ParseObject object: objects){

                            objectId.add(object.getObjectId());
                            fromFromParse.add(object.getString("from"));
                            whereFromParse.add(object.getString("destination"));
                            dateFromParse.add(object.getString("date")); // String ---->getDate

                            postActivityForCustomer.notifyDataSetChanged();
                        }
                    }
                }
            }
        });
    }

    public void buyTrip(View view) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Trips");

        query.getInBackground(objectIdText.getText().toString(), new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e != null) {
                    e.printStackTrace();
                } else {
                    Intent intent = new Intent(getApplicationContext(),PaymentActivity.class);
                    startActivity(intent);

                   // intent.putExtra("objectId",objectId);
                   // startActivity(intent);
                }
            }
        });





    }
}
