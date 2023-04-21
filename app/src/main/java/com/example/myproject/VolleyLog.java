package com.example.myproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class VolleyLog {



        static String resp="";

        public static void showError(Context ctx, VolleyError error){
            String message = null;
            if (error instanceof NetworkError || error instanceof AuthFailureError || error instanceof NoConnectionError) {
                message = ctx.getString(R.string.net_connect);
            } else if (error instanceof ParseError) {
                message = ctx.getString(R.string.parsing_err);
            }else if(error instanceof TimeoutError){
                message = ctx.getString(R.string.time_out);
            }else {
                message=ctx.getString(R.string.try_again);
            }
            Toast.makeText(ctx,message, Toast.LENGTH_LONG ).show();
        }

        public static void showNoDataError(Context ctx, VolleyError error){
            String message = null;
            if (error instanceof NetworkError || error instanceof AuthFailureError || error instanceof NoConnectionError) {
                message = ctx.getString(R.string.net_connect);
            } else if (error instanceof ParseError) {
                message = ctx.getString(R.string.parsing_err);
            }else if(error instanceof TimeoutError){
                message = ctx.getString(R.string.time_out);
            }else {
                message=ctx.getString(R.string.no_data);
            }
            Toast.makeText(ctx,message, Toast.LENGTH_LONG ).show();
        }


        public static void showImgError(Context ctx){
//        Toast.makeText(ctx, ctx.getString(R.string.cover_img_size_str,String.valueOf(Config.img_width),String.valueOf(Config.img_height)), Toast.LENGTH_SHORT).show();
        }
        public static void make_toast(Context ctx, String text){
            Toast.makeText(ctx, text, Toast.LENGTH_SHORT).show();
        }


        public interface VolleyCallback{
            void onSuccess(String response);
        }



        public static void getResponse1(final Context ctx, String url, final HashMap<String, String> params, final VolleyCallback callback){

            final ProgressDialog loading = ProgressDialog.show(ctx,ctx.getString(R.string.plz_wait),ctx.getString(R.string.fetching),false,true);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            loading.dismiss();
                            callback.onSuccess(response);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            loading.dismiss();
                            VolleyLog.showError(ctx,error);
                        }
                    }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    return params;
                }
            };
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    0,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            stringRequest.setShouldCache(false);
            RequestQueue requestQueue = Volley.newRequestQueue(ctx);
            requestQueue.add(stringRequest);
        }
    public static void getServerResponse(final Context ctx, String url, final HashMap<String, String> params, final VolleyCallback callback){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        loading.dismiss();
                        callback.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        loading.dismiss();
//                        VolleyLog.showError(ctx,error);
//                        Toast.makeText(ctx, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        stringRequest.setShouldCache(false);
        RequestQueue requestQueue = Volley.newRequestQueue(ctx);
        requestQueue.add(stringRequest);
    }
    public static void getResponse(final Context ctx, String url, final HashMap<String, String> params, final VolleyCallback callback){

        final ProgressDialog loading = ProgressDialog.show(ctx,ctx.getString(R.string.plz_wait),ctx.getString(R.string.fetching),false,true);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
//                        Toast.makeText(ctx, response, Toast.LENGTH_SHORT).show();
                        callback.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(ctx, error.toString(), Toast.LENGTH_SHORT).show();
                        VolleyLog.showError(ctx,error);
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };

//        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
//                0,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        stringRequest.setShouldCache(false);
        RequestQueue requestQueue = Volley.newRequestQueue(ctx);
        requestQueue.add(stringRequest);
    }
    }

