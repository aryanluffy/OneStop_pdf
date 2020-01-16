package com.swc.onestop.EachFragmentMenu;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import androidx.core.content.ContextCompat;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


import com.swc.onestop.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class EmergencyMenuAdapter extends RecyclerView.Adapter<EmergencyMenuAdapter.MyViewHolder> {
    private List<EmergencyMenu> mDataset;
    private Context context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private AppCompatTextView name,phone;
        private CircleImageView im;
        private Button d;
        public MyViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.titleOfCon);
            phone = v.findViewById(R.id.phoneOfConcerned);
            im = v.findViewById(R.id.imageViewOfEmergency);
            d = v.findViewById(R.id.emergency_call_button);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public EmergencyMenuAdapter(List<EmergencyMenu> myDataset , Context context) {
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
                        .inflate(R.layout.emergency_card, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.name.setText(mDataset.get(position).getNameOfConcerned());
        //holder.name.setFont
        holder.phone.setText(mDataset.get(position).getPhoneOfConcerned());
        holder.im.setImageDrawable(mDataset.get(position).getD());
        holder.d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+mDataset.get(position).getPhoneOfConcerned()));

                if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(context,"Permission Not Granted",Toast.LENGTH_LONG).show();
                }
                else
                    context.startActivity(callIntent);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

