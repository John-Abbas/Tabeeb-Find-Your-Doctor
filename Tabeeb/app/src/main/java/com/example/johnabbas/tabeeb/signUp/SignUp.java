package com.example.johnabbas.tabeeb.signUp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.johnabbas.tabeeb.R;
import com.example.johnabbas.tabeeb.dashboard;
import com.example.johnabbas.tabeeb.userInfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private EditText etEmail,etPass,etConPass,etName,etAge;
    private RadioGroup radioSex,radioUserType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_appbar);
        TextView tvTitle = (TextView) getSupportActionBar().getCustomView().findViewById(R.id.mytext);
        tvTitle.setText("Sign Up");

        mAuth = FirebaseAuth.getInstance();
        etEmail = (EditText) findViewById(R.id.etEmailId);
        etPass = (EditText) findViewById(R.id.etPassword);
        etConPass = (EditText) findViewById(R.id.etConfirmPassword);
        etName = (EditText) findViewById(R.id.etName);
        etAge  = (EditText) findViewById(R.id.etAge);
        radioSex = (RadioGroup) findViewById(R.id.radioSex);
        radioUserType = (RadioGroup) findViewById(R.id.radioUserType);

        findViewById(R.id.btn_signUp).setOnClickListener(this);
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    public void signUp(View view) {
        if(!validateInputFields()){
            return;
        }
        mAuth.createUserWithEmailAndPassword(etEmail.getText().toString(),etPass.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();

                            String userID = user.getUid();
                            insertUserDetails(userID);

                            Intent mainScreen = new Intent(SignUp.this,dashboard.class);
                            mainScreen.putExtra("UID",userID);
                            startActivity(mainScreen);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Error : ", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUp.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }

    private void insertUserDetails(String userID){
        DatabaseReference userDetailRef = FirebaseDatabase.getInstance().getReference("UserDetails");

        userInfo userDet = new userInfo(etEmail.getText().toString(),etName.getText().toString()
                ,((RadioButton)findViewById(radioSex.getCheckedRadioButtonId())).getText().toString(),Integer.parseInt(etAge.getText().toString())
                ,((RadioButton)findViewById(radioUserType.getCheckedRadioButtonId())).getText().toString());
        userDetailRef.child(userID).setValue(userDet);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_signUp:
                signUp(v);
                break;

            default:
                break;
        }

    }

    private boolean validateInputFields(){
        if((etAge.getText().toString().isEmpty()) ||(etName.getText().toString().isEmpty())
            ||(etEmail.getText().toString().isEmpty())||(etConPass.getText().toString().isEmpty())||(etPass.getText().toString().isEmpty())) {
            Toast.makeText(SignUp.this, "Fill all fields", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!etPass.getText().toString().equals(etConPass.getText().toString())){
            Toast.makeText(SignUp.this, "Password and Confirm Password should be same", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!isValidEmail(etEmail.getText())){
            Toast.makeText(SignUp.this, "Incorrect Email", Toast.LENGTH_SHORT).show();
            return false;
        }
        if((Integer.parseInt(etAge.getText().toString()) > 200) || (Integer.parseInt(etAge.getText().toString()) < 1)){
            Toast.makeText(SignUp.this, "Enter Age between 1 & 200", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
