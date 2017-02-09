package com.nhancv.mosbymvp;

import android.support.annotation.NonNull;
import android.widget.ListView;

import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;
import com.nhancv.mosbymvp.model.Repo;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EActivity(R.layout.activity_main)
public class MainActivity extends MvpActivity<MainView, MainPresenter> implements MainView {

    @ViewById(R.id.activity_main_lv_user_repo)
    ListView lvUserRepo;
    private QuickAdapter<Repo> adapter;

    @AfterViews
    void init() {
        //setup adapter
        adapter = new QuickAdapter<Repo>(this, R.layout.item_repo) {
            @Override
            protected void convert(BaseAdapterHelper helper, Repo item) {
                helper.setText(R.id.item_repo_tv_id, item.getId());
            }
        };
        //assign adapter to list
        lvUserRepo.setAdapter(adapter);
    }

    @NonNull
    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Click(R.id.activity_main_bt_get_list)
    void btGetListClick() {
        presenter.getRepoList("nhancv");
    }

    @Override
    public void showRepoList(List<Repo> repoList) {
        adapter.replaceAll(repoList);
    }
}
