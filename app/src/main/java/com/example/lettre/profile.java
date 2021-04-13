package com.example.lettre;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import com.example.lettre.databinding.ActivityProfileBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class profile extends AppCompatActivity {
    TextView name, mail,id;
    EditText country;
    ImageView dp;
    Button logout, Continue;
    ActivityProfileBinding binding;
    Uri selctedImage;
    String uid,uname,umail,personPhotoUrl,ucountry;
    FirebaseAuth auth;
    FirebaseDatabase database;
    FirebaseStorage storage;
    DatabaseReference reff;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding= ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth= FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance();
        storage=  FirebaseStorage.getInstance();
        reff = database.getReference().child("users");
        dialog = new ProgressDialog(profile.this);

        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/LemonMilk.otf");
        TextView login = (TextView) findViewById(R.id.profile);
        login.setTypeface(type);

        binding.dp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,45);

            }
        });




        name= findViewById(R.id.name);
        mail= findViewById(R.id.mail);
        dp= findViewById(R.id.dp);
        country= findViewById(R.id.country);
        Continue= findViewById(R.id.Continue);
        logout= findViewById(R.id.logout);

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(signInAccount!=null){
            name.setText(signInAccount.getDisplayName());
            mail.setText(signInAccount.getEmail());
            dp.setImageURI(signInAccount.getPhotoUrl());
            String personPhotoUrl = signInAccount.getPhotoUrl().toString();
            Glide.with(getApplicationContext()).load(personPhotoUrl)
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(dp);
            uname = signInAccount.getDisplayName();
            umail = signInAccount.getEmail();
            uid =  signInAccount.getId();
            ucountry= country.getText().toString();










        }

        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setMessage("LOADING.....");
                dialog.show();
                user User = new user(uid,uname,umail,personPhotoUrl,ucountry);
                reff.push().setValue(User);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);





            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), login.class);
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