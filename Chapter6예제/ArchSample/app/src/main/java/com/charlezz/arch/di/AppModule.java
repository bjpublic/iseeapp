package com.charlezz.arch.di;

import javax.inject.Named;
import javax.inject.Singleton;

import android.app.Application;
import android.content.Context;

import com.charlezz.arch.App;
import com.charlezz.arch.util.SingleLiveEvent;

import dagger.Module;
import dagger.Provides;

@Module(includes = {
        ViewModelModule.class,
        RetrofitModule.class
})
public class AppModule {

    @Provides
    @Singleton
    Application provideApp(App app) {
        return app;
    }

    @Provides
    @Singleton
    @ApplicationContext
    Context provideContext(App app) {
        return app;
    }

    //앱의 오류 이벤트를 처리하는 SingleLiveEvent
    @Singleton
    @Provides
    @Named("errorEvent")
    SingleLiveEvent<Throwable> provideErrorEvent(){
        return new SingleLiveEvent<>();
    }

}