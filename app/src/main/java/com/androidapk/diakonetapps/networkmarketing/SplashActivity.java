package com.androidapk.diakonetapps.networkmarketing;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    private ImageView loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        loading = findViewById(R.id.loading);
//
//        Glide.with(getApplicationContext())
////                .load("https://ui-ex.com/images/transparent-background-loading-3.gif")
//                .load(R.drawable.trans)
//                .into(loading);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
//                overridePendingTransition(R.anim.split_enter, R.anim.split_exit);
                finish();
            }
        }, 2000);

    }
}
