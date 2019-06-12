package com.androidapk.diakonetapps.networkmarketing;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.androidapk.diakonetapps.networkmarketing.network.MainActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

//
import com.google.android.vending.licensing.LicenseChecker;
import com.google.android.vending.licensing.LicenseCheckerCallback;
import com.google.android.vending.licensing.Policy;
import com.google.android.vending.licensing.ServerManagedPolicy;
import com.google.android.vending.licensing.AESObfuscator;

public class HomeActivity extends AppCompatActivity {

    private CardView pos, poh, network, hackers, office;
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 95;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        pos = findViewById(R.id.pos);
        poh = findViewById(R.id.poh);
        network = findViewById(R.id.network);
        hackers = findViewById(R.id.hackers);
        office = findViewById(R.id.office);
    }

    @Override
    protected void onStart() {
        super.onStart();
        network.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(HomeActivity.this, MainActivity.class);
                home.putExtra("course", "network marketing");
                startActivity(home);
            }
        });

        pos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(HomeActivity.this, MainActivity.class);
                home.putExtra("course", "proficiency of software");
                startActivity(home);
            }
        });

        poh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(HomeActivity.this, MainActivity.class);
                home.putExtra("course", "proficiency of hardware");
                startActivity(home);
            }
        });

        office.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(HomeActivity.this, MainActivity.class);
                home.putExtra("course", "microsoft office");
                startActivity(home);
            }
        });

        hackers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(HomeActivity.this, MainActivity.class);
                home.putExtra("course", "hackers pro");
                startActivity(home);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuId = item.getItemId();

        if (menuId == R.id.main_menu_share){
            String text = "Join us or tell others to download POSH APP to receive more amazing " +
                    "information’s and guides like this and many other more. Until then, stay blessed." +
                    "\n\nhttps://play.google.com/store/apps/details?id="
                    + getPackageName() + "\n\nDownload this app and enjoy with the world...";

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, text);
            sendIntent.setType("text/plain");
            startActivity(Intent.createChooser(sendIntent, "Share..."));

            return true;
        }else if (menuId == R.id.main_menu_exit){
            alertExit();
            return true;
        } else{
            return false;
        }

    }

    private void alertExit() {
        AlertDialog.Builder a_builder = new AlertDialog.Builder(HomeActivity.this);
        a_builder.setMessage("Do you really want to Exit?");
        a_builder.setCancelable(true);

        a_builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Thank You!", Toast.LENGTH_LONG).show();
                dialog.cancel();
                finish();
                System.exit(1);
            }
        });

        a_builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alert = a_builder.create();
        alert.getWindow().setGravity(Gravity.BOTTOM);
        alert.setTitle("Alert!!!");
        alert.show();
    }

}
