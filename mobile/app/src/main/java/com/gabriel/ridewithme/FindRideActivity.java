package com.gabriel.ridewithme;

import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FindRideActivity extends AppCompatActivity {

    Button bSearch;
    EditText fromTxt, toTxt;
    ListView lvRidesList;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_ride);

        fromTxt = (EditText) findViewById(R.id.etFrom);
        toTxt = (EditText) findViewById(R.id.etTo);
        bSearch = (Button) findViewById(R.id.bSearch);
        requestQueue = Volley.newRequestQueue(this);


        lvRidesList = (ListView) findViewById(R.id.lvRidesList);


        bSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String from = fromTxt.getText().toString();
                final String to = toTxt.getText().toString();

                if(from.length() == 0 || to.length() == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(FindRideActivity.this);
                    builder.setMessage("Locul de plecare cat si destinatia sunt obligatorii!")
                            .setNegativeButton("Incearca din nou", null)
                            .create()
                            .show();
                    return;
                }

                JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET, "http://10.0.2.2:3000/api/v1/ride/" + from + "/" + to, null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {

                                List<String> list = new ArrayList<String>();
                                if (response.length() > 0){
                                    for(int i = 0; i < response.length(); i++){
                                        String info = null;
                                        try {
                                            info = "\n" + response.getJSONObject(i).getString("from") + " - " + response.getJSONObject(i).getString("to") + "\n" +
                                                    "Pret - " + response.getJSONObject(i).getString("price") + " RON" + "\n" +
                                                    "Data de plecare - " + response.getJSONObject(i).getString("date") + "\n" +
                                                    "Ora de plecare - " + response.getJSONObject(i).getString("time");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        list.add(info);
                                    }
                                }else{
                                    list.add("Pentru: " + from + " - "+ to +"\n" +"Nu a fost gasita nici o inregistrare...");
                                };


                                ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(FindRideActivity.this, android.R.layout.simple_list_item_1, list);
                                lvRidesList.setAdapter(itemsAdapter);
                                Log.i("RideAdapter: ", response.toString());
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                            }
                        });

                requestQueue.add(jsonRequest);
            }
        });

    }


}
