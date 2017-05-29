package com.gabriel.ridewithme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etFullName = (EditText) findViewById(R.id.etFullName);
        final TextView welcomeMessage = (TextView) findViewById(R.id.tvWelcomeMsg);

        Intent intent = getIntent();
        String fisrtName = intent.getStringExtra("fistName");
        String lastName = intent.getStringExtra("lastName");
        String fullName = fisrtName + " " + lastName;

        String message = fisrtName + " welcome to you user area";
        welcomeMessage.setText(message);
        etFullName.setText(fullName);

    }
}
