package com.example.lettre;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import org.w3c.dom.Text;

public class login extends AppCompatActivity {

    private final static int RC_SIGN_IN = 123;
    private GoogleSignInClient mGoogleSignInClient;
    public Button verify;
    TextInputLayout username, password;
    private FirebaseAuth mAuth;

    @Override
    protected void onStart() {
        super.onStart();

        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/art_brewery.ttf");
        Typeface type2 = Typeface.createFromAsset(getAssets(), "fonts/JosefinSansSemiBold.ttf");
        Typeface type3 = Typeface.createFromAsset(getAssets(), "fonts/JosefinSansSemiBold.ttf");
        TextView login = (TextView) findViewById(R.id.login);
        TextView login2 = (TextView) findViewById(R.id.straight_login);
        TextView login3 = (TextView) findViewById(R.id.straight_login_2);
        login.setTypeface(type);
        login2.setTypeface(type2);
        login3.setTypeface(type3);

        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {                                                                      //if user is logged skips the profile step
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (TextInputLayout) findViewById(R.id.user);
        password = (TextInputLayout) findViewById(R.id.pass);

        mAuth = FirebaseAuth.getInstance();

        createRequest();
        findViewById(R.id.google_sigin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }

    private void createRequest() {
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(getApplicationContext(), profile.class);
                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(login.this, "ERROR try again", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }


    public void signuphere(View view) {

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

        mAuth = FirebaseAuth.getInstance();

        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            username.getEditText().setText("");
                            password.getEditText().setText("");
                            Toast.makeText(login.this, "Signup successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), profile.class);
                            intent.putExtra("email", mAuth.getCurrentUser().getEmail());
                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.
                            username.getEditText().setText("");
                            password.getEditText().setText("");
                            Toast.makeText(login.this, "Signup failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                });
    }

    public void gotosignin(View view) {
        startActivity(new Intent(login.this, email_signin.class));
    }

}