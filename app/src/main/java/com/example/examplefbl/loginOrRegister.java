package com.example.examplefbl;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class loginOrRegister extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginorregister);

        getSupportActionBar().hide();
        SaveSharedPreference.setLoggedIn(getApplicationContext(), true);
        if (FirebaseAuth.getInstance().getCurrentUser()!=null)
        {
            Intent intent= new Intent(getApplicationContext(), afterLogin.class);
            startActivity(intent);
        }
        @SuppressLint("WrongConstant") SharedPreferences sh
                = getSharedPreferences("MySharedPref", MODE_APPEND);
        int tf = sh.getInt("tf", 0);

        if (tf==1)
        {
            startActivity(new Intent(getApplicationContext(), EditorPage.class));
            finish();
            System.exit(0);
        }

        /*if (FirebaseAuth.getInstance().getCurrentUser()!=null)
        {
            finish();
            System.exit(0);
        }*/

        Button login= (Button) findViewById(R.id.login);
        Button account= (Button) findViewById(R.id.account);
        TextView editorLogin= (TextView) findViewById(R.id.editorLogin);

        editorLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(loginOrRegister.this, EditorLogin.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), com.example.examplefbl.login.class));
            }
        });

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), createAccount.class));
            }
        });
    }
}