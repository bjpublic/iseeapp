package com.charlezz.proxydagger;

import androidx.databinding.DataBindingUtil;
import android.widget.Toast;

import com.charlezz.proxydagger.databinding.ActivityUserBinding;
import com.charlezz.proxydagger.di.ActivityScope;

import java.lang.reflect.Proxy;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class UserModule {
    @ActivityScope
    @Provides
    static ActivityUserBinding provideBinding(UserActivity activity){
        return DataBindingUtil.setContentView(activity, R.layout.activity_user);
    }

    @ActivityScope
    @Provides
    static UserAdapter provideAdapter(){
        return new UserAdapter();
    }

    @ActivityScope
    @Provides
    static UserViewModel provideViewModel(UserClickListener listener){
        return new UserViewModel(listener);
    }

    @ActivityScope
    @Provides
    static UserInvocationHandler provideInvocationHandler (UserActivity activity){
        return new UserInvocationHandler(activity);
    }

    @ActivityScope
    @Provides
    static UserClickListener provideNavigator(UserInvocationHandler invocationHandler){
        return (UserClickListener) Proxy.newProxyInstance(
                UserClickListener.class.getClassLoader(),
                new Class[]{UserClickListener.class},
                invocationHandler
        );
    }

    @ActivityScope
    @Provides
    static Toast provideToast(UserActivity activity){
        return Toast.makeText(activity, "", Toast.LENGTH_SHORT);
    }

}
