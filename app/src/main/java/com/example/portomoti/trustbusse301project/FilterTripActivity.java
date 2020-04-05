package com.example.portomoti.trustbusse301project;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class FilterTripActivity extends AppCompatActivity  {

    Spinner fromSpinner,toSpinner;
    TextView dateChosenGo,dateChosenTurn,returnDateTexView;
    Button setDate,list,setDateReturn;
    Switch oneWay;
    Context context=this;
    String Cities[] = {"Istanbul", "Ankara", "Izmir", "Diyarbakır"};
    ArrayAdapter<String> adapter;
    String recordFrom = "";
    String recordWhere = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_trip);

        fromSpinner=findViewById(R.id.fromSpinner);
        toSpinner=findViewById(R.id.toSpinner);
        setDate=findViewById(R.id.setDateButton);
        list=findViewById(R.id.listButton);
        dateChosenGo =findViewById(R.id.dateTextView);
        oneWay=findViewById(R.id.switchOneWay);
        dateChosenTurn=findViewById(R.id.chosenReturnDate);
        setDateReturn=findViewById(R.id.setDateButton2);
        returnDateTexView = findViewById(R.id.returnDateTextView);

        setDateReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dpd = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Aylar DatePicker Objesinde 0'dan başladığı için 1 ekliyoruz.
                        month+=1;
                        //TextView'a kullanıcının seçtiği tarihi set ediyoruz.

                        dateChosenTurn.setText(dayOfMonth + "/" + month + "/" + year);

                    }
                },year,month,day);
                dpd.setButton(DatePickerDialog.BUTTON_POSITIVE,"Select",dpd);
                dpd.setButton(DatePickerDialog.BUTTON_NEGATIVE,"Cancel",dpd);

                if(!((Activity) FilterTripActivity.this).isFinishing())
                {
                    dpd.show();
                }

            }
        });

        setDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dpd = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Aylar DatePicker Objesinde 0'dan başladığı için 1 ekliyoruz.
                        month+=1;
                        //TextView'a kullanıcının seçtiği tarihi set ediyoruz.

                        dateChosenGo.setText(dayOfMonth + "/" + month + "/" + year);

                    }
                },year,month,day);
                dpd.setButton(DatePickerDialog.BUTTON_POSITIVE,"Select",dpd);
                dpd.setButton(DatePickerDialog.BUTTON_NEGATIVE,"Cancel",dpd);

                if(!((Activity) FilterTripActivity.this).isFinishing())
                {
                    dpd.show();
                }
            }
        });

        oneWay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(!isChecked){
                    // If the switch button is on One-Way Trip Filtering

                  setDateReturn.setVisibility(View.VISIBLE);
                  dateChosenTurn.setVisibility(View.VISIBLE);
                  returnDateTexView.setVisibility(View.VISIBLE);
                }
                 else{

                    // If the switch button is off Two-Way Ticket
                    setDateReturn.setVisibility(View.INVISIBLE);
                    dateChosenTurn.setVisibility(View.INVISIBLE);
                    returnDateTexView.setVisibility(View.INVISIBLE);

                }

            }
        });
      }

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

}

