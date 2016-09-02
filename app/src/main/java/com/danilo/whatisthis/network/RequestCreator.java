package com.danilo.whatisthis.network;

import android.graphics.Bitmap;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by danilo on 31/08/2016.
 */
public class RequestCreator<MODEL> {

    private Request<MODEL> request;
    private String url;
    private MODEL model;
    private Exception exception;

    public void createPostRequest(String url){
        this.url = url;
    }

    public Bitmap createImageRequest(){
        return null;
    }

    public MODEL createJsonGetRequest(){
        final JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                ObjectMapper mapper = new ObjectMapper();
                try {
                    mapper.readValue(response.toString(), model.getClass());
                } catch (IOException e) {
                    e.printStackTrace();
                    exception = e;
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                exception = error;
            }
        });
        return model;
    }

    public Exception getException() {
        return exception;
    }
}
