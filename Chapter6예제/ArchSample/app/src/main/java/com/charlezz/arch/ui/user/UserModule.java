package com.charlezz.arch.ui.user;

import android.content.Context;
import android.view.LayoutInflater;

import com.charlezz.arch.databinding.FragmentUserBinding;
import com.charlezz.arch.di.ActivityContext;
import com.charlezz.arch.di.FragmentScope;

import dagger.Module;
import dagger.Provides;

@Module
public class UserModule {
    @Provides
    @FragmentScope
    FragmentUserBinding provideBinding(@ActivityContext Context context){
        return FragmentUserBinding.inflate(LayoutInflater.from(context));
    }
}
