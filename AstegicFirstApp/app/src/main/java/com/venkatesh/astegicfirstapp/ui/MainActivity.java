package com.venkatesh.astegicfirstapp.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.venkatesh.astegicfirstapp.FlickrData.ParseJsonData;
import com.venkatesh.astegicfirstapp.R;
import com.venkatesh.astegicfirstapp.databinding.ActivityMainBinding;
import com.venkatesh.astegicfirstapp.pojo.AppData;
import com.venkatesh.astegicfirstapp.ui.adapter.RecyclerItemClick;
import com.venkatesh.astegicfirstapp.ui.adapter.RecyclerViewAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String FLICKR_QUERY = "FLICKR_QUERY";
    public static final String PHOTO_TRANSFER = "PHOTO_TRANSFER";
    ActivityMainBinding activityMainBinding;
    private RecyclerViewAdapter recyclerViewAdapter;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);


        activityMainBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this,
                new ArrayList<AppData>());
        activityMainBinding.recyclerView.setAdapter(recyclerViewAdapter);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(MainActivity.this, fab, "transition1");

                ActivityCompat.startActivity(MainActivity.this, intent, options.toBundle());
            }
        });
        activityMainBinding.recyclerView.addOnItemTouchListener(new RecyclerItemClick(this,
                activityMainBinding.recyclerView, new RecyclerItemClick.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {


                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra(PHOTO_TRANSFER, recyclerViewAdapter.getPhoto(position));
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
    }


    @Override
    protected void onResume() {
        super.onResume();
        String query = getSavedPreferenceData(FLICKR_QUERY);
        if (query.length() > 0) {
            ProcessPhotos processPhotos = new ProcessPhotos(query, true);
            processPhotos.execute();
        }
    }

    private String getSavedPreferenceData(String key) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        return sharedPref.getString(key, "");
    }

    public class ProcessPhotos extends ParseJsonData {

        public ProcessPhotos(String searchCriteria, boolean matchAll) {
            super(searchCriteria, matchAll);
        }

        public void execute() {

            ProcessData processData = new ProcessData();
            processData.execute();
        }

        public class ProcessData extends DownloadJsonData {

            protected void onPostExecute(String webData) {
                super.onPostExecute(webData);
                recyclerViewAdapter.loadNewData(getPhotos());


            }
        }

    }


}
