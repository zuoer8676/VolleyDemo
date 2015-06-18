package com.example.volleydemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader.ImageCache;

@SuppressLint("NewApi")
class VolleyBitmapLruCache extends LruCache<String, Bitmap> implements ImageCache {  //采用Lru算法计算缓存

	
	public static int getDefaultLruCacheSize() {
		final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
		final int cacheSize = maxMemory / 8;
		return cacheSize;
	}

	public VolleyBitmapLruCache(Context context) {
		this(context , getDefaultLruCacheSize());
	}

	@SuppressLint("NewApi")
	public VolleyBitmapLruCache(Context context , int sizeInKiloBytes) {
		super(sizeInKiloBytes);
	}

	@SuppressLint("NewApi")
	@Override
	protected int sizeOf(String key, Bitmap value) {
		return value.getRowBytes() * value.getHeight() / 1024;
	}

	@SuppressLint("NewApi")
	@Override
	public Bitmap getBitmap(String url) {
		return get(url);
	}

	@Override
	public void putBitmap(String url, Bitmap bitmap) {
		put(url, bitmap);
	}
}
