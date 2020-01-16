package com.swc.onestop.Activities.Freshers.profile;


import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.swc.onestop.Activities.Freshers.Details.DetailsData;
import com.swc.onestop.R;

import androidx.recyclerview.widget.RecyclerView;

public class ProfileItem extends RecyclerView.ViewHolder {

    public ProfileItem(View itemView) {
        super(itemView);
    }

    void setContent(DetailsData data) {
        ((TextView)itemView.findViewById(R.id.tv_title)).setText(data.title);
        ((TextView)itemView.findViewById(R.id.tv_text)).setText(data.text);

        if(data.freshersPostLink.equals("")){

            LinearLayout linearLayoutLink = itemView.findViewById(R.id.linearLayoutLinkId);
            linearLayoutLink.setVisibility(View.GONE);

        }
        else ((TextView)itemView.findViewById(R.id.freshersLinkId)).setText(data.freshersPostLink);


    }

}
