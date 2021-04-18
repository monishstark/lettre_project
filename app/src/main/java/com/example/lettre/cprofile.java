package com.example.lettre;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
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

public class cprofile extends AppCompatActivity {

    TextView name, mail, id;
    EditText country;
    ImageView dp;
    ActivityCprofileBinding binding;
    Uri selctedImage;
    String uid, uname, umail, personPhotoUrl, ucountry;
    FirebaseAuth auth;
    FirebaseDatabase database;
    FirebaseStorage storage;
    DatabaseReference reff;
    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityCprofileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        reff = database.getReference().child("users");
        dialog = new ProgressDialog(cprofile.this);

        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/LemonMilk.otf");
        TextView login = (TextView) findViewById(R.id.profile);
        login.setTypeface(type);

        name = findViewById(R.id.name);
        mail = findViewById(R.id.mail);
        dp = findViewById(R.id.dp);
        country = findViewById(R.id.country);

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        name.setText(signInAccount.getDisplayName());
        mail.setText(signInAccount.getEmail());
        dp.setImageURI(signInAccount.getPhotoUrl());
        personPhotoUrl = signInAccount.getPhotoUrl().toString();
        Glide.with(getApplicationContext()).load(personPhotoUrl)
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(dp);
        uname = signInAccount.getDisplayName();
        umail = signInAccount.getEmail();
        uid = signInAccount.getId();
        ucountry = country.getText().toString();


        binding.downmenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.addfriend:
                        Intent intent1=new Intent(getApplicationContext(),addfriend.class);
                        startActivity(intent1);
                        break;

                }
                return false;
            }
        });


    }

}