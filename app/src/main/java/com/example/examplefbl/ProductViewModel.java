package com.example.examplefbl;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ProductViewModel extends RecyclerView.ViewHolder {
    public TextView heading;
    public TextView article;
    public ImageView imageView;

    public ProductViewModel(@NonNull View itemView) {
        super(itemView);
        heading= itemView.findViewById(R.id.header);
        article= itemView.findViewById(R.id.description);
        imageView= itemView.findViewById(R.id.icon_img1);
    }
}
