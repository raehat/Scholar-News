package com.example.examplefbl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class afterLogin extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afterlogin);

        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}