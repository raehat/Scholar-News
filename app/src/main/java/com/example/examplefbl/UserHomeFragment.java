package com.example.examplefbl;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.StackFrom;
import com.yuyakaido.android.cardstackview.SwipeableMethod;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adapter.RecyclerViewAdapter;
import adapter.RecyclerViewAdapter_userhome;

import static android.content.ContentValues.TAG;

public class UserHomeFragment extends Fragment {
    /*RecyclerView recyclerView;
    ArrayList<datamodel_userHome> dataholder_userhome;*/

    private CardStackLayoutManager manager;
    private CardStackAdapter adapter;
    private List<ItemModel> lst;
    private FirebaseFirestore fstore;
    private CardStackView cardStackView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home_user,container,false);


        lst= new ArrayList<>();
        /*lst.add(new ItemModel(R.drawable.logo_app, "Markonah", "Jember"));
        lst.add(new ItemModel(R.drawable.logo_app, "Markonahhh", "Jemberrrr"));*/
        fstore= FirebaseFirestore.getInstance();

        fstore= FirebaseFirestore.getInstance();

        fstore.collection("articles")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot querySnapshot: task.getResult())
                {
                    lst.add(new ItemModel(querySnapshot.getString("imageCode"),querySnapshot.getString("heading")
                    ,querySnapshot.getString("article")));
                }
                adapter = new CardStackAdapter(lst);
                cardStackView.setAdapter(adapter);
            }
        });

        cardStackView = view.findViewById(R.id.card_stack_view);
        manager= new CardStackLayoutManager(getContext(), new CardStackListener() {
            @Override
            public void onCardDragging(Direction direction, float ratio) {
                Log.d(TAG, "onCardDragging: d=" + direction.name() + " ratio=" + ratio);
            }

            @Override
            public void onCardSwiped(Direction direction) {
                Log.d(TAG, "onCardSwiped: p=" + manager.getTopPosition() + " d=" + direction);
                if (direction == Direction.Right){
                    Toast.makeText(getContext(), "Direction Right", Toast.LENGTH_SHORT).show();
                }
                if (direction == Direction.Top){

                    Map<String, Object> object= new HashMap<>();
                    object.put("code", lst.get(manager.getTopPosition()-1).getImage());
                    fstore.collection(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .document(lst.get(manager.getTopPosition()-1).getImage())
                            .set(object);
                    Toast.makeText(getContext(), "Direction Top", Toast.LENGTH_SHORT).show();
                }
                if (direction == Direction.Left){
                    Toast.makeText(getContext(), "Direction Left", Toast.LENGTH_SHORT).show();
                }
                if (direction == Direction.Bottom){
                    Toast.makeText(getContext(), "Direction Bottom", Toast.LENGTH_SHORT).show();
                }

                // Paginating
                if (manager.getTopPosition() == adapter.getItemCount() - 5){
                    paginate();
                }

            }


            @Override
            public void onCardRewound() {
                Log.d(TAG, "onCardRewound: " + manager.getTopPosition());
            }

            @Override
            public void onCardCanceled() {
                Log.d(TAG, "onCardRewound: " + manager.getTopPosition());

            }

            @Override
            public void onCardAppeared(View view, int position) {
                TextView tv = view.findViewById(R.id.item_heading);
                Log.d(TAG, "onCardAppeared: " + position + ", nama: " + tv.getText());
            }

            @Override
            public void onCardDisappeared(View view, int position) {
                TextView tv = view.findViewById(R.id.item_heading);
                Log.d(TAG, "onCardAppeared: " + position + ", nama: " + tv.getText());
            }
        });

        manager.setStackFrom(StackFrom.None);
        manager.setVisibleCount(3);
        manager.setTranslationInterval(8.0f);
        manager.setScaleInterval(0.95f);
        manager.setSwipeThreshold(0.3f);
        manager.setMaxDegree(20.0f);
        manager.setDirections(Direction.FREEDOM);
        manager.setCanScrollHorizontal(true);
        manager.setSwipeableMethod(SwipeableMethod.Manual);
        manager.setOverlayInterpolator(new LinearInterpolator());
        
        cardStackView.setLayoutManager(manager);
        
        cardStackView.setItemAnimator(new DefaultItemAnimator());



        /*recyclerView=view.findViewById(R.id.recyclerView_UserHome);
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

        recyclerView.setAdapter(new RecyclerViewAdapter_userhome(dataholder_userhome));*/

        return view;
    }

    private void paginate() {
        List<ItemModel> old = adapter.getItems();
        List<ItemModel> baru = new ArrayList<>(lst);
        CardStackCallBack callback = new CardStackCallBack(old, baru);
        DiffUtil.DiffResult hasil = DiffUtil.calculateDiff(callback);
        adapter.setItems(baru);
        hasil.dispatchUpdatesTo(adapter);
    }

    private List<ItemModel> addList() {
        List<ItemModel> items = new ArrayList<>();
        /*items.add(new ItemModel(R.drawable.logo_app, "Markonah", "Jember"));
        items.add(new ItemModel(R.drawable.logo_app, "Markonahhh", "Jemberrrr"));*/
        return items;
    }
}