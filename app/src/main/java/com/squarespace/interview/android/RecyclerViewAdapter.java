package com.squarespace.interview.android;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    //TAG string for debugging
    private static final String TAG = "RecyclerViewAdapter";

    //declare variables
    private ArrayList<String> colorList = new ArrayList<>();
    private ArrayList<String> nameList = new ArrayList<>();
    private ArrayList<String> sloganList = new ArrayList<>();
    private Context mContext;

    //default constructor for recycler view adapter
    public RecyclerViewAdapter(ArrayList<String> colorList, ArrayList<String> nameList, ArrayList<String> sloganList, Context mContext) {
        this.colorList = colorList;
        this.nameList = nameList;
        this.sloganList = sloganList;
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
        //log every time item is created
        Log.d(TAG, "onBindViewHolder: called");

        //viewHolder.colorView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        viewHolder.colorView.setCircleBackgroundColor(000000);

        viewHolder.nameText.setText(nameList.get(i));

        //onCLickListener
        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"clicked on " + nameList.get(i));
            }
        });

    }

    @Override
    public int getItemCount() {
        //tell adapter the number of list items in list
        return nameList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        //declare widget items
        CircleImageView colorView;
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
