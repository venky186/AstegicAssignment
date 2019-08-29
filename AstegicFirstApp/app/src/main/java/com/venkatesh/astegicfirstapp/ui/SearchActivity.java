package com.venkatesh.astegicfirstapp.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.venkatesh.astegicfirstapp.R;
import com.venkatesh.astegicfirstapp.databinding.SearchlayoutBinding;

import static com.venkatesh.astegicfirstapp.ui.MainActivity.FLICKR_QUERY;

public class SearchActivity extends AppCompatActivity {

private SearchlayoutBinding searchlayoutBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchlayoutBinding= DataBindingUtil.setContentView(this,R.layout.searchlayout);
        searchlayoutBinding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchlayoutBinding.editText.getText().toString();
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                sharedPref.edit().putString(FLICKR_QUERY, searchlayoutBinding.editText.getText().toString()).commit();
                finish();

            }
        });

    }
}
