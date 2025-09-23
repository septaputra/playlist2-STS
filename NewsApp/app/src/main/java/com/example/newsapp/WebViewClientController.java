package com.example.newsapp;

import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.Nullable;

public class WebViewClientController extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        // Membuka semua tautan di dalam WebView
        return false;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        // Untuk kompatibilitas API lama
        return false;
    }
}
