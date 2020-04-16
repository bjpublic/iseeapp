package com.charlezz.arch.ui.detail;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import android.app.Application;

import com.charlezz.arch.data.CommentService;
import com.charlezz.arch.data.UserService;
import com.charlezz.arch.data.entity.Comment;
import com.charlezz.arch.data.entity.Post;
import com.charlezz.arch.util.SingleLiveEvent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import timber.log.Timber;

public class PostDetailViewModel
        extends AndroidViewModel
        implements PostDetailUserItem.EventListener{


    private final MutableLiveData<List<PostDetailItem>> liveItems = new MutableLiveData<>();
    private final UserService userService;
    private final CommentService commentService;
    @NonNull
    private final SingleLiveEvent<Throwable> errorEvent;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private final MutableLiveData<Boolean> loading = new MutableLiveData<>(true);

    private final SingleLiveEvent<Long> userClickEvent = new SingleLiveEvent<>();

    @Inject
    public PostDetailViewModel(@NonNull Application application,
                               UserService userService,
                               CommentService commentService,
                               @Named("errorEvent") SingleLiveEvent<Throwable> errorEvent) {
        super(application);
        Timber.d("PostDetailViewModel created");
        this.userService = userService;
        this.commentService = commentService;
        this.errorEvent = errorEvent;

    }

    public void load(Post post) {
        compositeDisposable.add(Single.zip(userService.getUser(post.getUserId()),
                Single.just(post),
                commentService.getComments(post.getId()),
                (user, p, comments) -> {
                    List<PostDetailItem> list = new ArrayList<>();
                    list.add(new PostDetailUserItem(user, this));
                    list.add(new PostDetailPostItem(p));
                    for (Comment comment : comments) {
                        list.add(new PostDetailCommentItem(comment));
                    }
                    return list;
                })
                .retry(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(postDetailItems -> loading.postValue(false))
                .subscribe(liveItems::setValue, errorEvent::setValue)
        );

    }

    public MutableLiveData<List<PostDetailItem>> getLiveItems() {
        return liveItems;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Timber.d("onCleared");
        this.compositeDisposable.dispose();
    }

    public MutableLiveData<Boolean> getLoading() {
        return loading;
    }

    public SingleLiveEvent<Long> getUserClickEvent() {
        return userClickEvent;
    }

    @Override
    public void onUserClick(long userId) {
        userClickEvent.setValue(userId);
    }
}
