package com.swc.onestop.MainActivity_Models;

import android.content.Context;
import android.content.DialogInterface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import com.squareup.picasso.Picasso;
import com.swc.onestop.Activities.SessionManager;
import com.swc.onestop.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class CustomAdapter_fav extends RecyclerView.Adapter<CustomAdapter_fav.MyViewHolder> {

    private ArrayList<Data_model> dataSet;
    private Context context;
    static FirebaseFirestore db;
    SessionManager sessionManager;
    private CustomAdapter_fav adapter;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewtitle;
        TextView textViewSubtitle;
        TextView textViewinfo;
        ImageView imageViewdesc;
        CircleImageView imageViewdp;
        ImageButton del;
        TextView postLink;
        View mView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
            this.imageViewdp = (CircleImageView) itemView.findViewById(R.id.dp);
            this.textViewtitle = (TextView) itemView.findViewById(R.id.title_name);
            this.textViewSubtitle = (TextView) itemView.findViewById(R.id.subtitle);
            this.textViewinfo = (TextView) itemView.findViewById(R.id.info);
            this.imageViewdesc = (ImageView)itemView.findViewById(R.id.image_desc);
            this.del = (ImageButton) itemView.findViewById(R.id.dp2);

            this.postLink=itemView.findViewById(R.id.linkId);
            Log.d("CustomAdapter","FindviewbyId Done");


        }
    }

    public CustomAdapter_fav(ArrayList<Data_model> data, Context context) {
        this.dataSet = data;
        this.context = context;
        this.adapter=this;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout, parent, false);

        db = FirebaseFirestore.getInstance();
        sessionManager = new SessionManager(parent.getContext());

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {


        TextView textViewtitle = holder.textViewtitle;
        TextView textViewSubtitle = holder.textViewSubtitle;
        TextView textViewinfo = holder.textViewinfo;
        final ImageView imageViewdesc = holder.imageViewdesc;
        final ImageView imageViewdp= holder.imageViewdp;
        TextView postLink=holder.postLink;
        ImageView del=holder.del;
        del.setBackground(context.getResources().getDrawable(R.drawable.ic_close_state));


        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClick);

                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Are you sure you want unsave?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                db.collection("feed").document(dataSet.get(listPosition).getID()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                        if (task.isSuccessful()) {
                                            DocumentSnapshot document = task.getResult();
                                            if (document.exists()) {

                                                db.collection("users").document(sessionManager.getUserDetails().get("id"))
                                                        .collection("fav").document(dataSet.get(listPosition).getID()).delete();
                                                dataSet.remove(listPosition);
                                                adapter.notifyDataSetChanged();

                                                Toast.makeText(holder.del.getContext(), "Removed Post", Toast.LENGTH_SHORT).show();
                                            } else {
//                                Log.d(TAG, "No such document");
                                            }
                                        } else {
//                            Log.d(TAG, "get failed with ", task.getException());
                                        }

                                    }
                                });
                                notifyDataSetChanged();


                                dialog.cancel();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();



            }
        });

        textViewtitle.setText(dataSet.get(listPosition).getTitle());
        textViewSubtitle.setText(dataSet.get(listPosition).getSubtitle());
        textViewinfo.setText(dataSet.get(listPosition).getDesc());

        final String dpurl = dataSet.get(listPosition).getdp();
        final String imageurl = dataSet.get(listPosition).getImage();

        if(dataSet.get(listPosition).getLink().isEmpty()){
            holder.mView.findViewById(R.id.linearLayoutLinkId).setVisibility(View.GONE);//****testing
        }
        if(!dataSet.get(listPosition).getLink().isEmpty())postLink.setText(dataSet.get(listPosition).getLink());//****testing
        Log.d("url", String.valueOf(dpurl.isEmpty()));

       if(!dpurl.isEmpty())
       {

           Picasso.get().load(dpurl).networkPolicy(NetworkPolicy.OFFLINE).placeholder(R.drawable.user).into(imageViewdp, new Callback() {
                                    @Override
                                    public void onSuccess() {
                                    }
                                    @Override
                                    public void onError(Exception e) {
                                        Picasso.get().load(dpurl).placeholder(R.drawable.user).into(imageViewdp);
                                    }
                                });
       }
       if(!imageurl.isEmpty())
       {

             
           Picasso.get().load(imageurl).networkPolicy(NetworkPolicy.OFFLINE).placeholder(R.drawable.user).into(imageViewdesc, new Callback() {
                                    @Override
                                    public void onSuccess() {
                                    }
                                    @Override
                                    public void onError(Exception e) {
                                        Picasso.get().load(imageurl).placeholder(R.drawable.user).into(imageViewdesc);
                                    }
                                });
       }


    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}