package com.example.altafshah.goldbin;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity implements RecyclerViewAdapter.ItemListener{
    RecyclerView recyclerView;
    ArrayList<DataModel> arrayList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_menu);

        Intent i = getIntent();
        String username = i.getStringExtra("username");

        /*TextView show = (TextView) findViewById(R.id.textView);
        show.setText(username);*/



        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        arrayList = new ArrayList<>();
        arrayList.add(new DataModel("Scrap", R.drawable.example, "#09A9FF"));
        arrayList.add(new DataModel("Rent Toilet", R.drawable.rent_toilet, "#3E51B1"));
        arrayList.add(new DataModel("Find Toilet", R.drawable.find_toilet, "#673BB7"));
        arrayList.add(new DataModel("Support", R.drawable.support, "#4BAA50"));

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, arrayList, this);
        recyclerView.setAdapter(adapter);


        /**
         AutoFitGridLayoutManager that auto fits the cells by the column width defined.
         **/

        AutoFitGridLayoutManager layoutManager = new AutoFitGridLayoutManager(this, 500);
        recyclerView.setLayoutManager(layoutManager);


        /**
         Simple GridLayoutManager that spans two columns
         **/
        /*GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);*/
    }

    @Override
    public void onItemClick(DataModel item) {
        if (item.text.toString()=="Scrap") {

            Intent i = new Intent(UserActivity.this, ScrapList.class);
            startActivity(i);
        }

        else if (item.text.toString()=="Find Toilet") {

        Intent i = new Intent(UserActivity.this, FindToilet.class);
        startActivity(i);

        }
        else if (item.text.toString()=="Rent Toilet") {

            Intent i = new Intent(UserActivity.this, RentToilet.class);
            startActivity(i);
        }

        Toast.makeText(getApplicationContext(), item.text + " is clicked", Toast.LENGTH_SHORT).show();

    }





}
