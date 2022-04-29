package com.android.login;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import Adapter.RecyclerViewAdapter;
import model.TourModel;

public class PlacesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<TourModel> tourPlaces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);
        recyclerView=findViewById(R.id.rvAnimation);
        tourPlaces =new ArrayList<>();
        addPlaces();

    }
    private void addPlaces(){
        tourPlaces.add(new TourModel("Mumbai",R.drawable.profile_picture));
        tourPlaces.add(new TourModel("Surat",R.drawable.profile_picture));
        tourPlaces.add(new TourModel("Kashmir",R.drawable.profile_picture));
        tourPlaces.add(new TourModel("Pune",R.drawable.profile_picture));
        tourPlaces.add(new TourModel("Goa",R.drawable.profile_picture));
        tourPlaces.add(new TourModel("Kerela",R.drawable.profile_picture));
        RecyclerViewAdapter adapter=new RecyclerViewAdapter(tourPlaces,this);
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


}

