package com.gabriel.ridewithme;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;


public class FindRideRequest {

    private static final String FIND_RIDE_REQUEST_URL = "http://10.0.2.2:3000/api/v1/ride/:from/:to";
    JSONObject params = new JSONObject();

    public FindRideRequest(String from, String to) throws JSONException {
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, FIND_RIDE_REQUEST_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // the response is already constructed as a JSONObject!
                try {
                    response = response.getJSONObject("args");
                    String site = response.getString("site"),
                            network = response.getString("network");
                    System.out.println("Site: "+site+"\nNetwork: "+network);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
    }

}
