package com.example.lettre;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.lettre.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    FirebaseDatabase database;
    ArrayList<User> users;
    UsersAdapters usersAdapters;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        database= FirebaseDatabase.getInstance();
        users= new ArrayList<>();

        usersAdapters= new UsersAdapters(this,users);
        binding.recyclerview.setAdapter(usersAdapters);


        database.getReference().child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                users.clear();;
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {                               //gets data from database
                    User user = snapshot1.getValue(User.class);
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
                    case R.id.addfriend:
                        Intent intent = new Intent(getApplicationContext(), addfriend.class);
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

}