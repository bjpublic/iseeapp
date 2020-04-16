package com.charlezz.arch.data.entity;

public class Comment {
    private long postId;
    private long id;
    private String name;
    private String email;
    private String body;

    public long getPostId() {
        return postId;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getBody() {
        return body;
    }
}
