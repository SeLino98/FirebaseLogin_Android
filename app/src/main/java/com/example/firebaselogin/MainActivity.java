package com.example.firebaselogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.firebaselogin.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;

    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        mFirebaseAuth =FirebaseAuth.getInstance();

    }

    class LogoutButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            mFirebaseAuth.signOut(); //로그아웃 메서드
            Toast.makeText(MainActivity.this, "로그아웃 했습니다. ", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    class DeleteUserButtonClickListener implements  View.OnClickListener{
        @Override //탈퇴처리
        public void onClick(View view) {

            mFirebaseAuth.getCurrentUser().delete();

        }

    }

}