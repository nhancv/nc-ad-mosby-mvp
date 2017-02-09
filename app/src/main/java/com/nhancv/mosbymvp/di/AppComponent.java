package com.nhancv.mosbymvp.di;

import com.nhancv.mosbymvp.MyApp;
import com.nhancv.mosbymvp.model.GitHubService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by nhancao on 2/9/17.
 */
@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface AppComponent {

    MyApp app();

    GitHubService gitHubService();

}
