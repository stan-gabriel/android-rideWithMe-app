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

    private static final String REGISTER_REQUEST_URL = "http://10.0.2.2:3000/api/v1/user";
    JSONObject params = new JSONObject();

    public RegisterRequest(String firstName, String lastName, String email, String password, Response.Listener<String> listener) throws JSONException {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params.put("firstName", firstName);
        params.put("lastName", lastName);
        params.put("email", email);
        params.put("password", password);
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
