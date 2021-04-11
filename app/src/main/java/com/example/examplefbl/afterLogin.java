package com.example.examplefbl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class afterLogin extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afterlogin);
        /*overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);*/

        getSupportActionBar().hide();
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation_user);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportActionBar().setTitle("SCHOLAR NEWS");
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_user,new UserHomeFragment()).commit();


    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch(item.getItemId()){
                        case R.id.nav_home_user:
                            selectedFragment = new UserHomeFragment();
                            break;
                        case R.id.nav_saved_user:
                            selectedFragment = new UserSavedFragment();
                            break;
                        case R.id.nav_account_user:
                            selectedFragment = new UserAccountsFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_user,
                            selectedFragment).commit();
                    return true;
                }
            };
    public void onBackPressed() {
        new AlertDialog.Builder(afterLogin.this)
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