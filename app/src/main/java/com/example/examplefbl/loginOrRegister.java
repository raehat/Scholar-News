package com.example.examplefbl;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
        Button editorLogin= (Button) findViewById(R.id.editorLogin);

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
    public void onBackPressed() {
        new AlertDialog.Builder(loginOrRegister.this)
                .setTitle("EXIT?")
                .setMessage("Do you want to leave?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent a = new Intent(Intent.ACTION_MAIN);
                        a.addCategory(Intent.CATEGORY_HOME);
                        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(a);
                    }
                }).setNegativeButton("NO", null).show();
    }
}