package com.juanpabloprado.diputados;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by Juan on 9/13/2015.
 */
public class DiputadosApplication extends Application{
    public final static String TAG = DiputadosApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.

        Parse.initialize(this);

        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);
    }
}
