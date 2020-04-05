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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import android.widget.TimePicker;

import java.util.Calendar;

public class AddTripActivity extends AppCompatActivity {
    Spinner fromSpinner,whereSpinner, busplate;

    String Cities[] = {"Istanbul", "Ankara", "Izmir", "Diyarbakır"};
    String Plates[] = {"34AC6111", "34EA1845", "34BPP61"};
    ArrayAdapter<String> adapter,adapter2;

    Button calenderButton;
    Button timeButton;
    String recordFrom = "";
    String recordWhere = "";
    String recordPlate = "";
    TextView dateText;
    TextView timeText;
    Context context=this;

    String DateALL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);





        //calender activity get date from calender
        //TextView dateTimeText = findViewById(R.id.addTripActivityTextView);

        /*
        String calenderDate = getIntent().getStringExtra("calenderDate");
        if (calenderDate!= null) {
            dateTimeText.setText(calenderDate + " 12:00");
                    DateALL = calenderDate + " 12:00"; //now only add 12:00 clock
        }
        */

        //calender Activity End
        fromSpinner = findViewById(R.id.addTripActivityFromSpinner);
        whereSpinner = findViewById(R.id.addTripActivityWhereSpinner);
        calenderButton = findViewById(R.id.addTripActivityDateButton);
        timeButton=findViewById(R.id.addTripActivityTimeButton);
        dateText = findViewById(R.id.addTripActivityTextView);
        timeText=findViewById(R.id.addTripActivityTextViewTime);
        busplate = findViewById(R.id.addTripActivityBusPlateSpinner);

        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,Cities);

        adapter2=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,Plates);

        calenderButton.setOnClickListener(new View.OnClickListener() {
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
                        dateText.setText(day + "/" + month + "/" + year+ " 12:00");

                    }

                },year,month,day);
                dpd.setButton(DatePickerDialog.BUTTON_POSITIVE,"Select",dpd);
                dpd.setButton(DatePickerDialog.BUTTON_NEGATIVE,"Cancel",dpd);
                dpd.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);


                if(!((Activity) AddTripActivity.this).isFinishing())
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

        busplate.setAdapter(adapter2);
        busplate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        recordPlate = "34AC6111";
                        break;
                    case 1:
                        recordPlate = "34EA1845";
                        break;
                    case 2:
                        recordPlate = "34BPP61";
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





        fromSpinner.setAdapter(adapter);
        fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
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
                switch (position){
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
    }

    public void goCalender(View view){
       // Intent intent = new Intent(getApplicationContext(), CalenderActivity.class);
        //startActivity(intent);
    }


    public void addTrip(View view) throws java.text.ParseException {

     //   String textFrom = fromText.getText().toString();
     //   String textDestionation = destinationText.getText().toString();
      //  String textDate = dateText.getText().toString();

//        Date date1 = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(textDate);


        //IF EKLENCEK AYNI DESTİNATIONA IZIN VERİLMEYECEK.
        if (recordFrom == recordWhere){
            Toast.makeText(getApplicationContext(), "From and Destination Cannot be same", Toast.LENGTH_LONG).show();
        }
        else {

            ParseObject object = new ParseObject("Trips");
            object.put("from", recordFrom);
            object.put("destination", recordWhere);
            object.put("date", dateText.getText().toString());
            object.put("time", timeText.getText().toString());
            object.put("deleted", false);
            object.put("delete", false);
            object.put("busID", recordPlate);
            object.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e != null) {
                        Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Trip uploaded", Toast.LENGTH_LONG).show();


                        ParseUser usr = ParseUser.getCurrentUser();

                        int usrType = usr.getInt("userType");
//HATA

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