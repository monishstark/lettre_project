package com.example.lettre;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lettre.databinding.ItemReceiveBinding;
import com.example.lettre.databinding.ItemSentBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import static androidx.core.content.ContextCompat.getSystemService;

public class MessagesAdapter extends RecyclerView.Adapter{

    Context context;
    ArrayList<Message> messages;
    final int ITEM_SENT= 0;
    final int ITEM_RECEIVE= 1;



    public MessagesAdapter(Context context,ArrayList<Message> messages){
        this.context= context;
        this.messages= messages;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType== ITEM_SENT){
            View view= LayoutInflater.from(context).inflate(R.layout.item_sent,parent,false);
            return new SentViewHolder(view);
        }
        else{
            View view= LayoutInflater.from(context).inflate(R.layout.item_receive,parent,false);
            return new ReceiverViewHolder(view);

        }
    }

    @Override
    public int getItemViewType(int position) {

        Message message= messages.get(position);
        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(context);
        if(signInAccount.getId().equals(message.getSenderId())) {
            return ITEM_SENT;
        }
        else {
            return ITEM_RECEIVE;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message message= messages.get(position);
        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(context);
        if (holder.getClass()== SentViewHolder.class){
            SentViewHolder viewHolder= (SentViewHolder)holder;
            viewHolder.binding.message.setText(message.getMessage());
            viewHolder.binding.senderName.setText(signInAccount.getDisplayName());



        }
        else {

            ReceiverViewHolder viewHolder= (ReceiverViewHolder)holder;
            viewHolder.binding.message.setText(message.getMessage());
            viewHolder.binding.senderName1.setText(message.getReceiverName());


        }

    }

    @Override
    public int getItemCount() {

        return messages.size();
    }

    public class SentViewHolder extends RecyclerView.ViewHolder{

        ItemSentBinding binding;

        public SentViewHolder(@NonNull View itemView) {
            super(itemView);
            binding= ItemSentBinding.bind(itemView);
        }
    }
    public class ReceiverViewHolder extends RecyclerView.ViewHolder{

        ItemReceiveBinding binding;

        public ReceiverViewHolder(@NonNull View itemView) {
            super(itemView);
            binding= ItemReceiveBinding.bind(itemView);

        }
    }
    /*private void addNotification(String name){
        NotificationCompat.Builder builder= new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.app_logo)
                .setContentTitle(name)
                .setContentText("NEW LETTRE");
        Intent intent= new Intent(context,MessagesAdapter.class);
        PendingIntent contentIntent= PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        NotificationManagerCompat manager = NotificationManagerCompat.from(context);
        manager.notify(0, builder.build());
    }*/
}
