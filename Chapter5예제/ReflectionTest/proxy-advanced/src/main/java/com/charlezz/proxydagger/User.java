package com.charlezz.proxydagger;

import androidx.annotation.NonNull;

public class User {

    private String name;
    private UserClickListener listener;

    public User(String name, @NonNull UserClickListener listener){
        this.name = name;
        this.listener = listener;
    }

    public String getName() {
        return name;
    }


    public void clickUser(){
        listener.onUserClick(this);
    }


}
