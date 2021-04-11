package com.example.examplefbl;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductViewModel extends RecyclerView.ViewHolder {
    public TextView heading;
    public TextView article;
    public ImageView imageView;
    public ImageView delete;
    public ProgressBar progressBar;

    public ProductViewModel(@NonNull View itemView) {
        super(itemView);
        heading= itemView.findViewById(R.id.header2);
        article= itemView.findViewById(R.id.description2);
        imageView= itemView.findViewById(R.id.icon_img3);
        delete= itemView.findViewById(R.id.delete);
        progressBar= itemView.findViewById(R.id.progressBar);
    }
}
