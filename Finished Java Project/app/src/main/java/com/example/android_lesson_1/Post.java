package com.example.android_lesson_1;

public class Post {

    private String title;
    private String content;
    private int likes;

    public Post() {
        // Required empty public constructor
    }

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
        this.likes = likes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
