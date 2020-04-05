package com.example.portomoti.trustbusse301project;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class PostActivityForAdmin extends ArrayAdapter<String> {
    private final ArrayList<String> objectId;
    private final ArrayList<String> from;
    private final ArrayList<String> where;
    private final ArrayList<String> date; //String ---> DATE
    private final Activity context;

    public PostActivityForAdmin(ArrayList<String> from, ArrayList<String> objectId, ArrayList<String> where, ArrayList<String> date, Activity context){
//String ----> Date
        super(context,R.layout.custom_view_list_trips_for_admin,from);

        this.from=from;
        this.objectId=objectId;
        this.where=where;
        this.date=date;
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater=this.context.getLayoutInflater();
        View customViewAdmin=layoutInflater.inflate(R.layout.custom_view_list_trips_for_admin,null,true);

        TextView objectIdText=customViewAdmin.findViewById(R.id.custom_view_for_admin_objectID_text);
        TextView fromText=customViewAdmin.findViewById(R.id.custom_view_for_admin_from_text);
        TextView whereText=customViewAdmin.findViewById(R.id.custom_view_for_admin_where_text);
        TextView dateText=customViewAdmin.findViewById(R.id.custom_view_for_admin_date_text);


        fromText.setText(from.get(position));
        objectIdText.setText(objectId.get(position));
        whereText.setText(where.get(position));
        dateText.setText(date.get(position));


        return customViewAdmin;
    }
}
