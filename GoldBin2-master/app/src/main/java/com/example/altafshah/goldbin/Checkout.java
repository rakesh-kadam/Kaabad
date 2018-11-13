package com.example.altafshah.goldbin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Checkout extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ScrapAdapter mAdapter;
    private String OrdersToSend;
    Button checkOut;
    ProgressDialog progressDialog;
    private static final String TAG = "Checkout";
    private static final String URL_FOR_REGISTRATION = "http://www.vodafoneindiaservices.com/Goldbin/checkout.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_in_cart);

        // Progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_checkout);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_checkout);

        mAdapter = new ScrapAdapter(ScrapList.scrapListAddedToCard);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();

        Gson gson = new GsonBuilder().create();
        JsonArray myCustomArray = gson.toJsonTree(ScrapList.scrapListAddedToCard).getAsJsonArray();

        JSONArray OrderList = new JSONArray();
        for (int i = 0; i < ScrapList.scrapListAddedToCard.size(); i++) {
            String title = ScrapList.scrapListAddedToCard.get(i).getTitle();
            String price = ScrapList.scrapListAddedToCard.get(i).getPricePU();
            String measure = ScrapList.scrapListAddedToCard.get(i).getMeasure();
            String quantity = ScrapList.scrapListAddedToCard.get(i).getQuantity();
            JSONObject Order = new JSONObject();
            try {
                Order.put("name", title);
                Order.put("price", price);
                Order.put("measure", measure);
                Order.put("quantity", quantity);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            OrderList.put(Order);

        }
        OrdersToSend = OrderList.toString();

        System.out.print(OrdersToSend);

        Toast.makeText(getApplicationContext(), "" + OrdersToSend, Toast.LENGTH_LONG).show();

        checkOut = (Button)findViewById(R.id.checkout_btn);
        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkout();
            }
        });
    }

    private void checkout() {
        sendOrder(OrdersToSend);
    }


    private void sendOrder(final String OrdersToSend) {
        // Tag used to cancel the request
        String cancel_req_tag = "checkout";

        progressDialog.setMessage("Placing Order ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                URL_FOR_REGISTRATION, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Checkout Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    if (!error) {
                        String order_id = jObj.getJSONObject("orders").getString("order_id");
                        Toast.makeText(getApplicationContext(), "Hi " + order_id +", Order successfully Placed!", Toast.LENGTH_SHORT).show();

                        // Launch login activity
                        Intent intent = new Intent(Checkout.this, OrderConfirmed.class);
                        startActivity(intent);
                        finish();
                    } else {

                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Registration Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {

                SharedPreferences prefs = getSharedPreferences("user", MODE_PRIVATE);
                Boolean isLoggedin = prefs.getBoolean("isLoggedin", false);

                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("orders", OrdersToSend);
                String order_id = UUID.randomUUID().toString();
                int cust_id = prefs.getInt("cust_id", 0);
                String name = prefs.getString("name", null);
                String email = prefs.getString("email", null);
                if(name!=null && email!=name && cust_id>0)
                {
                    params.put("contact", "9881489776");
                    params.put("order_id", order_id);
                    params.put("name", name);
                    params.put("email", email);
                    params.put("cust_id", ""+cust_id);
                }

                return params;
            }
        };
        // Adding request to request queue
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(strReq, cancel_req_tag);
    }

    private void showDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    private void hideDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }
}