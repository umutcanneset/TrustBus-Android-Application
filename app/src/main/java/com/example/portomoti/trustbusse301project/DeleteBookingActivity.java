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

public class DeleteBookingActivity extends AppCompatActivity {
    ListView listViewManager;
    ArrayList<String> objectId;
    ArrayList<String> fromFromParse;
    ArrayList<String> whereFromParse;
    ArrayList<String> dateFromParse; //String ---> DATE
    PostActivityForAdmin postActivityAdmin;
    Button deleteBooking;
    EditText objectIdText;
    String selected=null;


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);


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
        setContentView(R.layout.activity_delete_booking);
        objectIdText = findViewById(R.id.activityDeleteObjectText);
        deleteBooking = findViewById(R.id.activityDeleteBookingDeleteButton);
        listViewManager = findViewById(R.id.activityDeleteList);

        objectId = new ArrayList<>();
        fromFromParse = new ArrayList<>();
        whereFromParse = new ArrayList<>();
        dateFromParse = new ArrayList<>();

        postActivityAdmin = new PostActivityForAdmin(objectId, fromFromParse, whereFromParse, dateFromParse, this);
        listViewManager.setAdapter(postActivityAdmin);

        listViewManager.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                selected = (listViewManager.getItemAtPosition(position).toString());


                objectIdText.setText(selected);


            }
        });
        download();
    }
    public void download(){
        ParseQuery<ParseObject> query= ParseQuery.getQuery("Ticket");
        query.whereEqualTo("deleted",false);
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
                            whereFromParse.add(object.getString("where"));
                            dateFromParse.add(object.getString("date")); // String ---->getDate

                            postActivityAdmin.notifyDataSetChanged();
                        }
                    }
                }
            }
        });
    }
    public void deleteWrittenBooking(View view) {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Ticket");

        query.getInBackground(objectIdText.getText().toString(), new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e != null) {
                    e.printStackTrace();
                } else {
                    object.put("deleted",true);
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
