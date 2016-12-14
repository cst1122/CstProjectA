package com.example.dllo.project_a_cst.else_class;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;

/**
 * Created by leisure on 2016/11/27.
 */
public class NetHelper {
    private RequestQueue mRequestQueue;
    private Context mContext;
    private static NetHelper ourInstance = new NetHelper();

    public static NetHelper getInstance() {
        return ourInstance;
    }

    private NetHelper() {
        mContext = MyApp.getContext();
        mRequestQueue = getRequestQueue();
    }
    private RequestQueue getRequestQueue(){
        if (mRequestQueue == null){
            synchronized (NetHelper.class){
                if (mRequestQueue == null){
                    mRequestQueue = Volley.newRequestQueue(mContext);
                }
            }
        }
        return mRequestQueue;
    }

    public <T> void baseRequest(Request<T> request){
        mRequestQueue.add(request);
    }

    private void baseRequest(String url, final MyNetListener<String> myNetListener){
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                myNetListener.successListener(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                myNetListener.errorListener(error);
            }
        });
        mRequestQueue.add(stringRequest);
    }
    private <T> void baseGetGsonRequset(String url , final MyNetListener<T> listener, Class<T> mClass){
        GsonRequest<T> gsonRequest = new GsonRequest<>(Request.Method.GET, url, mClass, new Response.Listener<T>() {
            @Override
            public void onResponse(T response) {
            listener.successListener(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
              listener.errorListener(error);
            }
        });
        mRequestQueue.add(gsonRequest);
    }
    private <T> void basePostGsonRequset(String url, Class<T> mClass, final MyNetListener<T> listener, HashMap<String,String> map){
        GsonRequest<T> gsonRequest = new GsonRequest<T>(Request.Method.POST, url, mClass, new Response.Listener<T>() {
            @Override
            public void onResponse(T response) {
            listener.successListener(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               listener.errorListener(error);
            }
        },map);
        mRequestQueue.add(gsonRequest);

    }

    public static void myRequest(String url,MyNetListener<String> listener){
        getInstance().baseRequest(url,listener);
    }
    public static <T> void myRequest(String url,MyNetListener<T> listener,Class<T> mClass){
        getInstance().baseGetGsonRequset(url,listener,mClass);
    }
    public static <T> void myRequest(String url,MyNetListener<T> listener,Class<T> mClass,HashMap<String,String> map){
        getInstance().basePostGsonRequset(url,mClass,listener,map);
    }


}
