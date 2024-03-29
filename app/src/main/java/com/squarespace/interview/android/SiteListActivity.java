package com.squarespace.interview.android;

import android.content.Context;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.squarespace.interview.httpclient.SquarespaceClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SiteListActivity extends AppCompatActivity {

    //log for debugging
    private static final String TAG = "SiteListActivity";

    public static Context context;

    //variables
    private ArrayList<String> mColors = new ArrayList<>();
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mSlogans = new ArrayList<>();
    private ArrayList<String> mUrls = new ArrayList<>();
    private ArrayList<String> mDescs = new ArrayList<>();

    //declare squareSpaceClient instance
    private SquarespaceClient myClient = new SquarespaceClient(this);

      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_site_list);

          //create new thread instance for requesting data
          Thread thread = new Thread(new Runnable() {
              @Override
              public void run() {
                  getData();
              }
          });

          //start thread to get data
          thread.start();

          try {
              //wait for thread to finish getting data before continuing
              thread.join(0);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }

          //continue after thread has completed
          //load recycler view
          initRecyclerView();

      }


      //this function makes a request to the squareSpaceClient class to get list of sites
      //and it adds the information to the arrays that are used to display the cells in the
      //recyclerView (eg. name, color, slogan)
      private void getData(){
          try  {
              //store result in string variable
              String result = myClient.requestSites();
              //create JSON object from result
              final JSONObject obj = new JSONObject(result);
              //create JSON array from sites array
              final JSONArray sitesData = obj.getJSONArray("sites");

              //sort objects by name
              JSONArray sortedJsonArray = sortArray(sitesData);

              //loop through each site in the sites array
              for (int i = 0; i < sitesData.length(); ++i) {
                  //create an object for each site
                  final JSONObject site = sortedJsonArray.getJSONObject(i);

                  //store the color, name, and slogan (of each site) in their respective array
                  mColors.add(site.getString("color"));
                  mNames.add(site.getString("name"));
                  mSlogans.add(site.getString("slogan"));

                  //getting url and description here to save time by avoiding to fetch later
                  mUrls.add(site.getString("url"));
                  mDescs.add(site.getString("description"));
              }

          } catch (Exception e) {
              //catch any error
              e.printStackTrace();
          }

      }

      //this function sorts an JSONArray by name
      //input: JSONArray
      //output: JSONArray
      private JSONArray sortArray(JSONArray sitesData){
          //start sort array (retrieved from stack overflow source) --------
          JSONArray sortedJsonArray = new JSONArray();
          List<JSONObject> jsonValues = new ArrayList<JSONObject>();
          for (int i = 0; i < sitesData.length(); i++) {
              try {
                  jsonValues.add(sitesData.getJSONObject(i));
              } catch (JSONException e) {
                  e.printStackTrace();
              }
          }
          Collections.sort( jsonValues, new Comparator<JSONObject>() {
              private static final String KEY_NAME = "name";
              @Override
              public int compare(JSONObject a, JSONObject b) {
                  String valA = new String();
                  String valB = new String();
                  try {
                      valA = (String) a.get(KEY_NAME);
                      valB = (String) b.get(KEY_NAME);
                  }
                  catch (JSONException e) { }
                  return valA.compareTo(valB);
              }
          });

          for (int i = 0; i < sitesData.length(); i++) {
              sortedJsonArray.put(jsonValues.get(i));
          }

          //end sort array --------------------------------
          return sortedJsonArray;
      }

      //this function initializes the RecyclerViewAdapter
      private void initRecyclerView(){
          RecyclerView recyclerView =  findViewById(R.id.my_recycler_view);
          RecyclerViewAdapter adapter = new RecyclerViewAdapter(mColors, mNames, mSlogans, mUrls, mDescs, this);
          recyclerView.setAdapter(adapter);
          recyclerView.setLayoutManager(new LinearLayoutManager(this));
      }


}


