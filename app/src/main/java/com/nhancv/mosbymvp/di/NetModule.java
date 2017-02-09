package com.nhancv.mosbymvp.di;

import com.nhancv.mosbymvp.model.GitHubService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nhancao on 2/9/17.
 */
@Module
public class NetModule {

    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    GitHubService provideGitHubService() {
        return provideRetrofit().create(GitHubService.class);
    }
}
