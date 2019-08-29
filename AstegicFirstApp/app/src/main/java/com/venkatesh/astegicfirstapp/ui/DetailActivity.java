package com.venkatesh.astegicfirstapp.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.squareup.picasso.Picasso;
import com.venkatesh.astegicfirstapp.R;
import com.venkatesh.astegicfirstapp.databinding.PagedetailBinding;
import com.venkatesh.astegicfirstapp.pojo.AppData;

import static com.venkatesh.astegicfirstapp.ui.MainActivity.PHOTO_TRANSFER;

public class DetailActivity extends AppCompatActivity {
   private PagedetailBinding pagedetailBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pagedetailBinding = DataBindingUtil.setContentView(this,R.layout.pagedetail);

        Intent intent = getIntent();
        AppData app = (AppData) intent.getSerializableExtra(PHOTO_TRANSFER);

        pagedetailBinding.photoTitle.setText("Title: " + app.getTitle());

        pagedetailBinding.photoTags.setText("Tags: " + app.getTags());

        pagedetailBinding.photoAuthor.setText(app.getAuthor());

        Picasso.with(this).load(app.getLink())
                .error(R.drawable.recyclerviewdefualtimage)
                .placeholder(R.drawable.recyclerviewdefualtimage)
                .into(pagedetailBinding.photoImage);
    }
}