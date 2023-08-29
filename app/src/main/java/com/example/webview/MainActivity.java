package com.example.webview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.webview.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private WebView webView;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        init();
    }

    private void init(){

        initWebView();
        initUrl();
    }

    private void initWebView(){
        binding.webview.getSettings().setSupportZoom(true);
        binding.webview.getSettings().setBuiltInZoomControls(true);
        binding.webview.getSettings().setDisplayZoomControls(true);

        binding.webview.getSettings().setJavaScriptEnabled(true);

        binding.webview.setWebViewClient(new mWebViewClient());

        binding.webview.evaluateJavascript("var FunctionOne = function () {"
                + "  try{document.getElementsByClassName('test')[0].style.color='red';}catch(e){}"
                + "};", null);
    }

    private void initUrl(){
        binding.loadWebview.setOnClickListener(v -> {
           url = binding.editUrl.getText().toString();
            binding.webview.loadUrl(url);


        });
    }


    private class mWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            view.loadUrl(url);
            return true;

        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            Toast.makeText(MainActivity.this, "Loading page...", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onPageFinished(WebView view, String url) {

            Toast.makeText(MainActivity.this, "Loading finished", Toast.LENGTH_SHORT).show();

        }
    }
}