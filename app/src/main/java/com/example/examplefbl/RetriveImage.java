package com.example.examplefbl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.protobuf.DescriptorProtos;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class RetriveImage extends AppCompatActivity {
    FirebaseStorage storage;
    StorageReference storageReference;
    ImageView imageView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrive_image);

        final ProgressDialog pd= new ProgressDialog(this);

        imageView3 = findViewById(R.id.imageView3);

        File localFile = null;
        try {
            storage = FirebaseStorage.getInstance();
            storageReference = storage.getReference().child("images/03a24260-4933-4636-9b2b-7978925ff528");
            localFile = File.createTempFile("03a24260-4933-4636-9b2b-7978925ff528", "jpeg");
            final File finalLocalFile = localFile;
            storageReference.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(getApplicationContext(), "l", Toast.LENGTH_LONG).show();
                            pd.dismiss();
                            Bitmap bitmap= BitmapFactory.decodeFile(finalLocalFile.getAbsolutePath());
                            imageView3.setImageBitmap(bitmap);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    pd.dismiss();
                    Toast.makeText(getApplicationContext(), "dd" + exception.getMessage(), Toast.LENGTH_LONG).show();
                }
            }).addOnProgressListener(new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onProgress(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    float percent= (float) (100.00 * taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                    pd.setMessage("Percentage: " + percent + "%" );
                    pd.show();

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}