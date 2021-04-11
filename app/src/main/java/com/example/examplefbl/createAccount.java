package com.example.examplefbl;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class createAccount extends AppCompatActivity {

    EditText editTextPhone;
    CountryCodePicker ccp;
    String number;
    EditText name, email, city, pinn;
    public String codeSent;
    TextView codesent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().hide();
        Button button3 = findViewById(R.id.button3);
        name= findViewById(R.id.name);
        email= findViewById(R.id.email);
        city= findViewById(R.id.city);
        pinn= findViewById(R.id.pinn);
        editTextPhone = findViewById(R.id.no);
        ccp = findViewById(R.id.ccp);
        codesent= findViewById(R.id.codesent);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOtp();
            }
        });
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        /*overridePendingTransition(R.anim.slide_down, R.anim.slide_down1);*/
    }

    private void getOtp() {
        number = "+" + ccp.getFullNumber() + editTextPhone.getText().toString();

        Intent intent = new Intent(getApplicationContext(), enterOTP.class);
        intent.putExtra("temp", number);
        intent.putExtra("name", name.getText().toString());
        intent.putExtra("email", email.getText().toString());
        intent.putExtra("pinn", pinn.getText().toString());
        intent.putExtra("city", city.getText().toString());
        startActivity(intent);


    }



}

