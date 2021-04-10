package com.example.examplefbl;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import adapter.RecyclerViewAdapter;

public class fragment_article extends Fragment {
    RecyclerView recyclerview;
    ArrayList<datamodel> dataholder;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_article,container,false);
        recyclerview=view.findViewById(R.id.recyclerView);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        dataholder = new ArrayList<>();

        datamodel ob1=new datamodel(R.drawable.testimg,"Angular","Web Application");
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
        dataholder.add(ob10);

        recyclerview.setAdapter(new RecyclerViewAdapter(dataholder));

        return view;
    }
}
