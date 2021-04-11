package adapter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examplefbl.R;
import com.example.examplefbl.datamodel;
import com.example.examplefbl.datamodel_userHome;
import com.example.examplefbl.datamodel_userSaved;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class RecyclerViewAdapter_usersaved extends RecyclerView.Adapter<RecyclerViewAdapter_usersaved.myviewholder>{
    ArrayList<datamodel_userSaved> dataholder_userSaved;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private FirebaseFirestore fstore;

    public RecyclerViewAdapter_usersaved(ArrayList<datamodel_userSaved> dataholder_userSaved) {
        this.dataholder_userSaved = dataholder_userSaved;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_usersaved,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {


        holder.header2.setText(dataholder_userSaved.get(position).getHeader());
        holder.desc2.setText(dataholder_userSaved.get(position).getDesc());
        fstore= FirebaseFirestore.getInstance();

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fstore.collection(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .document(dataholder_userSaved.get(position).getImage()).delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                            }
                        });

                dataholder_userSaved.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,dataholder_userSaved.size());
            }
        });

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference().child("images/" + dataholder_userSaved.get(position).getImage());
        File localFile = null;
        try {
            localFile = File.createTempFile(dataholder_userSaved.get(position).getImage(), "jpeg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        final File finalLocalFile = localFile;
        storageReference.getFile(localFile)
                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        Bitmap bitmap= BitmapFactory.decodeFile(finalLocalFile.getAbsolutePath());
                        holder.img3.setImageBitmap(bitmap);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                /*Toast.makeText(context, "" + exception.getMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "" + imageAdd, Toast.LENGTH_SHORT).show();
                holder.progressBar.setVisibility(View.GONE);*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataholder_userSaved.size();
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        ImageView img3, delete;
        TextView header2,desc2;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            img3=itemView.findViewById(R.id.icon_img3);
            header2=itemView.findViewById(R.id.header2);
            desc2=itemView.findViewById(R.id.description2);
            delete= itemView.findViewById(R.id.delete);
        }
    }
}

