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

import adapter.RecyclerViewAdapter_userhome;
import adapter.RecyclerViewAdapter_usersaved;

public class UserSavedFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<datamodel_userSaved> dataholder_userSaved;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved_user,container,false);
        recyclerView=view.findViewById(R.id.recyclerView_UserSaved);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dataholder_userSaved = new ArrayList<>();

        datamodel_userSaved ob1=new datamodel_userSaved(R.drawable.testimg,"Angular","Web Application");
        dataholder_userSaved.add(ob1);

        datamodel_userSaved ob2=new datamodel_userSaved(R.drawable.testimg,"C Programming","Embed Programming");
        dataholder_userSaved.add(ob2);

        datamodel_userSaved ob3=new datamodel_userSaved(R.drawable.testimg,"C++ Programming","Embed Programming");
        dataholder_userSaved.add(ob3);

        datamodel_userSaved ob4=new datamodel_userSaved(R.drawable.testimg,".NET Programming","Desktop and Web Programming");
        dataholder_userSaved.add(ob4);

        datamodel_userSaved ob5=new datamodel_userSaved(R.drawable.testimg,"Java Programming","Desktop and Web Programming");
        dataholder_userSaved.add(ob5);

        datamodel_userSaved ob6=new datamodel_userSaved(R.drawable.testimg,"Magento","Web Application Framework");
        dataholder_userSaved.add(ob6);

        datamodel_userSaved ob7=new datamodel_userSaved(R.drawable.testimg,"NodeJS","Web Application Framework");
        dataholder_userSaved.add(ob7);

        datamodel_userSaved ob8=new datamodel_userSaved(R.drawable.testimg,"Python","Desktop and Web Programming");
        dataholder_userSaved.add(ob8);

        datamodel_userSaved ob9=new datamodel_userSaved(R.drawable.testimg,"Shopify","E-Commerce Framework");
        dataholder_userSaved.add(ob9);

        datamodel_userSaved ob10=new datamodel_userSaved(R.drawable.testimg,"Wordpress","WebApplication Framewrok");
        dataholder_userSaved.add(ob10);

        recyclerView.setAdapter(new RecyclerViewAdapter_usersaved(dataholder_userSaved));
        return view;
    }
}