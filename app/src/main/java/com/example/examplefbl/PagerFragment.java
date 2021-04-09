package com.example.examplefbl;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class PagerFragment extends Fragment {

    private String[] imageViewUrls = new String[]{
            "ashishTestCase.PNG",
            "bgfinal.png"
    };

    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000; // time in milliseconds between successive task executions.

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_pager, container, false);

        final ViewPager viewPager = view.findViewById(R.id.view_pager3);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getContext(), Arrays.asList(imageViewUrls));
        viewPager.setAdapter(adapter);

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                viewPager.post(new Runnable() {

                    @Override
                    public void run() {
                        viewPager.setCurrentItem((viewPager.getCurrentItem() + 1) % imageViewUrls.length);
                    }
                });
            }
        };
        timer = new Timer();
        timer.schedule(timerTask, 3000, 3000);


        return view;
    }

    public void onDestroy() {
        timer.cancel();
        super.onDestroy();
    }
}