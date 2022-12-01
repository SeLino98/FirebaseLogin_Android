package com.example.firebaselogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.firebaselogin.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding activityLoginBinding;
    private FirebaseAuth mFirebaseAuth; //파베 인증처리
    private DatabaseReference mDatabaseRef; //실시간 데베


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(activityLoginBinding.getRoot());

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("firebaselogin");


        LoginButtonClickListener loginClick = new LoginButtonClickListener();
        activityLoginBinding.btnLogin.setOnClickListener(loginClick);

        RegButtonClickListener regClick = new RegButtonClickListener();
        activityLoginBinding.btnRegister.setOnClickListener(regClick);

    }

    class LoginButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            String strEmail = activityLoginBinding.tvEmail.getText().toString();
            String strPwd = activityLoginBinding.tvPwd.getText().toString();

            mFirebaseAuth.signInWithEmailAndPassword(strEmail,strPwd).
                    addOnCompleteListener(LoginActivity.this,
                            new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        //로그인 성공
                                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                        startActivity(intent);

                                        //세션유지값을 위한 Uid??ㅃ?ㅉㄸ?ㅉㅃㄷㄷ
                                       finish(); //현재 액티비티 파괴

                                    } else{
                                        //로그인 실패
                                        Toast.makeText(LoginActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
        }
    }
    class RegButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
            startActivity(intent);
            finish();
        }
    }


}