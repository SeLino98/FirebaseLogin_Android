package com.example.firebaselogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.firebaselogin.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth; //파베 인증처리
    private DatabaseReference mDatabaseRef; //실시간 데베
    ActivityRegisterBinding activityRegisterBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRegisterBinding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(activityRegisterBinding.getRoot());

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("firebaselogin");

        LoginButtonClickListener loginClick = new LoginButtonClickListener();
        activityRegisterBinding.btnLogin.setOnClickListener(loginClick);

    }
    class LoginButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {

            String strEmail = activityRegisterBinding.tvEmail.getText().toString();
            String strPwd = activityRegisterBinding.tvPwd.getText().toString();

            //Firebase Auth 진행
            mFirebaseAuth.createUserWithEmailAndPassword(strEmail,strPwd).
                    addOnCompleteListener(RegisterActivity.this,
                            new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                               //가입성공 됐을 때
                                    if(task.isSuccessful()){
                                        FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser(); //현재 유저를 가져온다.
                                        UserAccount account = new UserAccount();
                                        account.setEmail(firebaseUser.getEmail());
                                        account.setPassword(strPwd);
                                        account.setIdToken(firebaseUser.getUid()); //유니크값

                                        //setValue : 데이터베이스에 인서트하는 것
                                        mDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).setValue(account);
                                        Toast.makeText(RegisterActivity.this, "회원가입 성공", Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(RegisterActivity.this, "회원가입 실패", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
        }

    }
}