package com.example.examplefbl;

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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import adapter.RecyclerViewAdapter_userhome;
import adapter.RecyclerViewAdapter_usersaved;

public class UserSavedFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<datamodel_userSaved> dataholder_userSaved;
    FirebaseFirestore fstore;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved_user,container,false);
        recyclerView=view.findViewById(R.id.recyclerView_UserSaved);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dataholder_userSaved = new ArrayList<>();

        fstore= FirebaseFirestore.getInstance();

        fstore.collection(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot querySnapshot: task.getResult())
                {
                    fstore.collection("articles")
                            .document(querySnapshot.getString("code"))
                            .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            datamodel_userSaved obj= new datamodel_userSaved(
                                    documentSnapshot.getString("imageCode"),
                                    documentSnapshot.getString("heading"),
                                    documentSnapshot.getString("article")
                            );
                            dataholder_userSaved.add(obj);

                            setAdapt();
                        }
                    });
                }

            }

        });

        /*datamodel_userSaved ob1=new datamodel_userSaved(R.drawable.testimg,"Angular","Web Application");
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
        dataholder_userSaved.add(ob10);*/

        /*datamodel_userSaved obj= new datamodel_userSaved(
                "a",
                "b",
                "c");
        dataholder_userSaved.add(obj);
        recyclerView.setAdapter(new RecyclerViewAdapter_usersaved(dataholder_userSaved));*/

        return view;
    }

    private void setAdapt() {
        recyclerView.setAdapter(new RecyclerViewAdapter_usersaved(dataholder_userSaved));
    }
}