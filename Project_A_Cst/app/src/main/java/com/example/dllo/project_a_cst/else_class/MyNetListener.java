package com.example.dllo.project_a_cst.else_class;

import com.android.volley.VolleyError;

/**
 * Created by dllo on 16/12/7.
 */

public interface MyNetListener<T> {
    void successListener(T response);
    void errorListener(VolleyError error);
}
