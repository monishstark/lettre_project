package com.example.lettre;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.lettre.User;
import com.example.lettre.databinding.RowConversationBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class UsersAdapters extends RecyclerView.Adapter<UsersAdapters.UsersViewHolder> {
    Context context;
    ArrayList<User> users;

    public UsersAdapters(Context context,ArrayList<User> users){
        this.context=context;
        this.users=users;

    }
    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.row_conversation,parent,false);

        return new UsersViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(context);

        User user= users.get(position);
        /*holder.binding.cname.setText(signInAccount.getDisplayName());
        //Glide.with(context).load(user.getDp()).into(holder.binding.cdp);
        Glide.with(context).load(user.getDp())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.user__2_).into(holder.binding.cdp);*/
        /*holder.binding.ccountry.setText(user.getCountry());*/





        /*String senderUid = signInAccount.getId();

        String senderRoom = senderUid + user.getUid();

        FirebaseDatabase.getInstance().getReference()
                .child("chats")
                .child(senderRoom)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            String lastMsg = snapshot.child("lastMsg").getValue(String.class);
                            long time = snapshot.child("lastMsgTime").getValue(long.class);

                            holder.binding.recent.setText(lastMsg);
                        }
                        else{
                            holder.binding.recent.setText("Tap to Chat");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });*/
        holder.binding.cname.setText(user.getName());
        //Glide.with(context).load(user.getDp()).into(holder.binding.cdp);
        Glide.with(context).load(user.getDp())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.user__2_).into(holder.binding.cdp);
        holder.binding.recent.setText(user.getCountry());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(context);*/
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra("name", user.getName());

                intent.putExtra("uid",user.getUid());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }



    public class UsersViewHolder extends RecyclerView.ViewHolder{

        RowConversationBinding binding;

        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);
            binding= RowConversationBinding.bind(itemView);
        }
    }
}
