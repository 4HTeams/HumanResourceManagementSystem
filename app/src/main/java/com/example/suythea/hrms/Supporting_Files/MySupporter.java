package com.example.suythea.hrms.Supporting_Files;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Base64;
import android.util.Log;
import android.view.WindowManager;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.suythea.hrms.Interfaces.MySupporter_Interface;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.EachExceptionsHandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MySupporter {

    public static Context myContext;
    static MySupporter_Interface mySupporter_interface;
    static ProgressDialog dialog;

    public static void runFirstDefault (Context context){
        myContext = context;
        dialog = new ProgressDialog(myContext);
    }

    public static String encodeBase64 (Bitmap bitmap){

        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 60, bao);
        byte [] ba = bao.toByteArray();

        return Base64.encodeToString(ba,Base64.DEFAULT);
    }

    public static void Volley (String url, final Map<String, String> params, MySupporter_Interface _context){

        MyVolley.cancelOldPandingRequest();
        mySupporter_interface = _context;

        StringRequest stringRequest = new StringRequest(Request.Method.DEPRECATED_GET_OR_POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mySupporter_interface.onVolleyFinished(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mySupporter_interface.onVolleyError(error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                String key = "", value = "";
                HashMap<String, String> maps = new HashMap<>();

                for (Map.Entry<String, String> entry : params.entrySet()) {

                    key = entry.getKey();

                    try {
                        value = URLEncoder.encode(entry.getValue(), "utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                    maps.put(key, value);
                }

                return maps;
            }
        };

        MyVolley.getMyInstance().addToRequestQueue(stringRequest);
    }

    public static HashMap<String, String> myEncoder (Map<String, String> params){

        String key = "", value = "";
        HashMap<String, String> maps = new HashMap<>();

        for (Map.Entry<String, String> entry : params.entrySet()) {

            key = entry.getKey();

            try {
                value = URLEncoder.encode(entry.getValue(), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            maps.put(key, value);
        }

        Log.d("result", String.valueOf(maps));

        return maps;
    }


    public static void Http (String url, Map<String,String> params, Context context){

        MyVolley.cancelOldPandingRequest();
        mySupporter_interface = (MySupporter_Interface) context;

        MyHttp myTask = new MyHttp(context, myEncoder(params), new AsyncResponse() {
            @Override
            public void processFinish(String response) {
                mySupporter_interface.onHttpFinished(response);
            }
        });

        myTask.execute(url);
        myTask.setEachExceptionsHandler(new EachExceptionsHandler() {
            @Override
            public void handleIOException(IOException e) {
                mySupporter_interface.onHttpError(e.getMessage());
            }

            @Override
            public void handleMalformedURLException(MalformedURLException e) {
                mySupporter_interface.onHttpError(e.getMessage());
            }

            @Override
            public void handleProtocolException(ProtocolException e) {
                mySupporter_interface.onHttpError(e.getMessage());
            }

            @Override
            public void handleUnsupportedEncodingException(UnsupportedEncodingException e) {
                mySupporter_interface.onHttpError(e.getMessage());
            }
        });
    }

    public static void Http2 (String url, Map<String,String> params, Context context, MySupporter_Interface _mySupporter_interface){

        MyVolley.cancelOldPandingRequest();
        mySupporter_interface = _mySupporter_interface;

        MyHttp myTask = new MyHttp(context,  myEncoder(params), new AsyncResponse() {
            @Override
            public void processFinish(String response) {
                mySupporter_interface.onHttpFinished(response);
            }
        });

        myTask.execute(url);
        myTask.setEachExceptionsHandler(new EachExceptionsHandler() {
            @Override
            public void handleIOException(IOException e) {
                mySupporter_interface.onHttpError(e.getMessage());
            }

            @Override
            public void handleMalformedURLException(MalformedURLException e) {
                mySupporter_interface.onHttpError(e.getMessage());
            }

            @Override
            public void handleProtocolException(ProtocolException e) {
                mySupporter_interface.onHttpError(e.getMessage());
            }

            @Override
            public void handleUnsupportedEncodingException(UnsupportedEncodingException e) {
                mySupporter_interface.onHttpError(e.getMessage());
            }
        });
    }

    public static void showLoading (String message){
        dialog.setMessage(message);
        dialog.setCancelable(false);
        dialog.setInverseBackgroundForced(false);
        dialog.show();
    }

    public static void hideLoading () {
        dialog.hide();
    }

    public static String checkError (){
        ConnectivityManager conMgr = (ConnectivityManager) myContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        if ( conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED
                || conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED ) {

            return "Generic error may be because of our server, try again later !";
        }
        else if ( conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.DISCONNECTED
                || conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.DISCONNECTED) {

            return "Check your Internet connection !";
        }

        return "Generic error may be because of our server, try again later !";
    }

    public static String verifyControls(Map<String, String> maps){

        String result = "OK";
        String key = "", value = "";

        for (Map.Entry<String, String> entry : maps.entrySet()) {

            key = entry.getKey().toUpperCase();
            value = entry.getValue();

            switch (key) {
                case "EMAIL" :
                    if (!verifyWithFormat("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+", value)){
                        return "Check your email format !";
                    }
                    break;
                case "USERNAME" :
                    if (value.length() > 50 || value.length() <= 2){
                        return "Your username must be greater than 2 less than 50 characters !";
                    }
                    else if (!verifyWithFormat("[a-zA-Z0-9\\_]+", value)){
                        return "Check your username format !";
                    }
                    break;
                case "PASSWORD" :
                    if (value.length() > 100 || value.length() <= 5){
                        return "Your password must be greater than 5 less than 100 characters !";
                    }
                    break;
                case "CNAME" :
                    if (value.length() > 100 || value.length() <= 2){
                        return "Your Company name must be greater than 2 less than 100 characters !";
                    }
                    break;
                case "CEMAIL" :
                    if (!verifyWithFormat("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+", value)){
                        return "Check your company email format !";
                    }
                    break;
                case "EMPAMOUNT" :
                    if (value.length() < 1){
                        return "Your company employee amount cannot be empty !";
                    }
                    break;
                case "CONTACT" :
                    if (value.length() < 1){
                        return "Your company contact cannot be empty !";
                    }
                    break;
                case "ABOUT" :
                    if (value.length() < 1){
                        return "Your company about cannot be empty !";
                    }
                    break;
                case "ADDRESS" :
                    if (value.length() < 1){
                        return "Your company address cannot be empty !";
                    }
                    break;
            }

        }

        return result;
    }

    private static Boolean verifyWithFormat (String format, String text){
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile(format);
        matcher = pattern.matcher(text);
        return matcher.matches();
    }

}
