package com.charlezz.arch.ui.post;

import com.charlezz.arch.data.entity.Post;

import androidx.annotation.NonNull;

public class PostItem {
    @NonNull
    private final Post post;
    @NonNull
    private final EventListener eventListener;

    public PostItem(@NonNull Post post, EventListener eventListener) {
        this.post = post;
        this.eventListener = eventListener;
    }

    @NonNull
    public Post getPost() {
        return post;
    }

    public String getTitle() {
        return post.getTitle();
    }

    @NonNull
    public EventListener getEventListener() {
        return eventListener;
    }

    public interface EventListener {
        void onPostClick(PostItem postItem);
    }
}
