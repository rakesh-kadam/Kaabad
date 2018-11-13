package com.example.altafshah.goldbin;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class OrderConfirmed extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_confirmed);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_summary);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Summary");

        TableLayout table = (TableLayout) findViewById(R.id.order_summary);
        TableRow head=new TableRow(this);
        head.setBackgroundColor(getResources().getColor(R.color.pink));
        head.setPadding(8,20,8,20);

        TextView tvDebt=new TextView(this);
        TableRow.LayoutParams params = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
        tvDebt.setLayoutParams(params);
        tvDebt.setTextColor(getResources().getColor(R.color.white));
        tvDebt.setText(""+"Item");
        tvDebt.setTextSize(19);
        tvDebt.setGravity(Gravity.CENTER);
        tvDebt.setTypeface(tvDebt.getTypeface(), Typeface.BOLD);


        TextView tvFee=new TextView(this);
        //tvFee.setLayoutParams(new TableLayout.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));
        tvFee.setLayoutParams(params);
        tvFee.setTextColor(getResources().getColor(R.color.white));
        tvFee.setText(""+"Quantity");
        tvFee.setTextSize(19);
        tvFee.setGravity(Gravity.CENTER);
        tvFee.setTypeface(tvDebt.getTypeface(), Typeface.BOLD);


        TextView tvQuan=new TextView(this);
        tvQuan.setLayoutParams(params);
        //tvQuan.setLayoutParams(new TableLayout.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));
        tvQuan.setTextColor(getResources().getColor(R.color.white));
        tvQuan.setText(""+"Price");
        tvQuan.setTextSize(19);
        tvQuan.setGravity(Gravity.CENTER);
        tvQuan.setTypeface(tvDebt.getTypeface(), Typeface.BOLD);

        head.addView(tvDebt);
        head.addView(tvFee);
        head.addView(tvQuan);
        table.addView(head);


        for(int i=0;i<ScrapList.scrapListAddedToCard.size();i++)
        {
            TableRow row=new TableRow(this);
            row.setBackgroundColor(getResources().getColor(R.color.white));
            row.setPadding(8,8,8,8);
            TableRow.LayoutParams params1 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);

            String title = ScrapList.scrapListAddedToCard.get(i).getTitle();
            String quantity = ScrapList.scrapListAddedToCard.get(i).getQuantity();
            String price = ScrapList.scrapListAddedToCard.get(i).getPricePU();
            String measure = ScrapList.scrapListAddedToCard.get(i).getMeasure();


            TextView tvItem=new TextView(this);
            tvItem.setLayoutParams(params1);
            tvItem.setTextSize(16);
            tvItem.setText(""+title);



            TextView tvQuantity=new TextView(this);
            tvQuantity.setLayoutParams(params1);
            tvQuantity.setTextSize(16);
            tvQuantity.setGravity(Gravity.CENTER);
            tvQuantity.setText(""+quantity);

            TextView tvPrice=new TextView(this);
            tvPrice.setLayoutParams(params1);
            tvPrice.setTextSize(16);
            tvPrice.setGravity(Gravity.CENTER);
            tvPrice.setText(""+price+" "+measure);

            row.addView(tvItem);
            row.addView(tvQuantity);
            row.addView(tvPrice);
            table.addView(row);
            View v = new View(this);
            v.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1));
            v.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            table.addView(v);
        }

        ScrapList.scrapListAddedToCard.clear();

        Button btnHome = (Button)findViewById(R.id.btn_goHome);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OrderConfirmed.this, UserActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
