package com.nhancv.mosbymvp.di;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by nhancao on 2/9/17.
 */

@Scope
@Retention(RUNTIME)
public @interface PerActivity {
}
