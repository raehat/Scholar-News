package com.example.examplefbl;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

public class EditorPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_page);

        @SuppressLint("WrongConstant") SharedPreferences sh
                = getSharedPreferences("MySharedPref", MODE_APPEND);
        SharedPreferences.Editor myEdit
                = sh.edit();
        myEdit.putInt("tf",
                Integer.parseInt(
                        String.valueOf(1)));
        myEdit.commit();
        Toast.makeText(this, "" + sh.getString("username", "lll"), Toast.LENGTH_SHORT).show();
    }
}