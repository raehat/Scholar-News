package com.example.examplefbl;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Document;

import java.util.ArrayList;

import adapter.RecyclerViewAdapter;

import static android.os.ParcelFileDescriptor.MODE_APPEND;

public class fragment_article extends Fragment {
    RecyclerView recyclerview;
    ArrayList<datamodel> dataholder;
    FirebaseFirestore fstore;
    MyRecyclerViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_article,container,false);
        recyclerview=view.findViewById(R.id.recyclerView);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        dataholder = new ArrayList<>();
        fstore= FirebaseFirestore.getInstance();

        Toast.makeText(getContext(), "Scroll down for more!", Toast.LENGTH_SHORT).show();
        ProgressDialog progressDialog= new ProgressDialog(getContext());
        progressDialog.setTitle("Loading, just a moment..");
        progressDialog.setMessage("This won't take a lot of time");
        progressDialog.show();
        @SuppressLint("WrongConstant") SharedPreferences sh
                = getActivity().getSharedPreferences("MySharedPref", MODE_APPEND);
        final String userr = sh.getString("username", "");
        CollectionReference documentReference= fstore.collection("articles" + userr);
        fstore.collection("articles" + userr)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (DocumentSnapshot querySnapshot: task.getResult())
                        {
                            datamodel obj= new datamodel(R.drawable.testimg,querySnapshot.getString("heading"),querySnapshot.getString("article"), querySnapshot.getString("imageCode"));
                            dataholder.add((obj));
                        }
                        progressDialog.dismiss();
                        adapter= new MyRecyclerViewAdapter(getContext(), dataholder);
                        recyclerview.setAdapter(adapter);
                    }
                });


        /*datamodel ob1=new datamodel(R.drawable.testimg,"Angular","Web Application");
        dataholder.add(ob1);

        datamodel ob2=new datamodel(R.drawable.testimg,"C Programming","Embed Programming");
        dataholder.add(ob2);

        datamodel ob3=new datamodel(R.drawable.testimg,"C++ Programming","Embed Programming");
        dataholder.add(ob3);

        datamodel ob4=new datamodel(R.drawable.testimg,".NET Programming","Desktop and Web Programming");
        dataholder.add(ob4);

        datamodel ob5=new datamodel(R.drawable.testimg,"Java Programming","Desktop and Web Programming");
        dataholder.add(ob5);

        datamodel ob6=new datamodel(R.drawable.testimg,"Magento","Web Application Framework");
        dataholder.add(ob6);

        datamodel ob7=new datamodel(R.drawable.testimg,"NodeJS","Web Application Framework");
        dataholder.add(ob7);

        datamodel ob8=new datamodel(R.drawable.testimg,"Python","Desktop and Web Programming");
        dataholder.add(ob8);

        datamodel ob9=new datamodel(R.drawable.testimg,"Shopify","E-Commerce Framework");
        dataholder.add(ob9);

        datamodel ob10=new datamodel(R.drawable.testimg,"Wordpress","WebApplication Framewrok");
        dataholder.add(ob10);*/

        return view;
    }
}
