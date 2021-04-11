package com.example.examplefbl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class EditorLogin extends AppCompatActivity {

    EditText username;
    EditText password;
    Button loginEditor;
    FirebaseFirestore fstore;
    public static String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_login);

        getSupportActionBar().setTitle("LOGIN AS EDITOR");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        username= findViewById(R.id.username);
        password= findViewById(R.id.password);
        loginEditor= findViewById(R.id.login_editor);
        fstore= FirebaseFirestore.getInstance();

        loginEditor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog progressDialog= new ProgressDialog(EditorLogin.this);
                progressDialog.setTitle("Verifying the password");
                progressDialog.show();
                user= username.getText().toString();
                DocumentReference documentReference= fstore.collection("editors").document(username.getText().toString());
                documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            if (password.getText().toString().contains(documentSnapshot.getString("password"))) {
                                Toast.makeText(EditorLogin.this, "Logged in!!!", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                @SuppressLint("WrongConstant") SharedPreferences sh
                                        = getSharedPreferences("MySharedPref", MODE_APPEND);
                                SharedPreferences.Editor myEdit
                                        = sh.edit();
                                myEdit.putString("username",username.getText().toString());
                                myEdit.putString("password",password.getText().toString());
                                myEdit.commit();

                                startActivity(new Intent(EditorLogin.this, EditorPage.class));
                            } else {
                                Toast.makeText(EditorLogin.this, "WRONG PASSWORD", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        }
                        else {
                            Toast.makeText(EditorLogin.this, "user doesn't exist" , Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EditorLogin.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}