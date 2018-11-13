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

        /*TextView show = (TextView) findViewById(R.id.textView);
        show.setText(username);*/

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        arrayList = new ArrayList<>();
        arrayList.add(new DataModel("Sell Scrap", R.drawable.example, "#09A9FF"));
        arrayList.add(new DataModel("Locate Tree", R.drawable.point_tree, "#3E51B1"));
        arrayList.add(new DataModel("Find Tree", R.drawable.find_tree, "#673BB7"));
        arrayList.add(new DataModel("Logout", R.drawable.logout, "#EF0AD7"));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, arrayList, this);
        recyclerView.setAdapter(adapter);

        //AutoFitGridLayoutManager that auto fits the cells by the column width defined.

        AutoFitGridLayoutManager layoutManager = new AutoFitGridLayoutManager(this, 500);
        recyclerView.setLayoutManager(layoutManager);

        //Simple GridLayoutManager that spans two columns

        /*GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);*/
    }

    @Override
    public void onItemClick(DataModel item) {
        if (item.text.toString()=="Sell Scrap") {

            Intent i = new Intent(UserActivity.this, ScrapList.class);
            startActivity(i);
        }

        else if (item.text.toString()=="Point Tree") {

        Intent i = new Intent(UserActivity.this, FindToilet.class);
        startActivity(i);

        }
        else if (item.text.toString()=="Find Tree") {

            Intent i = new Intent(UserActivity.this, RentToilet.class);
            startActivity(i);
        }
        else if (item.text.toString()=="Logout") {

            Intent i = new Intent(UserActivity.this, MainActivity.class);
            startActivity(i);
        }

        Toast.makeText(getApplicationContext(), item.text + " ", Toast.LENGTH_SHORT).show();
    }
}
