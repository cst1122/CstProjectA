package com.example.dllo.project_a_cst.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by dllo on 16/11/23.
 */

public abstract class BaseActivity extends AppCompatActivity{
    Context context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setlaouyt());
        initView();
        initData();
        context = this;
    }

    abstract int setlaouyt();
    abstract void initView();
    abstract void initData();
    public <T extends View> T bindView(int id) {
        return (T)findViewById(id);
    }
}
