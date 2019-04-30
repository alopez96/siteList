package com.squarespace.interview.android;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.squarespace.interview.httpclient.SquarespaceClient;

import java.io.IOException;
import java.util.ArrayList;

public class SiteListActivity extends AppCompatActivity {

    //log for debugging
    private static final String TAG = "SiteListActivity";

    public static Context context;

    //variables
    private ArrayList<String> mColors = new ArrayList<>();
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mSlogans = new ArrayList<>();

      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_site_list);
          Log.d(TAG, "started");

          initList();

          //context = getApplicationContext();
          //context = this;
          context = getBaseContext();

          SquarespaceClient myClient = new SquarespaceClient(context);

          try {
              Log.d(TAG,"fetching data from squarespace");
              myClient.requestSites();
          } catch (IOException e) {
              Log.d(TAG,"Error fetching data from squarespace");
              e.printStackTrace();
          }


      }


      private void initList(){
          Log.d(TAG, "init list items");

          mColors.add("2000");
          mNames.add("Jose");
          mSlogans.add("J's slogan");

          mColors.add("0000");
          mNames.add("JS");
          mSlogans.add("JSs slogan");

          initRecyclerView();
      }

      private void initRecyclerView(){

          RecyclerView recyclerView =  findViewById(R.id.my_recycler_view);
          RecyclerViewAdapter adapter = new RecyclerViewAdapter(mColors, mNames, mSlogans, this);
          recyclerView.setAdapter(adapter);
          recyclerView.setLayoutManager(new LinearLayoutManager(this));
      }


}


