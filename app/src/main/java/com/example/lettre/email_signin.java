package com.example.lettre;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class email_signin extends AppCompatActivity {

    TextInputLayout username, password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_signin);

        username = (TextInputLayout) findViewById(R.id.user_login);
        password = (TextInputLayout) findViewById(R.id.pass_login);

        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/art_brewery.ttf");
        Typeface type2 = Typeface.createFromAsset(getAssets(), "fonts/LemonMilk.otf");
        TextView login = (TextView) findViewById(R.id.login);
        TextView login2 = (TextView) findViewById(R.id.login_text);
        login.setTypeface(type);
        login2.setTypeface(type2);

        mAuth = FirebaseAuth.getInstance();
    }

    public void signinhere(View view) {

        String email = username.getEditText().getText().toString();
        String pass = password.getEditText().getText().toString();

        if(TextUtils.isEmpty(email)){
            username.setError("Email is required");
            return;
        }
        if(TextUtils.isEmpty(pass)){
            password.setError("Password is required");
            return;
        }
        if(pass.length() < 6){
            password.setError("Password must be atleast 6 characters long");
            return;
        }

        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(email_signin.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            username.getEditText().setText("");
                            password.getEditText().setText("");
                            Toast.makeText(email_signin.this, "Login successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), profile.class);
                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.
                            username.getEditText().setText("");
                            password.getEditText().setText("");
                            Toast.makeText(email_signin.this, "Login failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                });

    }
}