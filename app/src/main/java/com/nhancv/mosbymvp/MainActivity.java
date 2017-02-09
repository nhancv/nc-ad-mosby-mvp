package com.nhancv.mosbymvp;

import android.support.annotation.NonNull;
import android.widget.ListView;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;
import com.nhancv.mosbymvp.model.Repo;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import javax.inject.Inject;

@EActivity(R.layout.activity_main)
public class MainActivity extends MvpActivity<MainView, MainPresenter> implements MainView {

    @ViewById(R.id.activity_main_lv_user_repo)
    ListView lvUserRepo;
    @App
    MyApp application;
    @Inject
    MainPresenter presenter;

    private QuickAdapter<Repo> adapter;

    @AfterInject
    void inject() {
        DaggerMainComponent.builder()
                .appComponent(application.getAppComponent())
                .build()
                .inject(this);
    }

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
        MainService_.intent(getApplication()).start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MainService.getBus().register(this);
    }

    @Override
    protected void onPause() {
        MainService.getBus().unregister(this);
        super.onPause();
    }

    @NonNull
    @Override
    public MainPresenter createPresenter() {
        return presenter;
    }

    @Subscribe
    public void eventSubscribe(String msg) {
        Toast.makeText(application, msg, Toast.LENGTH_SHORT).show();
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
