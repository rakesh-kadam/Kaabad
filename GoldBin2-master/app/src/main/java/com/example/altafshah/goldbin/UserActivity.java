package com.example.altafshah.goldbin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import java.util.ArrayList;

public class UserActivity extends AppCompatActivity implements RecyclerViewAdapter.ItemListener{
    RecyclerView recyclerView;
    ArrayList<DataModel> arrayList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_menu);

        Intent i = getIntent();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        arrayList = new ArrayList<>();
        arrayList.add(new DataModel("Recycle Scrap", R.drawable.example, "#5CED0E"));
        arrayList.add(new DataModel("Find Tree", R.drawable.find_tree, "#0EDFED"));
        arrayList.add(new DataModel("Mark Tree", R.drawable.point_tree, "#FF5733"));
        arrayList.add(new DataModel("Review Tree", R.drawable.health, "#1A0EE1"));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, arrayList, this);
        recyclerView.setAdapter(adapter);

        //AutoFitGridLayoutManager that auto fits the cells by the column width defined.
        AutoFitGridLayoutManager layoutManager = new AutoFitGridLayoutManager(this, 500);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onItemClick(DataModel item) {
        if (item.text.toString()=="Recycle Scrap") {
            Intent i = new Intent(UserActivity.this, CategoryBlock.class);
            startActivity(i);
        }
        else if (item.text.toString()=="Find Tree") {
            Intent i = new Intent(UserActivity.this, FindToilet.class);
            startActivity(i);
        }
        else if (item.text.toString()=="Mark Tree") {
            Intent i = new Intent(UserActivity.this, RentToilet.class);
            startActivity(i);
        }
        else if (item.text.toString()=="Review Tree") {
            Intent i = new Intent(UserActivity.this, ReviewTree.class);
            startActivity(i);
        }
        else if (item.text.toString()=="Logout") {
            Intent i = new Intent(UserActivity.this, MainActivity.class);
            startActivity(i);
        }
        Toast.makeText(getApplicationContext(), item.text + " ", Toast.LENGTH_SHORT).show();
    }
}
