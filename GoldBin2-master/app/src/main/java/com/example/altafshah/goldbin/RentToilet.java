package com.example.altafshah.goldbin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.common.ConnectionResult;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class RentToilet extends AppCompatActivity {
    private FusedLocationProviderClient client;
    private static final String TAG = "RentToilet";
    private static final String URL_FOR_ADDING_TREE = "http://www.vodafoneindiaservices.com/Goldbin/register.php";

    ProgressDialog progressDialog;
    Button GetLocation, SaveLocations;
    EditText Lat_locations, Lng_location;

    private Button btnSaveTree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_toilet);

        // Progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        client = LocationServices.getFusedLocationProviderClient(this);
        GetLocation = (Button) findViewById(R.id.getLocation);
        SaveLocations = (Button)findViewById(R.id.SaveLocation);

        Lat_locations = (EditText) findViewById(R.id.Lat_Location);
        Lng_location = (EditText) findViewById(R.id.Lng_Location);

        btnSaveTree = (Button) findViewById(R.id.SaveTreeLocation);

        GetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(RentToilet.this,ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
                    return;
                }

                client.getLastLocation().addOnSuccessListener(RentToilet.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location!=null){
                            float lat = (float) (location.getLatitude());
                            float lng = (float) (location.getLongitude());
                            Lat_locations.setText(String.valueOf(lat));
                            Lng_location.setText(String.valueOf(lng));
                        }
                    }
                });
            }
        });
    }

    private void submitTree(final String Lat_Location, final String Lng_Location, final String TreeName, final String TreeType) {
        // Tag used to cancel the request
        String cancel_req_tag = "Enter";

        progressDialog.setMessage("Adding tree...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                URL_FOR_ADDING_TREE, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Tree Entry Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    if (!error) {
                        String user = jObj.getJSONObject("user").getString("name");
                        Toast.makeText(getApplicationContext(), "Hi " + user +", You are successfully Added!", Toast.LENGTH_SHORT).show();

                        // Launch login activity
                        Intent intent = new Intent(
                                RentToilet.this,
                                MainActivity.class);
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
                Log.e(TAG, "Data Entry Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("Latitude", Lat_Location);
                params.put("Longitude", Lng_Location);
                params.put("Tree Name", TreeName);
                params.put("Tree Type", TreeType);
                //params.put("age", dob);
                return params;
            }
        };
        // Adding request to request queue
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(strReq, cancel_req_tag);
    }


//    --------------------------------------------------------------------------------------------------

    private void getlocation(final String locations){

    }
        private void requestPermission(){
        ActivityCompat.requestPermissions(this,new String[]{ACCESS_FINE_LOCATION},1);
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
