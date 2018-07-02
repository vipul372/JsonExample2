package com.example.vipul.jsonexmaple2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

public class Discription extends AppCompatActivity {

        WebView webView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_discription);

            Intent intent=getIntent();
            String res=intent.getStringExtra("uRl");
            Toast.makeText(Discription.this,
                    res,Toast.LENGTH_LONG).show();

            webView=findViewById(R.id.webView);
            webView.getSettings().setLoadsImagesAutomatically(true);
            webView.getSettings().setJavaScriptEnabled(false);
            webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            webView.loadUrl(res);

        }
    }

