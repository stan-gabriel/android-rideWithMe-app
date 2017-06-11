package com.gabriel.ridewithme;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etFullName = (EditText) findViewById(R.id.etFullName);
        final TextView welcomeMessage = (TextView) findViewById(R.id.tvWelcomeMsg);
        final Button bCreateRide = (Button) findViewById(R.id.bCreateRide);
        final Button bFindRide = (Button) findViewById(R.id.bFindRide);

        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String firstName = sharedPreferences.getString("firstName", "");
        String lastName = sharedPreferences.getString("lastName", "");
        String fullName = sharedPreferences.getString("fullName", "");

//        Intent intent = getIntent();
//        String fisrtName = intent.getStringExtra("fistName");
//        String lastName = intent.getStringExtra("lastName");
//        String fullName = fisrtName + " " + lastName;

        String message = firstName + " bine ai venit!";
        welcomeMessage.setText(message);
        etFullName.setText(fullName);


        bCreateRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createRideIntent = new Intent(MainActivity.this, CreateRideActivity.class);
                MainActivity.this.startActivity(createRideIntent);
            }
        });

        bFindRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent findRideIntent = new Intent(MainActivity.this, FindRideActivity.class);
                MainActivity.this.startActivity(findRideIntent);
            }
        });
    }
}
