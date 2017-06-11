package com.gabriel.ridewithme;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class CreateRideActivity extends AppCompatActivity  implements View.OnClickListener {

    Button btnDatePicker, btnTimePicker, bSave;
    EditText txtDate, txtTime, fromTxt, toTxt, priceTxt;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ride);

        fromTxt = (EditText)findViewById(R.id.etFrom);
        toTxt = (EditText)findViewById(R.id.etTo);
        priceTxt = (EditText)findViewById(R.id.etPrice);
        bSave = (Button)findViewById(R.id.bSave);

        btnDatePicker=(Button)findViewById(R.id.bSelectDate);
        btnTimePicker=(Button)findViewById(R.id.bSelectTime);
        txtDate=(EditText)findViewById(R.id.selectedDate);
        txtTime=(EditText)findViewById(R.id.selectedTime);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);


        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String from = fromTxt.getText().toString();
                final String to = toTxt.getText().toString();
                final String price = priceTxt.getText().toString();
                final String date = txtDate.getText().toString();
                final String time = txtTime.getText().toString();

                if (from.length() == 0 || to.length() == 0 || price.length() == 0 || date.length() == 0 || time.length() == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(CreateRideActivity.this);
                    builder.setMessage("Toate campurile sunt obligatorii!")
                            .setNegativeButton("Incearca din nou", null)
                            .create()
                            .show();
                    return;
                }


                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);

                            if (jsonResponse != null) {
                                Intent intent = new Intent(CreateRideActivity.this, MainActivity.class);
                                CreateRideActivity.this.startActivity(intent);

                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(CreateRideActivity.this);
                                builder.setMessage("Crearea unei noi rute a esuat!")
                                        .setNegativeButton("Incearca din nou", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();

                            AlertDialog.Builder builder = new AlertDialog.Builder(CreateRideActivity.this);
                            builder.setMessage("Crearea unei noi rute a esuat!")
                                    .setNegativeButton("Incearca din nou", null)
                                    .create()
                                    .show();
                        }
                    }
                };

                CreateRideRequest createRideRequest = null;
                try {
                    createRideRequest = new CreateRideRequest(from, to, price, date, time, responseListener);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RequestQueue queue = Volley.newRequestQueue(CreateRideActivity.this);
                queue.add(createRideRequest);
            }
        });
    }


// DATE & TIME picker ----------------------------------------------------------------------------
    @Override
    public void onClick(View v) {

        if (v == btnDatePicker) {
            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            txtTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }
}
