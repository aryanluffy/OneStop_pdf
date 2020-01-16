package com.swc.onestop.EachFragmentMenu;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.swc.onestop.R;
import com.swc.onestop.ui.AllDetailActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FragmentMenuAdapter extends RecyclerView.Adapter<FragmentMenuAdapter.MyViewHolder> {
    private  List<FragmentMenu> mDataset;
    private Context context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public  class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private AppCompatTextView name,status;
        private CircleImageView d;
        public MyViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.titleOfCon);
            status = v.findViewById(R.id.statusOfConcerned);
            d = v.findViewById(R.id.imageViewOfConcerned);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int p = getAdapterPosition();
                    if(p!=RecyclerView.NO_POSITION){
                        Intent i = new Intent(context, AllDetailActivity.class);
                        i.putExtra("title",name.getText().toString());
                        i.putExtra("status",status.getText().toString());
                        i.putExtra("Image",R.id.imageViewOfConcerned);
                        i.putExtra("position",p);
                        i.putExtra("whichFrag",mDataset.get(p).getTypeOfData());
                        context.startActivity(i);
                    }
                }
            });

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public FragmentMenuAdapter(List<FragmentMenu> myDataset , Context context) {
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
                        .inflate(R.layout.detail_contact_card, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.name.setText(mDataset.get(position).getNameOfConcerned());
        holder.status.setText(mDataset.get(position).getStatusOfConcerned());
        holder.d.setImageDrawable(mDataset.get(position).getDraw());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }



}

