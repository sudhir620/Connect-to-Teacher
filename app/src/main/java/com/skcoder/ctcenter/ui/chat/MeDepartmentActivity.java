package com.skcoder.ctcenter.ui.chat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.skcoder.ctcenter.R;
import com.skcoder.ctcenter.ui.chat.adapters.CsAdapter;
import com.skcoder.ctcenter.ui.chat.models.CsModel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MeDepartmentActivity extends AppCompatActivity {
    RecyclerView meRecView;
    ArrayList<CsModel> list;
    CsAdapter adapter;

    ImageButton meSend;
    ImageView meImgVew;
    EditText meEditText;

    private Bitmap bitmap;

    String message, userName,uname;
    String downloadUrl = null;
    FirebaseUser fUser;
    DatabaseReference reference, uRef, mRef;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me_department);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.setTitle("ME Department");

        reference = FirebaseDatabase.getInstance().getReference().child("chat");
        uRef = FirebaseDatabase.getInstance().getReference().child("Users");
        mRef = FirebaseDatabase.getInstance().getReference().child("chat").child("ME_Department");
        fUser = FirebaseAuth.getInstance().getCurrentUser();
        storageReference = FirebaseStorage.getInstance().getReference();

        meRecView = findViewById(R.id.me_rec_view);
        meSend = findViewById(R.id.meSend);
        meImgVew = findViewById(R.id.meImgView);
        meEditText = findViewById(R.id.meEditTv);

        meRecView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        meRecView.setLayoutManager(linearLayoutManager);

        meImgVew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        meSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkValidity();
            }
        });

        getMessage();

    }

    private void readUserName() {
        uRef.child(fUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map<String, Object> data = (Map<String, Object>) dataSnapshot.getValue();
                userName = (String) data.get("username");
                sendMessge(userName);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "" + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openGallery() {
        Intent pickImage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickImage, 101);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101 && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            uploadImage();
        }
    }

    private void uploadImage() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] finalimg = baos.toByteArray();
        final StorageReference filePath;
        filePath = storageReference.child("Chats_img").child("ME_department").child(finalimg + "jpg");
        final UploadTask uploadTask = filePath.putBytes(finalimg);
        uploadTask.addOnCompleteListener(MeDepartmentActivity.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()) {
                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    downloadUrl = String.valueOf(uri);

                                    uRef.child(fUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            Map<String, Object> data1 = (Map<String, Object>) dataSnapshot.getValue();
                                            uname = (String) data1.get("username");

                                            String uniqueKey = reference.push().getKey();
                                            HashMap<String, Object> hashMap1 = new HashMap<>();
                                            hashMap1.put("sender", fUser.getUid());
                                            hashMap1.put("sender_name", uname);
                                            hashMap1.put("imgUrl", downloadUrl);

                                            reference.child("ME_Department").child(uniqueKey).setValue(hashMap1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    Toast.makeText(getApplicationContext(), "Photo send", Toast.LENGTH_SHORT).show();
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(getApplicationContext(), "Error : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {
                                            Toast.makeText(getApplicationContext(), "" + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            });
                        }
                    });
                } else {
                    Toast.makeText(MeDepartmentActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void checkValidity() {
        message = meEditText.getText().toString();
        if (message.isEmpty()) {
            meEditText.setError("Please type something");
            meEditText.requestFocus();
        } else {
            meEditText.setText("");
            readUserName();
        }
    }

    private void sendMessge(String userName) {
        String UniqueKey = reference.push().getKey();

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("sender", fUser.getUid());
        hashMap.put("sender_name", userName);
        hashMap.put("message", message);

        reference.child("ME_Department").child(UniqueKey).setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                meEditText.setText("");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error : " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getMessage() {
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    CsModel mydata = snapshot.getValue(CsModel.class);
                    list.add(mydata);
                }
                adapter = new CsAdapter(getApplicationContext(), list);
                meRecView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Error : " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}