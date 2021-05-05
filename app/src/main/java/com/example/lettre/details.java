package com.example.lettre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.lettre.databinding.ActivityDetailsBinding;

public class details extends AppCompatActivity {

    ActivityDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String name= getIntent().getStringExtra("name");
        String mail= getIntent().getStringExtra("mail");
        String country= getIntent().getStringExtra("country");
        double lan= getIntent().getDoubleExtra("lan",0);
        double longt= getIntent().getDoubleExtra("longt",0);
        String dp=  getIntent().getStringExtra("dp");

        binding.name.setText(name);
        binding.mail.setText(mail);
        binding.country.setText(country);
        Glide.with(this).load(dp)
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.user__2_).into(binding.dp);

       binding.slocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),Frlocation.class);
                intent.putExtra("lan",lan);
                intent.putExtra("longt",longt);
                startActivity(intent);

            }
        });










        getSupportActionBar().setTitle("PROFILE");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

}