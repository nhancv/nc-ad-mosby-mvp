package com.nhancv.mosbymvp;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.nhancv.mosbymvp.presenter.HelloWorldPresenter;
import com.nhancv.mosbymvp.view.HelloWorldView;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends MvpActivity<HelloWorldView, HelloWorldPresenter> implements HelloWorldView {

    @ViewById(R.id.activity_main_tv_greeting)
    TextView activity_main_tv_greeting;

    @NonNull
    @Override
    public HelloWorldPresenter createPresenter() {
        return new HelloWorldPresenter();
    }

    @Click(R.id.activity_main_btn_get_hello)
    public void activity_main_btn_get_hello_on_click() {
        presenter.greetHello();
    }

    @Click(R.id.activity_main_btn_get_goodbye)
    public void activity_main_btn_get_goodbye_on_click() {
        presenter.greetGoodbye();
    }

    @Override
    public void showHello(String greetingText) {
        activity_main_tv_greeting.setTextColor(Color.RED);
        activity_main_tv_greeting.setText(greetingText);
    }

    @Override
    public void showGoodbye(String greetingText) {
        activity_main_tv_greeting.setTextColor(Color.BLUE);
        activity_main_tv_greeting.setText(greetingText);
    }



}
