package com.example.examplefbl;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static android.os.ParcelFileDescriptor.MODE_APPEND;

public class accountfragment extends Fragment {

    private TextView username;
    private Button logout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_accounts,container,false);

        username= view.findViewById(R.id.username);
        logout= view.findViewById(R.id.logout);

        @SuppressLint("WrongConstant") SharedPreferences sh
                = getActivity().getSharedPreferences("MySharedPref", MODE_APPEND);
        final String userr = sh.getString("username", "");

        username.setText(userr);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                @SuppressLint("WrongConstant") SharedPreferences sh
                        = getContext().getSharedPreferences("MySharedPref", MODE_APPEND);
                SharedPreferences.Editor myEdit
                        = sh.edit();
                myEdit.putInt("tf",
                        Integer.parseInt(
                                String.valueOf(0)));
                myEdit.commit();
                startActivity(new Intent(getContext(), loginOrRegister.class));
            }
        });

        return view;
    }
}
