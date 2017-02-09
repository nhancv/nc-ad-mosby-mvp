package com.nhancv.mosbymvp;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.nhancv.mosbymvp.model.Repo;

import java.util.List;

/**
 * Created by nhancao on 9/26/16.
 */

public interface MainView extends MvpView {
    /**
     * Show repo list on ui
     *
     * @param repoList
     */
    void showRepoList(List<Repo> repoList);

}
