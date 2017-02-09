package com.nhancv.mosbymvp;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.nhancv.mosbymvp.model.GitHubService;
import com.nhancv.mosbymvp.model.Repo;

import java.util.List;

import bolts.Continuation;
import bolts.Task;
import bolts.TaskCompletionSource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nhancao on 9/26/16.
 */

public class MainPresenter extends MvpBasePresenter<MainView> {

    private GitHubService service;

    public MainPresenter() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(GitHubService.class);
    }

    /**
     * Get repository list by username
     *
     * @param username
     */
    public void getRepoList(String username) {
        taskGitHub(username)
                .continueWith(new Continuation<List<Repo>, Void>() {
                    @Override
                    public Void then(Task<List<Repo>> task) {
                        if (!task.isFaulted() && !task.isCancelled()) {
                            List<Repo> repoList = task.getResult();
                            if (isViewAttached()) {
                                getView().showRepoList(repoList);
                            }
                        }
                        return null;
                    }
                }, Task.UI_THREAD_EXECUTOR);

    }

    /**
     * Task fetch repo list on github by username
     *
     * @param username
     * @return repo list
     */
    private Task<List<Repo>> taskGitHub(String username) {

        Call<List<Repo>> body = service.listRepos(username);
        final TaskCompletionSource<List<Repo>> tcs = new TaskCompletionSource<>();
        body.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                tcs.setResult(response.body());
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                tcs.setError((Exception) t);
            }
        });
        return tcs.getTask();
    }

}
