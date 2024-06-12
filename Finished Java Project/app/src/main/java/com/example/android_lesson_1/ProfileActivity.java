package com.example.android_lesson_1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {

    private TextView textViewEmailValue, textViewPasswordValue;

    private Button btnPosts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Initialize TextViews
        textViewEmailValue = findViewById(R.id.textViewEmailValue);
        textViewPasswordValue = findViewById(R.id.textViewPasswordValue);
        btnPosts = findViewById(R.id.btnPosts);

        // Retrieve current user from Firebase Authentication
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {

            String userEmail = currentUser.getEmail();
            textViewEmailValue.setText(userEmail);


            textViewPasswordValue.setText("********");
        }

        btnPosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, AllPostsActivity.class));
            }
        });
    }


}