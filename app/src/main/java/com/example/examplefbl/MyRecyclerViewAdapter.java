package com.example.examplefbl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<ProductViewModel> {

    private FirebaseStorage storage;
    private StorageReference storageReference;

    public MyRecyclerViewAdapter(Context context, ArrayList<datamodel> list) {
        this.context = context;
        this.list = list;
    }

    public Context context;
    public ArrayList<datamodel> list;
    public ProductViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());



        View view= layoutInflater.inflate(R.layout.cardview, parent, false);
        return new ProductViewModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductViewModel holder, final int position) {
        holder.heading.setText(list.get(position).getHeader());
        holder.article.setText(list.get(position).getDesc());
        final String imageAdd= list.get(position).getDocId();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference().child("images/" + imageAdd);
        File localFile = null;
        try {
            localFile = File.createTempFile(imageAdd, "jpeg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        final File finalLocalFile = localFile;
        storageReference.getFile(localFile)
                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        Bitmap bitmap= BitmapFactory.decodeFile(finalLocalFile.getAbsolutePath());
                        Toast.makeText(context, "" + imageAdd, Toast.LENGTH_SHORT).show();
                        holder.imageView.setImageBitmap(bitmap);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(context, "" + exception.getMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "" + imageAdd, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
