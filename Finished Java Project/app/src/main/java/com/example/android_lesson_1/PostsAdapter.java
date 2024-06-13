package com.example.android_lesson_1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostViewHolder> {

    private List<Post> postList;

    public PostsAdapter(List<Post> postList) {
        this.postList = postList;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_card, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = postList.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView tvPostTitle, tvPostContent;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.postImage);
            tvPostTitle = itemView.findViewById(R.id.tvPostTitle);
            tvPostContent = itemView.findViewById(R.id.tvPostContent);
        }

        public void bind(Post post) {

                imageView.setImageResource(R.drawable.book);


            tvPostTitle.setText(post.getTitle());
            tvPostContent.setText(post.getContent());
        }
    }
}