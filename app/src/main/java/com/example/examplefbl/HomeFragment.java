package com.example.examplefbl;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.common.net.InternetDomainName;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static android.app.Activity.RESULT_OK;
import static android.os.ParcelFileDescriptor.MODE_APPEND;
import static com.example.examplefbl.EditorLogin.user;

public class HomeFragment extends Fragment {
    private EditText article;
    private Button submitArticle;
    private FirebaseFirestore fstore;
    private EditText heading;
    private ImageView imageView3;
    private Uri imageUri;
    private boolean flag=false;
    private StorageReference storageReference;
    private FirebaseStorage storage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home2,container,false);


        @SuppressLint("WrongConstant") SharedPreferences sh
                = getActivity().getSharedPreferences("MySharedPref", MODE_APPEND);
        final String userr = sh.getString("username", "");

        Toast.makeText(getContext(), "Scroll down for more!", Toast.LENGTH_SHORT).show();
        article= view.findViewById(R.id.article);
        submitArticle= view.findViewById(R.id.submit_article);
        fstore= FirebaseFirestore.getInstance();
        heading= view.findViewById(R.id.heading);
        imageView3= view.findViewById(R.id.imageView3);


        storage= FirebaseStorage.getInstance();
        storageReference= storage.getReference();

        submitArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp= new Random().nextInt(999999);
                DocumentReference documentReference1= fstore.collection("articles").document(String.valueOf(temp));
                DocumentReference documentReference= fstore.collection("articles" + userr).document(String.valueOf(temp));
                Map<String, Object> object= new HashMap<>();
                object.put("article", article.getText().toString());
                object.put("heading", heading.getText().toString());
                if (flag){
                    uploadPicture(temp);
                    object.put("imageCode", String.valueOf(temp));
                }


                documentReference1.set(object);
                documentReference.set(object);
            }
        });

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePicture();
            }
        });
     
        return view;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                imageUri= result.getUri();
                imageView3.setImageURI(imageUri);
                flag=true;
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

    public void uploadPicture(int temp) {
        final ProgressDialog pd= new ProgressDialog(getContext());
        pd.setTitle("Image is being uploaded!");
        pd.show();
        StorageReference riversRef = storageReference.child("images/" + String.valueOf(temp));

        riversRef.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        pd.dismiss();
                        Toast.makeText(getContext(), "Upload Succesful", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        pd.dismiss();
                        Toast.makeText(getContext(), "" + "Please make sure that the " +
                                "image is less than 1024 KB/ 1 MB", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double progressPercent= (100.00 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                        pd.setMessage("Percentage: " + (int) progressPercent + "%");
                    }
                });
    }

    private void choosePicture() {
        CropImage.activity()
                .setAspectRatio(414, 736)
                .start(getContext(), this);
    }
}
