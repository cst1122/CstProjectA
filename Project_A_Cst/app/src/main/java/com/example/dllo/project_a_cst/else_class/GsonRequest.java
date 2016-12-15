package com.example.dllo.project_a_cst.else_class;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
/**
 * Created by dllo on 16/12/7.
 */

public class GsonRequest<T> extends Request<T> {
    private Gson mGson;
    private Response.Listener<T> mListener;
    private Class<T> mClass;
    private HashMap<String,String> map;

    public GsonRequest(int method, String url, Class<T> mClass, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.mListener = listener;
        mGson = new Gson();
        this.mClass = mClass;
    }
    public GsonRequest(int method, String url, Class<T> mClass, Response.Listener<T> listener, Response.ErrorListener errorListener, HashMap<String,String> map) {
        super(method, url, errorListener);
        this.mListener = listener;
        mGson = new Gson();
        this.mClass = mClass;
        this.map = map;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(mGson.fromJson(jsonString,mClass),HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Response.error(new ParseError(e));
        }

    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }

    @Override
    public void deliverError(VolleyError error) {

        if (error instanceof NoConnectionError){
            Cache.Entry entry = this.getCacheEntry();
            if (error != null){
                Log.d("GsonRequest", "我是缓存的数据");
                Response<T> response = parseNetworkResponse
                        (new NetworkResponse(entry.data,entry.responseHeaders));
                deliverResponse(response.result);
                return;
            }
        }
        super.deliverError(error);
    }
}
