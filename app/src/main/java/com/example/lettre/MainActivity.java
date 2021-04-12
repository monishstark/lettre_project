package com.example.lettre;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;

import com.example.lettre.databinding.ActivityMainBinding;
import com.example.lettre.databinding.ActivityProfileBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    FirebaseDatabase database;
    ArrayList<user> users;
    UsersAdapters usersAdapters;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        database= FirebaseDatabase.getInstance();
        users= new ArrayList<>();
        usersAdapters= new UsersAdapters(this,users);
        binding.RecyclerView.setAdapter(usersAdapters);

        database.getReference().child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                users.clear();;
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
    }

}