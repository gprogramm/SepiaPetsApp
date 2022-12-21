package com.android.sepiapetsapp.views;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.android.sepiapetsapp.R;

public class PetsDetailsActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pets_details);

        initComponents();
    }

    /**
     * Initialize the components used in xml
     */
    private void initComponents() {
        // Fetch parameters that get passed from previous screen
        String petsDetailsUrl = getIntent().getStringExtra("content_url");

        // Initialize other components
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        initWebView(petsDetailsUrl);
    }

    /**
     * Init/SetUp web-view
     *
     * @param url
     */
    private void initWebView(String url) {
        // bind view
        WebView webView = findViewById(R.id.web_view);

        // Set up the settings of web-view
        setUpWebViewDefaults(webView);

        // Load the url
        webView.loadUrl(url);
    }

    /**
     * Convenience method to set some generic defaults for a
     * given WebView
     *
     * @param webView
     */
    private void setUpWebViewDefaults(WebView webView) {
        WebSettings settings = webView.getSettings();

        // Enable Javascript
        settings.setJavaScriptEnabled(true);

        // Use WideViewport and Zoom out if there is no viewport defined
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);

        // Enable pinch to zoom without the zoom buttons
        settings.setBuiltInZoomControls(true);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {
            // Hide the zoom controls for HONEYCOMB+
            settings.setDisplayZoomControls(false);
        }

        // Enable remote debugging via chrome://inspect
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }

        // We set the WebViewClient to ensure links are consumed by the WebView rather
        // than passed to a browser if it can
        //videoWebView.setWebViewClient(new WebViewClient());
        webView.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
