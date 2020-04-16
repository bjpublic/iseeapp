package com.charlezz.arch.ui.user;

import javax.inject.Inject;
import javax.inject.Named;

import android.app.Application;

import com.charlezz.arch.data.UserService;
import com.charlezz.arch.data.entity.User;
import com.charlezz.arch.util.SingleLiveEvent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import timber.log.Timber;

public class UserViewModel extends AndroidViewModel {
    @NonNull
    private final UserService userService;
    @NonNull
    private final SingleLiveEvent<Throwable> errorEvent;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final MutableLiveData<User> liveItem = new MutableLiveData<>();
    private final LiveData<String> name = Transformations.map(liveItem, input -> input.getName());
    private final LiveData<String> email = Transformations.map(liveItem, input -> input.getEmail());
    private final LiveData<String> homepage = Transformations.map(liveItem, input -> input.getWebsite());
    private final LiveData<String> phone = Transformations.map(liveItem, input -> input.getPhone());
    private final LiveData<String> location = Transformations.map(liveItem, input -> input.getAddress().toString());


    private final MutableLiveData<Boolean> loading = new MutableLiveData<>(true);

    @Inject
    public UserViewModel(@NonNull Application application,
                         @NonNull UserService userService,
                         @Named("errorEvent") SingleLiveEvent<Throwable> errorEvent) {
        super(application);
        Timber.d("UserViewModel created");
        this.userService = userService;
        this.errorEvent = errorEvent;

    }

    public void loadUser(long userId) {
        compositeDisposable.add(userService.getUser(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess((item) -> loading.postValue(false))
                .subscribe(liveItem::setValue, errorEvent::setValue));
    }

    public MutableLiveData<Boolean> getLoading() {
        return loading;
    }

    public LiveData<String> getName() {
        return name;
    }

    public LiveData<String> getEmail() {
        return email;
    }

    public LiveData<String> getHomepage() {
        return homepage;
    }

    public LiveData<String> getPhone() {
        return phone;
    }

    public LiveData<String> getLocation() {
        return location;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }

}
