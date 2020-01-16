package com.swc.onestop.SmallRecyclers;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import androidx.core.content.ContextCompat;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.swc.onestop.R;

import java.util.List;

public class OtherAndCallClassAdapter extends RecyclerView.Adapter<OtherAndCallClassAdapter.MyViewHolder> {
    private  List<OtherAndCallClass> mDataset;
    private Context context;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public  class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private AppCompatTextView title,subhead;
        private AppCompatButton other,call;
        public MyViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.titleOfCon);
            subhead = v.findViewById(R.id.subheadOfCon);
            other = v.findViewById(R.id.other_button);
            call = v.findViewById(R.id.callToCon);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public OtherAndCallClassAdapter(List<OtherAndCallClass> myDataset , Context context) {
        mDataset = myDataset;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        // create a new vie
        View v =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.other_and_call, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.title.setText(mDataset.get(position).getTitle());
        holder.subhead.setText(mDataset.get(position).getSubhead());
        //holder.d.setImageDrawable(mDataset.get(position).getD());

        holder.other.setText((mDataset.get(position).getTypeOfData() == 2) ? "WEB":"MAIL");

        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+mDataset.get(position).getPhoneNumber()));

                if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(context,"Permission Not Granted",Toast.LENGTH_LONG).show();
                }
                else
                    callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(callIntent);
            }
        });

        if(mDataset.get(position).getTypeOfData() == 1){
            holder.other.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //access website
                    String email = mDataset.get(position).getOtherData();
                    Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
                    intent.setType("text/plain");

                    intent.setData(Uri.parse("mailto:"+email)); // or just "mailto:" for blank
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
                    context.startActivity(intent);
                    }
                }
            );
        }
        else{
            holder.other.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //access mail
                    final Intent intent = new Intent(Intent.ACTION_VIEW)
                            .setType("plain/text")
                            .setData(Uri.parse(mDataset.get(position).getOtherData()))
                            .setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
                }
            });
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
