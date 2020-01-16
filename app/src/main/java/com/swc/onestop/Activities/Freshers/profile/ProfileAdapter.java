package com.swc.onestop.Activities.Freshers.profile;

import androidx.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import com.swc.onestop.Activities.Freshers.Details.DetailsData;
import com.swc.onestop.R;
import com.swc.onestop.databinding.ProfileItemBinding;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


class ProfileAdapter extends RecyclerView.Adapter<ProfileItem> {

    private final List<DetailsData> mData;

    public ProfileAdapter(final List<DetailsData> data) {
        super();
        mData = data;
    }

    @Override
    public ProfileItem onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final ProfileItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.profile_item, parent, false);
        return new ProfileItem(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(ProfileItem holder, int position) {
        holder.setContent(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

}
