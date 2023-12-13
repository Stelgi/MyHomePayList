package com.example.myhomepaylist;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button registerBtn;
    private EditText login, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        mAuth = FirebaseAuth.getInstance();
        registerBtn = findViewById(R.id.registerBtn);
        login = findViewById(R.id.loginEditText);
        pass = findViewById(R.id.passwordEditText);

//        registerBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mAuth.signInWithEmailAndPassword(login.getText().toString(), pass.getText().toString())
//                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                System.out.println("");
//                            }
//                        })
//            }
//        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.createUserWithEmailAndPassword(login.getText().toString(), pass.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    FirebaseUser fireBaseUser = mAuth.getCurrentUser();
                                    System.out.println(fireBaseUser.getEmail());
                                    System.out.println(fireBaseUser.getUid());

                                }else{
                                    Log.w(TAG, "cr", task.getException());
                                }
                            }
                        });
            }
        });
    }
}