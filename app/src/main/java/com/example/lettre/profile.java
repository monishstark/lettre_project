package com.example.lettre;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lettre.databinding.ActivityProfileBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class profile extends AppCompatActivity {
    TextView name, mail, date;
    Button logout;
    ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        FirebaseAuth auth;
        FirebaseDatabase database;
        FirebaseStorage storage;


        super.onCreate(savedInstanceState);
        binding= ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth= FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance();
        storage=  FirebaseStorage.getInstance();

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
        logout= findViewById(R.id.logout);

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(signInAccount!=null){
            name.setText(signInAccount.getDisplayName());
            mail.setText(signInAccount.getEmail());


        }
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
            }
        }
    }
}