package com.androidapk.diakonetapps.networkmarketing.network;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidapk.diakonetapps.networkmarketing.R;
import com.androidapk.diakonetapps.networkmarketing.database.Notes;
import com.androidapk.diakonetapps.networkmarketing.database.NotesDbHelper;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class ReadActivity extends AppCompatActivity {

    private WebView mywebView;
    private AdView mAdView;

    private int itemNumber;

    private Notes notes;
    private NotesDbHelper helper;

    private ProgressBar progress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        helper = new NotesDbHelper(ReadActivity.this);

        if (getIntent().getExtras() != null){
            itemNumber = getIntent().getExtras().getInt("item_number", 0);
            notes = helper.getNoteByID(itemNumber);
        }

        setTitle(notes.getTopic());

        progress = findViewById(R.id.loading_progress);

        //      initialisations
        mywebView = findViewById(R.id.mywebview);
        WebSettings webSettings= mywebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        mywebView.loadUrl("file:///android_asset/html/network_marketing/"+notes.getFile()+".html");
        mywebView.setWebViewClient(new WebViewClient());
        mywebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);

                if (newProgress < 100 && progress.getVisibility() == ProgressBar.GONE) {
                    progress.setVisibility(ProgressBar.VISIBLE);
                }else if(newProgress == 100) {
                    progress.setVisibility(ProgressBar.GONE);
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener(){
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                mAdView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                Toast.makeText(ReadActivity.this, i + " Banner", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
