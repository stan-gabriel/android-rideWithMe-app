package com.gabriel.ridewithme;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class LoginRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://192.168.100.2:3000/api/v1/user/login";
    JSONObject params = new JSONObject();

    public LoginRequest(String email, String password, Response.Listener<String> listener) throws JSONException {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
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