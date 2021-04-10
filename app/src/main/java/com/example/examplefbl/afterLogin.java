package com.example.examplefbl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class afterLogin extends AppCompatActivity {

    private List<String> imageViewUrls;

    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000; // time in milliseconds between successive task executions

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afterlogin);

        imageViewUrls= Arrays.asList(new String[]{"ashishTestCase.PNG",
                "bgfinal.png"});


        final ViewPager viewPager = findViewById(R.id.view_pager5);
        ViewPagerAdapter adapter = new ViewPagerAdapter(this, imageViewUrls);
        viewPager.setAdapter(adapter);

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                viewPager.post(new Runnable() {

                    @Override
                    public void run() {
                        viewPager.setCurrentItem((viewPager.getCurrentItem() + 1) % imageViewUrls.size());
                    }
                });
            }
        };
        timer = new Timer();
        timer.schedule(timerTask, 3000, 3000);

        /*Toast.makeText(getApplicationContext(), "Welcome!!!", Toast.LENGTH_SHORT).show();

        logout= (Button) findViewById(R.id.logout);
        mAuth= FirebaseAuth.getInstance();
        imageView= (ImageView) findViewById(R.id.imageView);
        next= (Button) findViewById(R.id.next);
        storage= FirebaseStorage.getInstance();
        storageReference= storage.getReference();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(afterLogin.this, RetriveImage.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(afterLogin.this).setTitle("Delete Account?")
                        .setMessage("You really want to delete this account?")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent= new Intent(getApplicationContext(), loginOrRegister.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("no", null).show();
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePicture();
            }
        });*/

        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null)
        {
            imageUri= data.getData();
            imageView.setImageURI(imageUri);
            uploadPicture();
        }
    }

    private void uploadPicture() {

        final ProgressDialog pd= new ProgressDialog(this);
        pd.setTitle("Image is being uploaded!");
        pd.show();
        final String uid= UUID.randomUUID().toString();
        StorageReference riversRef = storageReference.child("images/" + uid);

        riversRef.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(), "Upload Succesful", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(), "fuck you!", Toast.LENGTH_LONG).show();
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
        Intent intent= new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }*/
    public void onDestroy() {
        timer.cancel();
        super.onDestroy();
    }
}