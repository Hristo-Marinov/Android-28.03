package com.example.android_lesson_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;

public class AllPostsActivity extends AppCompatActivity {

    private Button btnAddPost;
    private Button btnRefresh;
    private Button btnProfile;
    private RecyclerView recyclerViewPosts;
    private List<Post> postList;
    private PostsAdapter postsAdapter;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_posts);

        btnAddPost = findViewById(R.id.btnAddPost);
        btnRefresh = findViewById(R.id.btnRefresh);
        btnProfile = findViewById(R.id.btnProfile);
        recyclerViewPosts = findViewById(R.id.recyclerViewPosts);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        recyclerViewPosts.setLayoutManager(new LinearLayoutManager(this));
        postList = new ArrayList<>();
        postsAdapter = new PostsAdapter(postList);
        recyclerViewPosts.setAdapter(postsAdapter);

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllPostsActivity.this, ProfileActivity.class));
            }
        });

        btnAddPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllPostsActivity.this, AddPostsActivity.class));
            }
        });

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPostsFromFirestore();
            }
        });

        loadPostsFromFirestore();
    }

    private void loadPostsFromFirestore() {
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            db.collection("posts")
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            postList.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Post post = document.toObject(Post.class);
                                postList.add(post);
                            }
                            postsAdapter.notifyDataSetChanged();
                        } else {
                            // Handle errors
                        }
                    });
        }
    }
}