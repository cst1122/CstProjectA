package com.example.dllo.project_a_cst.else_class;

import android.app.Application;
import android.content.Context;

/**
 * Created by leisure on 2016/11/27.
 */

public class MyApp extends Application {

    private static Context mContext;
    @Override

    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getmContext() {
        return mContext;
    }
}
