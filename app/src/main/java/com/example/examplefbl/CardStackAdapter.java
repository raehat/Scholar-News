package com.example.examplefbl;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CardStackAdapter extends RecyclerView.Adapter<CardStackAdapter.ViewHolder> {
    private FirebaseStorage storage;
    private StorageReference storageReference;

    public CardStackAdapter(List<ItemModel> items) {
        this.items = items;
    }

    private List<ItemModel> items;

    public List<ItemModel> getItems() {
        return items;
    }

    public void setItems(List<ItemModel> items) {
        this.items = items;
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view= inflater.inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        /*holder.setData(items.get(position));*/
        holder.heading.setText(items.get(position).getHeading());
        holder.desc.setText(items.get(position).getDesc());

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference().child("images/" + items.get(position).getImage());
        File localFile = null;
        try {
            localFile = File.createTempFile(items.get(position).getImage(), "jpeg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        final File finalLocalFile = localFile;
        storageReference.getFile(localFile)
                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        Bitmap bitmap= BitmapFactory.decodeFile(finalLocalFile.getAbsolutePath());
                        holder.image.setImageBitmap(bitmap);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                /*Toast.makeText(context, "" + exception.getMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "" + imageAdd, Toast.LENGTH_SHORT).show();
                holder.progressBar.setVisibility(View.GONE);*/
            }
        });


        Picasso.get()
                .load(items.get(position).getImage())
                .fit()
                .centerCrop()
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView heading, desc;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            image= itemView.findViewById(R.id.item_image);
            heading= itemView.findViewById(R.id.item_heading);
            desc= itemView.findViewById(R.id.item_desc);
        }


        void setData(ItemModel data) {
            Picasso.get()
                    .load(data.getImage())
                    .fit()
                    .centerCrop()
                    .into(image);
            heading.setText(data.getHeading());
            desc.setText(data.getDesc());
        }
    }
}
