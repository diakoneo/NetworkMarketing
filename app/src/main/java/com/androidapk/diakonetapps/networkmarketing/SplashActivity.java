package com.androidapk.diakonetapps.networkmarketing;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.vending.licensing.AESObfuscator;
import com.google.android.vending.licensing.LicenseChecker;
import com.google.android.vending.licensing.LicenseCheckerCallback;
import com.google.android.vending.licensing.Policy;
import com.google.android.vending.licensing.ServerManagedPolicy;

public class SplashActivity extends AppCompatActivity {

    private static final String BASE64_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjEHtS41YUC29dtBZqZayNgLk6vX4Ysjhqf7e/xEgM9r6xLFsTCBPlokWcR+LqHVSXXwFRD7Kgy5EvONi9DBWu9nQ0S3pHDEFU7e3lJm77VEM/zNDW54VJ/k0ldRX32/HODQVy3xXTveR3HihIiLI/TkVdJ1bLgZF8AOQbOlWyuDZXUHGZ76mDq5YqDSLfOA9hQJVYbhqwPJEpiF0xw2lyY+QYcLOJfa/22aK2eQNxLqPNFVJxqB0JwvUk7uwqLPbt5XIeQsjRAhjqG7mFV8xrwY3PPExzokjc0tX3UZkLhX+CP6fdJMEj5nAcXXJ7IlJSSbkJMlJGStc0z7zwhkudwIDAQAB";
    // Generate your own 20 random bytes, and put them here.
    private static final byte[] SALT = new byte[] {
            -46, 65, 30, -128, -103, -57, 74, -64, 51, 88, -95, -45, 77, -117, -36, -113, -11, 32, -64,
            89
    };

    private ImageView loading;

    private LicenseCheckerCallback mLicenseCheckerCallback;
    private LicenseChecker mChecker;
    // A handler on the UI thread.
    private Handler mHandler;

    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        prefManager = new PrefManager(this);
        //
        mHandler = new Handler();

        // Try to use more data here. ANDROID_ID is a single point of attack.
        String deviceId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        // Construct the LicenseCheckerCallback. The library calls this when done.
        mLicenseCheckerCallback = new MyLicenseCheckerCallback();

        // Construct the LicenseChecker with a Policy.
        mChecker = new LicenseChecker(
                this, new ServerManagedPolicy(this,
                new AESObfuscator(SALT, getPackageName(), deviceId)),
                BASE64_PUBLIC_KEY  // Your public licensing key.
        );

        if (prefManager.isFirstTimeLaunch() || !prefManager.getLicenseStatus()) {
            prefManager.setFirstTimeLaunch(false);
            doCheck();
        } else {
            setDelay();
        }

    }

    public void setDelay(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
//                overridePendingTransition(R.anim.split_enter, R.anim.split_exit);
                finish();
            }
        }, 2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mChecker.onDestroy();
    }

    protected Dialog onCreateDialog(int id) {
        final boolean bRetry = id == 1;
        return new AlertDialog.Builder(this)
                .setTitle(R.string.unlicensed_dialog_title)
                .setMessage(bRetry ? R.string.unlicensed_dialog_retry_body : R.string.unlicensed_dialog_body)
                .setPositiveButton(bRetry ? R.string.retry_button : R.string.restore_access_button,
                        new DialogInterface.OnClickListener() {
                            boolean mRetry = bRetry;
                            public void onClick(DialogInterface dialog, int which) {
                                if ( mRetry ) {
                                    doCheck();
                                } else {
                                    mChecker.followLastLicensingUrl(SplashActivity.this);
                                    finish();
                                }
                            }
                        })
                .setNegativeButton(R.string.quit_button, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).create();
    }

    private void doCheck() {
//        mCheckLicenseButton.setEnabled(false);
        setProgressBarIndeterminateVisibility(true);
//        mStatusText.setText(R.string.checking_license);
        mChecker.checkAccess(mLicenseCheckerCallback);
    }

    private void displayResult(final String result) {
        mHandler.post(new Runnable() {
            public void run() {
//                mStatusText.setText(result);
                setProgressBarIndeterminateVisibility(false);
//                mCheckLicenseButton.setEnabled(true);
            }
        });
    }

    private void displayDialog(final boolean showRetry) {
        mHandler.post(new Runnable() {
            public void run() {
                setProgressBarIndeterminateVisibility(false);
                showDialog(showRetry ? 1 : 0);
//                mCheckLicenseButton.setEnabled(true);
            }
        });
    }

    private class MyLicenseCheckerCallback implements LicenseCheckerCallback {
        public void allow(int policyReason) {
            if (isFinishing()) {
                // Don't update UI if Activity is finishing.
                return;
            }
            // Should allow user access.
            displayResult(getString(R.string.allow));
            prefManager.setLicenseStatus(true);
            setDelay();
            Toast.makeText(SplashActivity.this, "Application Licensed ...", Toast.LENGTH_SHORT).show();
        }

        public void dontAllow(int policyReason) {
            if (isFinishing()) {
                // Don't update UI if Activity is finishing.
                return;
            }
            displayResult(getString(R.string.dont_allow));
            // Should not allow access. In most cases, the app should assume
            // the user has access unless it encounters this. If it does,
            // the app should inform the user of their unlicensed ways
            // and then either shut down the app or limit the user to a
            // restricted set of features.
            // In this example, we show a dialog that takes the user to a deep
            // link returned by the license checker.
            // If the reason for the lack of license is that the service is
            // unavailable or there is another problem, we display a
            // retry button on the dialog and a different message.
            displayDialog(policyReason == Policy.RETRY);
            prefManager.setLicenseStatus(false);
//            Toast.makeText(SplashActivity.this, "cgdfgdfgfdgd", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void applicationError(int errorCode) {
            String result = String.format(getString(R.string.application_error), errorCode);
            displayResult(result);
            Toast.makeText(SplashActivity.this, "Error " + errorCode, Toast.LENGTH_SHORT).show();
        }
    }
}
