package com.nhancv.mosbymvp;

import android.app.Application;

import com.nhancv.mosbymvp.di.AppComponent;
import com.nhancv.mosbymvp.di.AppModule;
import com.nhancv.mosbymvp.di.DaggerAppComponent;
import com.nhancv.mosbymvp.di.NetModule;

import org.androidannotations.annotations.EApplication;

/**
 * Created by nhancao on 2/9/17.
 */

@EApplication
public class MyApp extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

}
