package com.example.portomoti.trustbusse301project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class listTripsForGuest extends AppCompatActivity {

    ListView listView;
    ArrayList<String> fromFromParse;
    ArrayList<String> whereFromParse;
    ArrayList<String> dateFromParse; //String ---> DATE
    PostActivity postActivity ;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_trips);

        listView = findViewById(R.id.listView);

        fromFromParse= new ArrayList<>();
        whereFromParse=new ArrayList<>();
        dateFromParse= new ArrayList<>();

        postActivity= new PostActivity(fromFromParse,whereFromParse,dateFromParse,this);

        listView.setAdapter(postActivity);


        download();

    }

    public void download(){
        ParseQuery<ParseObject> query= ParseQuery.getQuery("Trips");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e!=null){
                    Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_LONG).show();

                }else{

                    if(objects.size()>0){
                        for(ParseObject object: objects){


                            fromFromParse.add(object.getString("from"));
                            whereFromParse.add(object.getString("destination"));
                            dateFromParse.add(object.getString("date")); // String ---->getDate

                            postActivity.notifyDataSetChanged();
                        }
                    }
                }
            }
        });
    }


}
