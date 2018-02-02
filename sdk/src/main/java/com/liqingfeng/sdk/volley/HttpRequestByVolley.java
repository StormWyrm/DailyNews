package com.liqingfeng.sdk.volley;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.liqingfeng.sdk.global.GlobalApplication;

import java.util.Map;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/3/24 18:44
 * @DESC: 通过Volley来请求网络数据
 * @VERSION: V1.0
 */

public class HttpRequestByVolley implements HttpRequestManager {
    private static RequestQueue sRequestQueue;

    @Override
    public void getRequest(String url, final HttpRequestCallback callback) {
        initVolley();
        callback.onStart();
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.isEmpty()) {
                            callback.onEmpty();
                        } else {
                            callback.onSuccess(response);
                        }
                        callback.onEnd();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error.networkResponse != null)
                            callback.onFailure(error.networkResponse.statusCode, error.getCause());
                        else
                            callback.onFailure(0, error.getCause());
                        callback.onEnd();
                    }
                });
        sRequestQueue.add(request);
    }

    @Override
    public void postRequest(String url, final Map<String, String> map, final HttpRequestCallback callback) {
        initVolley();
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.isEmpty()) {
                            callback.onEmpty();
                        } else {
                            callback.onSuccess(response);
                        }
                        callback.onEnd();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onFailure(error.networkResponse.statusCode, error.getCause());
                        callback.onEnd();
                    }
                }) {
            protected Map<String, String> getParams() {
                return map;
            }
        };
    }

    //初始化Volley
    private void initVolley() {
        if (sRequestQueue == null) {
            synchronized (HttpRequestByVolley.class) {
                if (sRequestQueue == null) {
                    sRequestQueue = Volley.newRequestQueue(GlobalApplication.getInstance());
                }
            }
        }
    }
}
