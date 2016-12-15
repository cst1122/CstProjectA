package com.example.dllo.project_a_cst.else_class;

import android.content.Context;

import com.baidu.frontia.FrontiaApplication;
import com.example.dllo.project_a_cst.my_database.DaoMaster;
import com.example.dllo.project_a_cst.my_database.DaoSession;

/**
 * Created by dllo on 16/12/7.
 */

public class MyApp extends FrontiaApplication {
    private static Context sContext;
    private static DaoMaster sDaoMaster;
    private static DaoSession sDaoSession;
    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }
    public static Context getContext(){
        return sContext;
    }
    public static DaoMaster getDaoMaster(){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getContext(),"Person.db",null);
        // 初始化DaoMaster对象
        sDaoMaster = new DaoMaster(helper.getWritableDb());
        return sDaoMaster;
    }

    public static DaoSession getDaoSession(){
        if (sDaoSession == null){
            if (sDaoMaster == null){
                sDaoMaster = getDaoMaster();
            }
            // 初始化DaoSession对象
            sDaoSession = sDaoMaster.newSession();
        }
        return sDaoSession;
    }
}
