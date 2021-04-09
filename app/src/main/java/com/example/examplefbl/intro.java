package com.example.examplefbl;

import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;

import com.github.appintro.AppIntro;
import com.github.appintro.AppIntroFragment;

public class intro extends AppIntro {



    @Override
        public void onCreate (Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

        if (SaveSharedPreference.getLoggedStatus(getApplicationContext()))
        {
            Intent intent= new Intent(getApplicationContext(), loginOrRegister.class);
            startActivity(intent);
        }

        addSlide(AppIntroFragment.newInstance(
                "Welcome...",
                "We are really thankful for you to download this app. You can either skip this intro or bother" +
                        " yourself for this lol!"
        ));
        addSlide(AppIntroFragment.newInstance(
                "...Let's get started!",
                "Well! You can buy or sell stuff using this app! Very nycccc great success!!!"
        ));
        addSlide(AppIntroFragment.newInstance(
                "Last slide lmaoo",
                "You're all done!! Now you can either login or" +
                        " create an account if you're new ehehe)"
        ));
    }

    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        Intent intent= new Intent(intro.this, loginOrRegister.class);
        startActivity(intent);
        // Decide what to do when the user clicks on "Skip"
        finish();
    }

    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        // Decide what to do when the user clicks on "Done"
        Intent intent= new Intent(intro.this, loginOrRegister.class);
        startActivity(intent);
        finish();
    }

        }




