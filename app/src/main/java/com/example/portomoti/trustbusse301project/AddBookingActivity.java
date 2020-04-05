package com.example.portomoti.trustbusse301project;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.Calendar;

public class AddBookingActivity extends AppCompatActivity {
    Spinner fromSpinner, destinationSpinner;
    TextView email;
    TextView dateText;
    TextView timeText;
    String Cities[] = {"Istanbul", "Ankara", "Izmir", "Diyarbakır"};
    ArrayAdapter<String> adapter;
    Button calendarButton;
    Button timeButton;
    String recordFrom = "";
    String recordDestination = "";
    Context context=this;

    String DateALL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_booking);


        TextView dateTimeText = findViewById(R.id.addBookingActivityTextView);

      /*
        String calenderDate = getIntent().getStringExtra("calenderDate");
        if (calenderDate != null) {
            dateTimeText.setText(calenderDate + " 12:00");
            DateALL = calenderDate + " 12:00"; //now only add 12:00 clock
        }
       */

       email=findViewById(R.id.addBookingActivityUsername);
        fromSpinner = findViewById(R.id.addBookingActivityFromSpinner);
        destinationSpinner = findViewById(R.id.addBookingActivityDestinationSpinner);
        calendarButton = findViewById(R.id.addBookingActivityDateButton);
        dateText=findViewById(R.id.addBookingActivityTextView);
        timeText=findViewById(R.id.addBookingActivityTimeView);
        timeButton=findViewById(R.id.addBookingActivityTimeButton);



        calendarButton.setOnClickListener(new View.OnClickListener() {
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

                        dateText.setText(day + "/" + month + "/" + year+" 12:00");
                    }

                },year,month,day);
                dpd.setButton(DatePickerDialog.BUTTON_POSITIVE,"Select",dpd);
                dpd.setButton(DatePickerDialog.BUTTON_NEGATIVE,"Cancel",dpd);

                dpd.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);


                if(!((Activity) AddBookingActivity.this).isFinishing())
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

        destinationSpinner.setAdapter(adapter);
        destinationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        recordDestination = "Istanbul";
                        break;
                    case 1:
                        recordDestination = "Ankara";
                        break;
                    case 2:
                        recordDestination = "Izmir";
                        break;
                    case 3:
                        recordDestination = "Diyarbakır";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    public void moveBookingCalendar(View view){

    }




    public void addBooking(View view){
        if (recordFrom == recordDestination){
            Toast.makeText(getApplicationContext(), "From and Destination Cannot be same", Toast.LENGTH_LONG).show();
        }
        else {
            ParseObject object = new ParseObject("Ticket");
            object.put("e_mail", email.getText().toString());
            object.put("from", recordFrom);
            object.put("where", recordDestination);
            object.put("date", dateText.getText().toString());
            object.put("time",timeText.getText().toString());
            object.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e != null) {
                        Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(getApplicationContext(), "Booking Uploaded.", Toast.LENGTH_LONG).show();

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






}

