package com.example.android_lesson_1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddPostsActivity extends AppCompatActivity {

    private EditText etPostTitle, etPostContent;
    private Button btnSavePost;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_posts);

        etPostTitle = findViewById(R.id.etPostTitle);
        etPostContent = findViewById(R.id.etPostContent);
        btnSavePost = findViewById(R.id.btnSavePost);
        db = FirebaseFirestore.getInstance();

        btnSavePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePostToFirestore();
            }
        });
    }

    private void savePostToFirestore() {
        String title = etPostTitle.getText().toString().trim();
        String content = etPostContent.getText().toString().trim();

        if (!title.isEmpty() && !content.isEmpty()) {
            Post post = new Post(title, content);
            db.collection("posts").add(post)
                    .addOnSuccessListener(documentReference -> {
                        // Post added successfully
                        finish(); // Close activity after adding post
                    })
                    .addOnFailureListener(e -> {
                        // Error adding post
                    });
        }
    }
}