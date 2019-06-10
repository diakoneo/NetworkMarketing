package com.androidapk.diakonetapps.networkmarketing.network;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.androidapk.diakonetapps.networkmarketing.R;
import com.androidapk.diakonetapps.networkmarketing.database.Notes;
import com.androidapk.diakonetapps.networkmarketing.database.NotesDbHelper;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listview;
    private List<Notes> notesList;

    private NotesDbHelper notesDbHelper;
    private TopicsAdapter adapter;

    private String searchWord = null;
    private SearchView searchView;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Network Marketing");

        notesDbHelper = new NotesDbHelper(MainActivity.this);
        notesList = notesDbHelper.getAllNotes();

        listview = findViewById(R.id.lessons_list);

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
                Toast.makeText(MainActivity.this, i + " Banner", Toast.LENGTH_SHORT).show();
            }
        });

        loadNotes();
    }

    private void loadNotes() {
        this.notesList = new ArrayList<>();
        List<Notes> list = notesDbHelper.getAllNotes();// get All notes from DataBase
        this.notesList.addAll(list);
        this.adapter = new TopicsAdapter( MainActivity.this, notesList);
        // set listener to adapter
        listview.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemNo = position + 1;

//                if (itemNo == 4 || itemNo == 5) {
//                    Toast.makeText(MainActivity.this, "Populating content ...", Toast.LENGTH_SHORT).show();
//                    return;
//                }

              //  Toast.makeText(MainActivity.this, "Position: " +position, Toast.LENGTH_SHORT).show();
                Intent read = new Intent(MainActivity.this, ReadActivity.class);
                read.putExtra("item_n  umber", itemNo);
                startActivity(read);
            }
        });

        listview.setTextFilterEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        searchItem(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuId = item.getItemId();

        if (menuId == R.id.main_menu_about){

            return true;
        }else if (menuId == R.id.main_menu_share){

            return true;
        }else if (menuId == R.id.main_menu_exit){
            alertExit();
            return true;
        } else{
            return false;
        }

    }

    @Override
    public void onBackPressed() {

        if (!searchView.isIconified()){
            InputMethodManager inputMethodManager = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(searchView.getWindowToken(), 0);
            searchView.setIconified(true);
        } else {
//            alertExit();
            finish();
        }
    }

    private void alertExit() {
        AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
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

    private void searchItem(Menu menu) {
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.main_menu_search).getActionView();

        searchView.setSubmitButtonEnabled(true);
        searchView.setQueryHint("Search for topic or content!");

        if (null != searchView){
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            //  searchView.setIconifiedByDefault(false);
        }

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                InputMethodManager inputMethodManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(searchView.getWindowToken(), 0);

                return false;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                InputMethodManager inputMethodManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(searchView.getWindowToken(), 0);

                searchWord = query;

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (TextUtils.isEmpty(newText)){
                    listview.clearTextFilter();
                } else {
                    listview.setFilterText(newText);
                }
//                searchWord = newText;
                return true;
            }
        });

    }
}
