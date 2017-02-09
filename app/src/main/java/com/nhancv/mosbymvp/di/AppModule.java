package com.nhancv.mosbymvp.di;

import com.nhancv.mosbymvp.MyApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by nhancao on 2/9/17.
 */
@Module
public class AppModule {
    private MyApp application;

    public AppModule(MyApp application) {
        this.application = application;
    }

    @Provides
    @Singleton
    MyApp provideApplication() {
        return application;
    }
}
