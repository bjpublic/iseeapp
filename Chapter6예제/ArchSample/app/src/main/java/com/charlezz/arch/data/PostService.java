package com.charlezz.arch.data;

import java.util.List;

import com.charlezz.arch.data.entity.Post;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface PostService {
    @GET("/posts")
    Single<List<Post>> getPosts();
}
