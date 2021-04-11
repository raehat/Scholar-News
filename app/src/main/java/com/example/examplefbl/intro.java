package com.example.examplefbl;

import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import com.github.appintro.AppIntro;
import com.github.appintro.AppIntroFragment;

public class intro extends AppIntro {



    @Override
        public void onCreate (Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            getSupportActionBar().hide();
        if (SaveSharedPreference.getLoggedStatus(getApplicationContext()))
        {
            Intent intent= new Intent(getApplicationContext(), loginOrRegister.class);
            startActivity(intent);
        }

        new AlertDialog.Builder(this).setTitle("An app by Team Kurtoziz")
                .setMessage("")
                .setNeutralButton("Ok", null).show();

        addSlide(AppIntroFragment.newInstance(
                "Welcome...",
                "Become a certified editor and publish your own articles! ",R.drawable.pic1
        ));
        addSlide(AppIntroFragment.newInstance(
                "...Let's get started!",
                "Login either as a student or an editor",R.drawable.pic2
        ));
        addSlide(AppIntroFragment.newInstance(
                "Last slide wooohoo",
                "Access all the latest news!!!",R.drawable.pic3
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




