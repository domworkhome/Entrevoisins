package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
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

    Neighbour neighbour;
    NeighbourApiService mNeighbourApiService;
    Intent mIntent;
    Integer neighbourId;
    String neighbourName;
    String neighbourPic;
    String neighbourDetailName;
    String neighbourPlace;
    String neighbourPhone;
    String neighbourFacebook;
    String neighbourAbout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_detail);

        ButterKnife.bind(this);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mNeighbourApiService = DI.getNeighbourApiService();

        mIntent = getIntent();

        // get parcelable data from Neighbour model
        neighbour = mIntent.getParcelableExtra("neighbour");

                neighbourId = neighbour.getId();

                neighbourName = neighbour.getName();

                neighbourPic = neighbour.getAvatarUrl();

                neighbourDetailName = neighbour.getName();

                neighbourPlace = neighbour.getPlace();

                neighbourPhone = neighbour.getPhoneNumber();

                neighbourFacebook = neighbour.getFacebook();

                neighbourAbout = neighbour.getAbout();

                setNeighbourDetail(neighbourName, neighbourPic, neighbourDetailName, neighbourPlace, neighbourPhone, neighbourFacebook, neighbourAbout);

        // Set the favorite FAB on/off on click and add/remove neighbour from favorites + snackbar message to inform user of which action is done by click
        if (!mNeighbourApiService.getFavorites().contains(neighbour)) {
            fab.setImageResource(R.drawable.ic_star_border_yellorange_24dp);
        } else {
            fab.setImageResource(R.drawable.ic_star_yellorange_24dp);
        }

        fab.setOnClickListener(view -> {

            if (!mNeighbourApiService.getFavorites().contains(neighbour)) {
                mNeighbourApiService.addNeighbourToFavorite(neighbour);
                fab.setImageResource(R.drawable.ic_star_yellorange_24dp);
                System.out.println(neighbour.getName() + " has been added to favorite list");
                Snackbar.make(mCoordinatorLayout, getString(R.string.added_to_favorite_list, this.neighbour.getName()), Snackbar.LENGTH_SHORT).show();
            } else {
                fab.setImageResource(R.drawable.ic_star_border_yellorange_24dp);
                mNeighbourApiService.deleteNeighbourFromFavorite(neighbour);
                Snackbar.make(mCoordinatorLayout, getString(R.string.removed_from_favorite_list, this.neighbour.getName()), Snackbar.LENGTH_SHORT).show();
            }
        });

    }

    // Define each neighbour detail's layout component's content
    private void setNeighbourDetail(String neighbourName, String neighbourPic, String neighbourDetailName, String neighbourPlace, String neighbourPhone, String neighbourFacebook, String neighbourAbout){

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