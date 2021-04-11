package com.example.examplefbl;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserAccountsFragment extends Fragment {

    TextView fname;
    TextView City;
    TextView pin;
    TextView email;
    TextView number;
    FirebaseFirestore fstore;

    Button logout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_accounts_user,container,false);

        logout= view.findViewById(R.id.logout_user);
        fname= view.findViewById(R.id.fname);
        number= view.findViewById(R.id.number);
        email= view.findViewById(R.id.email);
        City= view.findViewById(R.id.City);
        pin= view.findViewById(R.id.pin);

        fstore= FirebaseFirestore.getInstance();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getContext(), loginOrRegister.class));
            }
        });

        fstore.collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                fname.setText(documentSnapshot.getString("fname"));
                number.setText(documentSnapshot.getString("number"));
                email.setText(documentSnapshot.getString("email"));
                City.setText(documentSnapshot.getString("City"));
                pin.setText(documentSnapshot.getString("pin"));
            }
        });

        return view;
    }
}