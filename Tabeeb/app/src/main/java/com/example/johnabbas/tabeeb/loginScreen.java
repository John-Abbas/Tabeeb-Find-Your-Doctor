package com.example.johnabbas.tabeeb;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class loginScreen extends AppCompatActivity implements OnClickListener {

    private static final String ERROR = "ERROR";
    GoogleSignInClient mGoogleSignInClient;
    static final int SIGN_IN_REQ = 1;

    private FirebaseAuth mAuth;

    private EditText etEmail,etPass,etConPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        FirebaseApp.initializeApp(this);

        etEmail = (EditText) findViewById(R.id.etEmailId);
        etPass = (EditText) findViewById(R.id.etPassword);
        etConPass = (EditText) findViewById(R.id.etConfirmPassword);
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.sign_in_button).setOnClickListener(this);
        findViewById(R.id.tvAskSignUp).setOnClickListener(this);
        findViewById(R.id.btn_signIn).setOnClickListener(this);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent,SIGN_IN_REQ);
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser  = mAuth.getCurrentUser();

        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        if(currentUser!= null) {
            launchNextAct(currentUser);
        }
        else {
            launchNextAct(account);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;

            case R.id.tvAskSignUp:
                Intent newAct = new Intent(this,SignUp.class);
                startActivity(newAct);
                break;

            case R.id.btn_signIn:
                signInUsingEmail();
                break;

                default:
                    // Do Nothing
                    break;
        }
    }

    private void signInUsingEmail() {
        mAuth.signInWithEmailAndPassword(etEmail.getText().toString(), etPass.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Message", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(loginScreen.this, "Signed In.",
                                    Toast.LENGTH_SHORT).show();
                            launchNextAct(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Error", "signInWithEmail:failure", task.getException());
                            Toast.makeText(loginScreen.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == SIGN_IN_REQ) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void firebaseAuthWithGoogle(final GoogleSignInAccount acct) {
        Log.d("Message", "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Message", "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            launchNextAct(acct);
                            //updateUI(user);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(ERROR, "signInWithCredential:failure", task.getException());
                            Toast.makeText(loginScreen.this,"Error : ", Toast.LENGTH_SHORT).show();
                            //Snackbar.make(findViewById(R.id.main_layout), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();

                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.

            //launchNextAct(account);

            firebaseAuthWithGoogle(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(ERROR, "signInResult:failed code=" + e.getStatusCode());

            //updateUI(null);
        }
    }

    private void launchNextAct(GoogleSignInAccount account){

        if(account != null) {
            Intent newAct = new Intent(this,dashboard.class);
            startActivity(newAct);
        }
        else{
            Toast.makeText(this,"Login First" ,Toast.LENGTH_SHORT).show();
        }
    }


    private void launchNextAct(FirebaseUser user){

        if(user != null) {
            Intent newAct = new Intent(this,dashboard.class);
            startActivity(newAct);
        }
        else{
            Toast.makeText(this,"Login First" ,Toast.LENGTH_SHORT).show();
        }
    }
}
