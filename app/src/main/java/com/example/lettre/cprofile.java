package com.example.lettre;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.lettre.databinding.ActivityCprofileBinding;
import com.example.lettre.databinding.ActivityProfileBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class cprofile extends AppCompatActivity{

    TextView name, mail,id;
    EditText country;
    ImageView dp;
    Button logout, Continue;
    ActivityCprofileBinding binding;
    Uri selctedImage;
    String uid,uname,umail,personPhotoUrl,ucountry;
    FirebaseAuth auth;
    FirebaseDatabase database;
    FirebaseStorage storage;
    DatabaseReference reff;
    ProgressDialog dialog;
    ImageButton home, search, profile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCprofileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        home = findViewById(R.id.home_button);
        search = findViewById(R.id.search_button);
        profile = findViewById(R.id.profile_button);

        binding.dp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 45);

            }
        });

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (signInAccount != null) {
            binding.name.setText(signInAccount.getDisplayName());
            binding.mail.setText(signInAccount.getEmail());
            binding.dp.setImageURI(signInAccount.getPhotoUrl());
            personPhotoUrl = signInAccount.getPhotoUrl().toString();
            Glide.with(getApplicationContext()).load(personPhotoUrl)
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(binding.dp);
            binding.country.setText("india");

        }

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), addfriend.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!= null){
            if (data.getData()!= null){
                binding.dp.setImageURI(data.getData());
                selctedImage= data.getData();

            }
        }
    }
}




