package com.nhancv.mosbymvp;

import com.nhancv.mosbymvp.di.AppComponent;
import com.nhancv.mosbymvp.di.PerActivity;

import dagger.Component;

/**
 * Created by nhancao on 2/9/17.
 */
@PerActivity
@Component(
        dependencies = AppComponent.class)
public interface MainComponent {
    MainPresenter presenter();

    void inject(MainActivity activity);
}
