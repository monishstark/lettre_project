package com.example.lettre;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.lettre.databinding.ActivityAddfriendBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class addfriend extends AppCompatActivity {

    ActivityAddfriendBinding binding;
    FirebaseDatabase database;
    ArrayList<user> users;
    UsersAdapters usersAdapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityAddfriendBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        database = FirebaseDatabase.getInstance();
        users = new ArrayList<>();
        usersAdapters = new UsersAdapters(this, users);
        binding.results.setAdapter(usersAdapters);


        database.getReference().child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                users.clear();
                ;
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {                               //gets data from database
                    user user = snapshot1.getValue(user.class);
                    users.add(user);
                }
                usersAdapters.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        binding.downmenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.profile:
                        Intent intent1=new Intent(getApplicationContext(),cprofile.class);
                        startActivity(intent1);
                        break;

                }
                return false;
            }
        });

    }
    public user getRandomElement(List<user> users ){
        Random random= new Random();
        return users.get(random.nextInt(users.size()));
    }







}