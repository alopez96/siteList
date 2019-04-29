package com.squarespace.interview.android;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    //TAG string for debugging
    private static final String TAG = "RecyclerViewAdapter";

    //declare variables
    private ArrayList<String> mNames = new ArrayList<>();
    private Context mContext;

    //default constructor for recycler view adapter
    public RecyclerViewAdapter(ArrayList<String> mNames, Context mContext) {
        this.mNames = mNames;
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

        viewHolder.textName.setText(mNames.get(i));

        //onCLickListener
        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"clicked on " + mNames.get(i));
            }
        });

    }

    @Override
    public int getItemCount() {
        //tell adapter the number of list items in list
        return mNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        //declare widget items
        TextView textName;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //attach widgets to their ids
            textName = itemView.findViewById(R.id.name);
            parentLayout = itemView.findViewById(R.id.parent_layout);

        }
    }
}
