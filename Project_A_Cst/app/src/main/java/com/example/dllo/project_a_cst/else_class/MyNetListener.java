package com.example.dllo.project_a_cst.else_class;

import com.android.volley.VolleyError;


/**
 * Created by leisure on 2016/11/27.
 */

public interface MyNetListener<T> {
    void successListener(T response);
    void errorListener(VolleyError error);
}
