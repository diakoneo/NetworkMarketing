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

    private String course;

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

        checkIntent();

        notesDbHelper = new NotesDbHelper(MainActivity.this);
        notesList = notesDbHelper.getAllNotes(course);

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

    private void checkIntent() {
        if (getIntent().getExtras() != null){
            course = getIntent().getExtras().getString("course").trim();
            setTitle(course.toUpperCase());
        }
    }

    private void loadNotes() {
        this.notesList = new ArrayList<>();
        List<Notes> list = notesDbHelper.getAllNotes(course);// get All notes from DataBase
        this.notesList.addAll(list);
        this.adapter = new TopicsAdapter(MainActivity.this, notesList);
        // set listener to adapter
        listview.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              //  int itemNo = parent.getSelectedItemPosition();

                String name = notesList.get(position).getTopic();

                //Toast.makeText(MainActivity.this, "Item: " +name, Toast.LENGTH_SHORT).show();
                Intent read = new Intent(MainActivity.this, ReadActivity.class);
                read.putExtra("item_number", name);
                startActivity(read);
            }
        });

        listview.setTextFilterEnabled(true);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sub_menu, menu);
        searchItem(menu);

        return true;
    }

    private void searchItem(Menu menu) {
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.sub_menu_search).getActionView();

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
