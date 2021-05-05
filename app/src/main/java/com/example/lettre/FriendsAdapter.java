package com.example.lettre;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.lettre.databinding.FriendsListBinding;
import com.example.lettre.databinding.RowConversationBinding;

import java.util.ArrayList;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.UsersViewHolder>{

    Context context;
    ArrayList<User> users;

    public FriendsAdapter(Context context,ArrayList<User> users){
        this.context=context;
        this.users=users;
    }


    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.friends_list,parent,false);

        return new UsersViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {

        User user= users.get(position);
        holder.binding.cname3.setText(user.getName());
        Glide.with(context).load(user.getDp())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.user__2_).into(holder.binding.cdp);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,details.class);
                intent.putExtra("name", user.getName());
                intent.putExtra("mail", user.getMail());
                intent.putExtra("country", user.getCountry());
                intent.putExtra("lan",user.getLan());
                intent.putExtra("longt",user.getLongt());
                intent.putExtra("dp", user.getDp());
                context.startActivity(intent);



            }
        });



    }

    @Override
    public int getItemCount() {
        return  users.size();
    }

    public class UsersViewHolder extends RecyclerView.ViewHolder{

        FriendsListBinding binding;

        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);
            binding= FriendsListBinding.bind(itemView);
        }
    }

}
