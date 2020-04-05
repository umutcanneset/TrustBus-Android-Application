package com.example.portomoti.trustbusse301project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.CalendarView;

public class CalenderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);


        CalendarView calendarView = findViewById(R.id.calenderActivityCalenderView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int i1, int i2, int i3) {
                String calenderDate= i3 + "/" + i2 + "/" + i1;


                Intent intent = new Intent(getApplicationContext(), AddTripActivity.class);
                intent.putExtra("calenderDate",calenderDate);

                startActivity(intent);

            }
        });



    }
}
