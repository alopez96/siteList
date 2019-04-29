package com.squarespace.interview.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class SiteListActivity extends AppCompatActivity {

    //log for debugging
    private static final String TAG = "SiteListActivity";

    //variables
    private ArrayList<String> mNames = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_site_list);
    Log.d(TAG, "started");


  }

  
}


