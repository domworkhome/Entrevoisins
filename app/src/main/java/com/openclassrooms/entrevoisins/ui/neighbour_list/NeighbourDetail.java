package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NeighbourDetail extends AppCompatActivity{

    @BindView(R.id.toolbarDetails)
    Toolbar toolbar;
    @BindView(R.id.neighbourPic)
    ImageView mNeighbourPic;
    @BindView(R.id.neighbourName)
    TextView mneighbourName;
    @BindView(R.id.neighbourDetailName)
    TextView mneighbourDetailName;
    @BindView(R.id.neighbourPlace)
    TextView mneighbourPlace;
    @BindView(R.id.neighbourPhone)
    TextView mneighbourPhone;
    @BindView(R.id.neighbourFacebook)
    TextView mneighbourFacebook;
    @BindView(R.id.Aboutme)
    TextView mneighbourAbout;
    @BindView(R.id.favoritefab)
    FloatingActionButton fab;
    @BindView(R.id.activity_details)
    CoordinatorLayout mCoordinatorLayout;

    Neighbour mNeighbour;
    NeighbourApiService mNeighbourApiService;
    Intent mIntent;
    Integer neighbourId;
    String neighbourName;
    String neighbourDetailName;
    String neighbourPlace;
    String neighbourPhone;
    String neighbourFacebook;
    String neighbourAbout;

    private static final String TAG = "NeighbourDetail";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_detail);

        ButterKnife.bind(this);

        //Add toolbar and return arrow
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mNeighbourApiService = DI.getNeighbourApiService();
        mIntent = getIntent();
        mNeighbour = new Neighbour(mIntent.getIntExtra("neighbourId", 0), mIntent.getStringExtra("neighbourName"),
                mIntent.getStringExtra("neighbourAvatar"), mIntent.getStringExtra("place"),
                mIntent.getStringExtra("neighbourPhone"), mIntent.getStringExtra("facebook"), mIntent.getStringExtra("neighbourAbout"));

                getIncomingIntent();

        fab.setOnClickListener(view -> {

            if (!mNeighbourApiService.getFavorites().contains(mNeighbour)) {
                mNeighbourApiService.addNeighbourToFavorite(mNeighbour);
                fab.setImageResource(R.drawable.ic_star_yellorange_24dp);
                System.out.println(" has been added to favorite list");
                Snackbar.make(mCoordinatorLayout, mNeighbour.getName() + " has been added to favorite list", Snackbar.LENGTH_SHORT).show();
            } else {
                fab.setImageResource(R.drawable.ic_star_border_yellorange_24dp);
                mNeighbourApiService.deleteNeighbourFromFavorite(mNeighbour);
                Snackbar.make(mCoordinatorLayout, mNeighbour.getName() + " is already in your favorite list", Snackbar.LENGTH_SHORT).show();
            }
        });

    }

    private void getIncomingIntent(){

        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");

        if(mIntent.hasExtra("neighbourName") && mIntent.hasExtra("neighbourAvatar") && mIntent.hasExtra("detailName") && mIntent.hasExtra("place") && mIntent.hasExtra("neighbourPhone") && mIntent.hasExtra("facebook") && mIntent.hasExtra("neighbourAbout")){
            Log.d(TAG, "getIncomingIntent: found intent extras.");

            neighbourId = mIntent.getIntExtra("neighbourId", 0);

            neighbourName = mIntent.getStringExtra("neighbourName");

            String neighbourPic = mIntent.getStringExtra("neighbourAvatar");

            neighbourDetailName = mIntent.getStringExtra("detailName");

            neighbourPlace = mIntent.getStringExtra("place");

            neighbourPhone = mIntent.getStringExtra("neighbourPhone");

            neighbourFacebook = mIntent.getStringExtra("facebook");

            neighbourAbout = mIntent.getStringExtra("neighbourAbout");

            setNeighbourDetail(neighbourName, neighbourPic, neighbourDetailName, neighbourPlace, neighbourPhone, neighbourFacebook, neighbourAbout);
        }

    }

    private void setNeighbourDetail(String neighbourName, String neighbourPic, String neighbourDetailName, String neighbourPlace, String neighbourPhone, String neighbourFacebook, String neighbourAbout){
        Log.d(TAG, "setNeighbourDetail: setting the neighbour infos to widget.");

            mneighbourName.setText(neighbourName);

            Glide.with(mNeighbourPic.getContext())
                    .load(neighbourPic)
                    .into(mNeighbourPic);

            mneighbourDetailName.setText(neighbourDetailName);

            mneighbourPlace.setText(neighbourPlace);

            mneighbourPhone.setText(neighbourPhone);

            mneighbourFacebook.setText("www.facebook.fr/" + neighbourFacebook);

            mneighbourAbout.setText(neighbourAbout);
            mneighbourAbout.setMovementMethod(new ScrollingMovementMethod());
    }
}