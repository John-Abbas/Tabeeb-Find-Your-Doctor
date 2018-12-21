package com.example.johnabbas.tabeeb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class userInfo extends AppCompatActivity implements View.OnClickListener {

    private FirebaseUser user;
    private EditText etEmail,etName,etAge;
    private RadioGroup radioSex,radioUserType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        user = FirebaseAuth.getInstance().getCurrentUser();
        etEmail = (EditText) findViewById(R.id.etEmailId);
        etName = (EditText) findViewById(R.id.etName);
        etAge  = (EditText) findViewById(R.id.etAge);
        radioSex = (RadioGroup) findViewById(R.id.radioSex);
        radioUserType = (RadioGroup) findViewById(R.id.radioUserType);

        findViewById(R.id.btn_signUp).setOnClickListener(this);
        etEmail.setText(user.getEmail());
    }

    private void insertUserDetails(String userID){
        DatabaseReference userDetailRef = FirebaseDatabase.getInstance().getReference("UserDetails");

        userDetails userDet = new userDetails(etEmail.getText().toString(),etName.getText().toString()
                ,((RadioButton)findViewById(radioSex.getCheckedRadioButtonId())).getText().toString(),Integer.parseInt(etAge.getText().toString())
                ,((RadioButton)findViewById(radioUserType.getCheckedRadioButtonId())).getText().toString());
        userDetailRef.child(userID).setValue(userDet);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_signUp:
                if(validateInputFields()){
                    insertUserDetails(user.getUid());
                    Intent mainScreen = new Intent(userInfo.this,dashboard.class);
                    mainScreen.putExtra("UID",user.getUid());
                    startActivity(mainScreen);
                }
                break;

            default:
                break;
        }

    }

    private boolean validateInputFields(){
        if((etAge.getText().toString().isEmpty()) ||(etName.getText().toString().isEmpty())
                ||(etEmail.getText().toString().isEmpty())) {
            Toast.makeText(userInfo.this, "Fill all fields", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!isValidEmail(etEmail.getText())){
            Toast.makeText(userInfo.this, "Incorrect Email", Toast.LENGTH_SHORT).show();
            return false;
        }
        if((Integer.parseInt(etAge.getText().toString()) > 200) || (Integer.parseInt(etAge.getText().toString()) < 1)){
            Toast.makeText(userInfo.this, "Enter Age between 1 & 200", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
