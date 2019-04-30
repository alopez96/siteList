package com.squarespace.interview.android;

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

        private String name, slogan, url, desc;

        //declare textView widgets
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

        Log.d(TAG,"name " + name);
        Log.d(TAG,"slogan: " + slogan);
        Log.d(TAG,"url: " + url);
        Log.d(TAG,"desc: " + desc);

        //attach variables to text widgets
        nameTV = findViewById(R.id.name_tv);
        sloganTV = findViewById(R.id.slogan_tv);
        urlTV = findViewById(R.id.desc_tv);
        descTV = findViewById(R.id.desc_tv);

        //set string values to their respective TextViews
        nameTV.setText(name);
        sloganTV.setText(slogan);
        urlTV.setText(url);
        descTV.setText(desc);


  }

  private void getInput(){
      name = getIntent().getStringExtra("name");
      slogan = getIntent().getStringExtra("slogan");
      url = getIntent().getStringExtra("url");
      desc = getIntent().getStringExtra("desc");
  }


}
