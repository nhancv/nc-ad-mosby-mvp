package com.nhancv.mosbymvp;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;

import org.androidannotations.annotations.EService;
import org.androidannotations.annotations.UiThread;
import org.greenrobot.eventbus.EventBus;

/**
 * Created by nhancao on 2/9/17.
 */
@EService
public class MainService extends IntentService {

    private static EventBus bus = new EventBus();

    public MainService() {
        super(MainService.class.getSimpleName());
    }

    public static EventBus getBus() {
        return bus;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        postEvent("service create");
    }

    @Override
    public void onDestroy() {
        postEvent("service destroy");
        super.onDestroy();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        for (int i = 0; i < 5; i++) {
            postEvent(String.valueOf(i));
            SystemClock.sleep(1000);
        }
    }

    @UiThread
    void postEvent(String msg) {
        getBus().post(msg);
    }
}
