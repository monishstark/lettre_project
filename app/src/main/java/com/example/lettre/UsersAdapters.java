package com.example.lettre;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.lettre.databinding.RowConversationBinding;

import java.util.ArrayList;

public class UsersAdapters extends RecyclerView.Adapter<UsersAdapters.UsersViewHolder> {
    Context context;
    ArrayList<user> users;

    public UsersAdapters(Context context,ArrayList<user> users){
        this.context=context;
        this.users=users;

    }
    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {                            //row conversation binding
        View view= LayoutInflater.from(context).inflate(R.layout.row_conversation,parent,false);

        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {                                   // binding data
        user user= users.get(position);
        holder.binding.cname.setText(user.getName());
        //Glide.with(context).load(user.getDp()).into(holder.binding.cdp);
        Glide.with(context).load(user.getDp())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.user__2_).into(holder.binding.cdp);
        holder.binding.ccountry.setText(user.getCountry());

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
