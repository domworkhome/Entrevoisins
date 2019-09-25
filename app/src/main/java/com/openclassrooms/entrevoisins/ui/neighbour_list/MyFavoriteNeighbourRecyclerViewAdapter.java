package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * // Created by Stéphane TAILLET on 25/09/2019
 */
public class MyFavoriteNeighbourRecyclerViewAdapter extends RecyclerView.Adapter<MyFavoriteNeighbourRecyclerViewAdapter.ViewHolder> {

    private final List<Neighbour> mFavoriteList;
    private Activity mActivity;

    public MyFavoriteNeighbourRecyclerViewAdapter(List<Neighbour> items, Activity activity) {
        mFavoriteList = items;
        mActivity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_favorite, parent, false);

        return new MyFavoriteNeighbourRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Neighbour neighbour = mFavoriteList.get(position);
        holder.mNeighbourName.setText(neighbour.getName());
        Glide.with(holder.mNeighbourAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mNeighbourAvatar);

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, NeighbourDetail.class);
                intent.putExtra("neighbourName", neighbour.getName());
                intent.putExtra("neighbourAvatar", neighbour.getAvatarUrl());
                intent.putExtra("detailName", neighbour.getName());
                intent.putExtra("place", neighbour.getPlace());
                intent.putExtra("neighbourPhone", neighbour.getPhoneNumber());
                intent.putExtra("facebook", neighbour.getFacebook());
                intent.putExtra("neighbourAbout", neighbour.getAbout());
                holder.itemView.getContext().startActivity(intent);
            }
        });

        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteNeighbourEvent(neighbour));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFavoriteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.item_list_avatar)
        public ImageView mNeighbourAvatar;
        @BindView(R.id.item_list_name)
        public TextView mNeighbourName;
        @BindView(R.id.item_list_delete_button)
        public ImageButton mDeleteButton;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
