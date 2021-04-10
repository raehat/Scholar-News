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
import adapter.RecyclerViewAdapter_userhome;

public class UserHomeFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<datamodel_userHome> dataholder_userhome;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home_user,container,false);
        recyclerView=view.findViewById(R.id.recyclerView_UserHome);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dataholder_userhome = new ArrayList<>();

        datamodel_userHome ob1=new datamodel_userHome(R.drawable.testimg,"Angular","Web Application");
        dataholder_userhome.add(ob1);

        datamodel_userHome ob2=new datamodel_userHome(R.drawable.testimg,"C Programming","Embed Programming");
        dataholder_userhome.add(ob2);

        datamodel_userHome ob3=new datamodel_userHome(R.drawable.testimg,"C++ Programming","Embed Programming");
        dataholder_userhome.add(ob3);

        datamodel_userHome ob4=new datamodel_userHome(R.drawable.testimg,".NET Programming","Desktop and Web Programming");
        dataholder_userhome.add(ob4);

        datamodel_userHome ob5=new datamodel_userHome(R.drawable.testimg,"Java Programming","Desktop and Web Programming");
        dataholder_userhome.add(ob5);

        datamodel_userHome ob6=new datamodel_userHome(R.drawable.testimg,"Magento","Web Application Framework");
        dataholder_userhome.add(ob6);

        datamodel_userHome ob7=new datamodel_userHome(R.drawable.testimg,"NodeJS","Web Application Framework");
        dataholder_userhome.add(ob7);

        datamodel_userHome ob8=new datamodel_userHome(R.drawable.testimg,"Python","Desktop and Web Programming");
        dataholder_userhome.add(ob8);

        datamodel_userHome ob9=new datamodel_userHome(R.drawable.testimg,"Shopify","E-Commerce Framework");
        dataholder_userhome.add(ob9);

        datamodel_userHome ob10=new datamodel_userHome(R.drawable.testimg,"Wordpress","WebApplication Framewrok");
        dataholder_userhome.add(ob10);

        recyclerView.setAdapter(new RecyclerViewAdapter_userhome(dataholder_userhome));
        return view;
    }
}