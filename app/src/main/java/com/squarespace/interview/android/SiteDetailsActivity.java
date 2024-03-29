package com.squarespace.interview.android;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squarespace.interview.httpclient.SquarespaceClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SiteDetailsActivity extends AppCompatActivity {

        //log variable for debugging
        private static final String TAG = "SiteDetailsActivity";

        //declare string variables
        private String color, name, slogan, url, desc;

        //declare textView widgets
        private ImageView colorView;
        private TextView nameTV;
        private TextView sloganTV;
        private TextView urlTV;
        private TextView descTV;

    @Override
  protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_details);

        //call get input
        getInput();

        //attach variables to text widgets
        colorView = findViewById(R.id.color_view);
        nameTV = findViewById(R.id.name_tv);
        sloganTV = findViewById(R.id.slogan_tv);
        urlTV = findViewById(R.id.url_tv);
        descTV = findViewById(R.id.desc_tv);

        //set background to thumbnail
        colorView.setBackgroundColor(Color.parseColor(color));
        //set string values to their respective TextViews
        nameTV.setText(name);
        sloganTV.setText("Slogan: " + slogan);
        urlTV.setText("URL: " + url);
        descTV.setText("Description: " + desc);


  }

  //this function gets the variables passed in from the RecyclerViewAdapter
  //eg(color, name, slogan, url, desc)
  private void getInput(){
        //get string values from RecyclerViewAdapter
        color = getIntent().getStringExtra("color");
        name = getIntent().getStringExtra("name");
        slogan = getIntent().getStringExtra("slogan");
        url = getIntent().getStringExtra("url");
        desc = getIntent().getStringExtra("desc");
  }


}
