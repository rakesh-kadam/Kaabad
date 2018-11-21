package com.example.altafshah.goldbin;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

public class CategoryBlock extends AppCompatActivity implements RecyclerViewAdapter.ItemListener{
    RecyclerView recyclerView;
    ArrayList<DataModel> arrayList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_block);

        Intent i = getIntent();

        /*TextView show = (TextView) findViewById(R.id.textView);
        show.setText(username);*/

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        arrayList = new ArrayList<>();
        arrayList.add(new DataModel("Paper", R.drawable.paper, "#0E2FD8"));
        arrayList.add(new DataModel("Plastic", R.drawable.plastic, "#755BA3"));
        arrayList.add(new DataModel("Metals", R.drawable.metals, "#DF18BB"));
        arrayList.add(new DataModel("E-Waste", R.drawable.ewaste, "#E8640E"));
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
        if (item.text.toString()=="Paper") {
            Intent i = new Intent(CategoryBlock.this, ScrapList_Paper.class);
            startActivity(i);
        }

        else if (item.text.toString()=="Plastic") {

            Intent i = new Intent(CategoryBlock.this, ScrapList_Plastic.class);
            startActivity(i);

        }
        else if (item.text.toString()=="Metals") {

            Intent i = new Intent(CategoryBlock.this, ScrapList_Metal.class);
            startActivity(i);
        }
        else if (item.text.toString()=="E-Waste") {

            Intent i = new Intent(CategoryBlock.this, ScrapList_EWaste.class);
            startActivity(i);
        }

        Toast.makeText(getApplicationContext(), item.text + " ", Toast.LENGTH_SHORT).show();
    }
}
