package com.gabriel.ridewithme;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class CreateRideRequest extends StringRequest {

    private static final String CREATE_RIDE_REQUEST_URL = "http://10.0.2.2:3000/api/v1/ride";
    JSONObject params = new JSONObject();

    public CreateRideRequest(String from, String to, String price, String date, String time, Response.Listener<String> listener) throws JSONException {
        super(Request.Method.POST, CREATE_RIDE_REQUEST_URL, listener, null);
        params.put("from", from);
        params.put("to", to);
        params.put("price", price);
        params.put("date", date);
        params.put("time", time);
    }

    @Override
    public String getBodyContentType() {
        return "application/json";
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        final String mRequestBody = params.toString();
        try {
            return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
        } catch (UnsupportedEncodingException uee) {
            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
            return null;
        }
    }
}
