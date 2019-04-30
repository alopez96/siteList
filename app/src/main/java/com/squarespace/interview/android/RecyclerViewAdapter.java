package com.squarespace.interview.android;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    //TAG string for debugging
    private static final String TAG = "RecyclerViewAdapter";

    //declare variables
    private ArrayList<String> colorList = new ArrayList<>();
    private ArrayList<String> nameList = new ArrayList<>();
    private ArrayList<String> sloganList = new ArrayList<>();
    private ArrayList<String> urlList = new ArrayList<>();
    private ArrayList<String> descList = new ArrayList<>();
    private Context mContext;

    //default constructor for recycler view adapter
    public RecyclerViewAdapter(ArrayList<String> colorList, ArrayList<String> nameList,
                               ArrayList<String> sloganList, ArrayList<String> urlList, ArrayList<String> descList,
                               Context mContext)
    {
        this.colorList = colorList;
        this.nameList = nameList;
        this.sloganList = sloganList;
        this.urlList = urlList;
        this.descList = descList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //this function inflates the view
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listitem, viewGroup, false);
        //create viewHolder instance
        ViewHolder holder = new ViewHolder(view);
        //return viewHolder
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder,final int i) {

        //save color string with #
        final String color = "#"+colorList.get(i);

        //set color, name, and slogan to their field
        viewHolder.colorView.setBackgroundColor(Color.parseColor(color));
        viewHolder.nameText.setText(nameList.get(i));
        viewHolder.sloganText.setText(sloganList.get(i));

        //handle onCLickListener for cell
        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"clicked on " + nameList.get(i));

                //create new activity
                Intent intent = new Intent(mContext, SiteDetailsActivity.class);

                //pass in the variables we have already retrieved
                intent.putExtra("color", color);
                intent.putExtra("name", nameList.get(i));
                intent.putExtra("slogan", sloganList.get(i));
                intent.putExtra("url", urlList.get(i));
                intent.putExtra("desc", descList.get(i));

                //start new activity
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        //tell adapter the number of list items
        return nameList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        //declare widget items
        ImageView colorView;
        TextView nameText;
        TextView sloganText;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //attach widgets to ids
            colorView = itemView.findViewById(R.id.color_view);
            nameText = itemView.findViewById(R.id.name_tv);
            sloganText = itemView.findViewById(R.id.slogan_tv);
            parentLayout = itemView.findViewById(R.id.parent_layout);

        }
    }
}
