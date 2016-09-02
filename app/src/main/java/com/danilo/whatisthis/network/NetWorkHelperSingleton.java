package com.danilo.whatisthis.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by danilo on 31/08/2016.
 */
public class NetWorkHelperSingleton {
    private ImageLoader imageLoader;
    private RequestQueue requestQueue;
    private static Context ctx;
    private static NetWorkHelperSingleton instance;

    private NetWorkHelperSingleton(Context ctx){
        this.ctx = ctx;
        requestQueue = getQueue();
        imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache(){
            private LruCache<String,Bitmap> cache = new LruCache<>(20);
            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url,bitmap);
            }
        });
    }
    private NetWorkHelperSingleton(Context ctx, ImageLoader.ImageCache cache){
        this.ctx = ctx;
        requestQueue = getQueue();
        imageLoader = new ImageLoader(requestQueue,cache);
    }

    public static synchronized NetWorkHelperSingleton newInstance(Context ctx) {
        if(instance == null){
            instance = new NetWorkHelperSingleton(ctx);
        }
        return instance;
    }

    public RequestQueue getQueue(){
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    public ImageLoader getImageLoader(){
        return imageLoader;
    }

    public <MODEL> void addToQueue(Request<MODEL> request){
        getQueue().add(request);
    }
}
