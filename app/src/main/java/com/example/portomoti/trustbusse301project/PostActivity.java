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

public class PostActivity extends ArrayAdapter<String> {

    private final ArrayList<String> from;
    private final ArrayList<String> where;
    private final ArrayList<String> date; //String ---> DATE
    private final Activity context;

    public PostActivity(ArrayList<String> from, ArrayList<String> where, ArrayList<String> date, Activity context){
//String ----> Date
        super(context,R.layout.custom_view_list_trips,from);
        this.from=from;
        this.where=where;
        this.date=date;
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater=this.context.getLayoutInflater();
        View customView=layoutInflater.inflate(R.layout.custom_view_list_trips,null,true);
        TextView fromText=customView.findViewById(R.id.custom_view_from_text);
        TextView whereText=customView.findViewById(R.id.custom_view_where_text);
        TextView dateText=customView.findViewById(R.id.custom_view_date_text);

        fromText.setText(from.get(position));
        whereText.setText(where.get(position));
        dateText.setText(date.get(position));


        return customView;
    }
}
