package com.gabriel.ridewithme;


import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    private static final String REGISTER_REQUEST_URL = "http://192.168.100.2:3000/api/v1/user";
//    private Map<String, String> params;
//    Map<String, String> params = new HashMap<String, String>();
    JSONObject params = new JSONObject();

    public RegisterRequest(String name, String email, String password, Response.Listener<String> listener) throws JSONException {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);

        params.put("email", email);
        params.put("password", password);
        params.put("name", name);
    }

    @Override
    public String getBodyContentType() {
        return "application/json";
    }

//    @Override
//    public Map<String, String> getParams() throws AuthFailureError {
//        return params;
//    }

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

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String responseString = "";
        if (response != null) {

            responseString = String.valueOf(response.statusCode);

        }
        return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
    }
}
