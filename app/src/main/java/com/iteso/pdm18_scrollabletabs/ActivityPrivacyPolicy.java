package com.iteso.pdm18_scrollabletabs;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class ActivityPrivacyPolicy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);

        WebView webView = findViewById(R.id.activity_privacy);
        webView.loadUrl("file:///android_asset/PrivacyPolicy.html");

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new WebAppInterface(this),"Android");
        webView.setWebViewClient(new MyWebClient());

    }
    public class WebAppInterface{
        Context mContext;
        WebAppInterface(Context c){
            mContext = c;
        }
        @JavascriptInterface
            public void showToast(String toast){
            Toast.makeText(mContext,toast, Toast.LENGTH_LONG).show();
        }
    }

    private class MyWebClient extends WebViewClient{
        public boolean shouldOverrideUrlLoading (WebView view, String url){
            if(Uri.parse(url).getHost().equals("www.iteso.mx")){
              return false;
            }
            Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(url));
            startActivity(intent);
            return true;
        }
    }
}
