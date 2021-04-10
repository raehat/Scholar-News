package com.example.examplefbl;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.example.examplefbl.EditorLogin.user;

public class EditorPage extends AppCompatActivity {

    EditText article;
    Button submitArticle;
    FirebaseFirestore fstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_page);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportActionBar().setTitle("SCHOLAR NEWS");
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();

        /*hey*/
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
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch(item.getItemId()){
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_article:
                            selectedFragment = new fragment_article();
                            break;
                        case R.id.nav_account:
                            selectedFragment = new accountfragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };
}