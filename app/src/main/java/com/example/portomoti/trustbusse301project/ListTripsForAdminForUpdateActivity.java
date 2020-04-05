package com.example.portomoti.trustbusse301project;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.TimePicker;
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

public class ListTripsForAdminForUpdateActivity extends AppCompatActivity {

    ListView listViewAdmin;
    ArrayList<String> objectId;
    ArrayList<String> fromFromParse;
    ArrayList<String> whereFromParse;
    ArrayList<String> dateFromParse; //String ---> DATE
    PostActivityForAdmin postActivityAdmin ;
    String selected=null;

    Context context=this;


    ArrayAdapter<String> adapter;
    Spinner fromSpinner,whereSpinner;
    String Cities[] = {"Istanbul", "Ankara", "Izmir", "Diyarbakır"};
    Button updateTrip,updateCelanderButton;
    EditText objectIdText;
    TextView dateText;
    Button timeButton;
    TextView timeText;



    String recordFrom = "";
    String recordWhere = "";

    String DateALL;

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
        setContentView(R.layout.activity_list_trips_for_admin_for_update_trip);


        /*
        //calender
        TextView dateTimeText = findViewById(R.id.listTripActivityForAdminForUpdateTripDateText);

        String calenderDate = getIntent().getStringExtra("calenderDateForUpdate");
        if (calenderDate != null) {
            dateTimeText.setText(calenderDate + " 12:00");
            DateALL = calenderDate + " 12:00"; //now only add 12:00 clock
        }
        */
        dateText=findViewById(R.id.listTripActivityForAdminForUpdateTripDateText);
        fromSpinner = findViewById(R.id.listTripsForAdminForUpdateFromSpinner);
        whereSpinner = findViewById(R.id.listTipsActivityForAdminForUpdateDestinationSpinner);
        updateCelanderButton = findViewById(R.id.listTripForAdminFroUpdateTripDepartureDateButton);
        timeText=findViewById(R.id.listTripsForAdminTimeText);
        timeButton=findViewById(R.id.listTripsForAdminTimeButton);


        updateCelanderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar= Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                final DatePickerDialog dpd= new DatePickerDialog(context,new DatePickerDialog.OnDateSetListener(){

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month += 1;
                        dateText.setText(day + "/" + month + "/" + year);

                    }

                },year,month,day);
                dpd.setButton(DatePickerDialog.BUTTON_POSITIVE,"Select",dpd);
                dpd.setButton(DatePickerDialog.BUTTON_NEGATIVE,"Cancel",dpd);
                dpd.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);


                if(!((Activity) ListTripsForAdminForUpdateActivity.this).isFinishing())
                {
                    dpd.show();
                }
            }
        });

        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar callendar = Calendar.getInstance();
                int hour = callendar.get(Calendar.HOUR_OF_DAY);
                int minute = callendar.get(Calendar.MINUTE);

                TimePickerDialog tpd = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // hourOfDay ve minute değerleri seçilen saat değerleridir.
                        // Edittextte bu değerleri gösteriyoruz.
                        timeText.setText(hourOfDay + ":" + minute);
                    }
                }, hour, minute, true);


// dialog penceresinin button bilgilerini ayarlıyoruz ve ekranda gösteriyoruz.
                tpd.setButton(TimePickerDialog.BUTTON_POSITIVE, "Seç", tpd);
                tpd.setButton(TimePickerDialog.BUTTON_NEGATIVE, "İptal", tpd);
                tpd.show();
            }
        });






            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Cities);

        fromSpinner.setAdapter(adapter);
        fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        recordFrom = "Istanbul";
                        break;
                    case 1:
                        recordFrom = "Ankara";
                        break;
                    case 2:
                        recordFrom = "Izmir";
                        break;
                    case 3:
                        recordFrom = "Diyarbakır";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        whereSpinner.setAdapter(adapter);
        whereSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        recordWhere = "Istanbul";
                        break;
                    case 1:
                        recordWhere = "Ankara";
                        break;
                    case 2:
                        recordWhere = "Izmir";
                        break;
                    case 3:
                        recordWhere = "Diyarbakır";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        objectIdText = findViewById(R.id.activityListTripForCustomerObjectIdTextUpdateTrip);
        updateTrip = findViewById(R.id.activityListTripsForCustomerBuyButton);

        listViewAdmin = findViewById(R.id.listViewCustomerBuyTrip);

        objectId= new ArrayList<>();
        fromFromParse= new ArrayList<>();
        whereFromParse=new ArrayList<>();
        dateFromParse= new ArrayList<>();

        postActivityAdmin= new PostActivityForAdmin(objectId,fromFromParse,whereFromParse,dateFromParse,this);

        listViewAdmin.setAdapter(postActivityAdmin);
        listViewAdmin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                selected = (listViewAdmin.getItemAtPosition(position).toString());


                objectIdText.setText(selected);


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

                            postActivityAdmin.notifyDataSetChanged();
                        }
                    }
                }
            }
        });
    }

    public void goCalender(View view){
       // Intent intent = new Intent(getApplicationContext(), CalenderActivityUpdate.class);
        //startActivity(intent);
    }
    public void updateTrip(View view) {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Trips");

        query.getInBackground(objectIdText.getText().toString(), new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e != null) {
                    e.printStackTrace();
                } else {
                    if(recordFrom != null) {
                        object.put("from", recordFrom);
                    }
                    if(recordWhere != null) {
                        object.put("destination", recordWhere);
                    }
                    if(dateText != null) {
                        object.put("date", dateText.getText().toString());
                    }
                    if(timeText!=null){
                        object.put("time",timeText.getText().toString());
                    }

                    object.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e != null) {
                                Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Changes Saved", Toast.LENGTH_LONG).show();
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

                        }
                    });
                }
            }
        });


    }
}
