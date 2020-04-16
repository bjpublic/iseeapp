package com.charlezz.arch.ui.detail;

import com.charlezz.arch.data.entity.User;

public class PostDetailUserItem extends PostDetailItem {
    private User user;
    private EventListener eventListener;

    public PostDetailUserItem(User user, EventListener eventListener) {
        this.user = user;
        this.eventListener = eventListener;
    }

    @Override
    public Type getType() {
        return Type.USER;
    }

    public String getName() {
        return user.getName();
    }

    public long getUserId() {
        return user.getId();
    }

    public EventListener getEventListener() {
        return eventListener;
    }

    public interface EventListener {
        void onUserClick(long userId);
    }
}
