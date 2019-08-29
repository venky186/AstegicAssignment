package com.venkatesh.astegicfirstapp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.venkatesh.astegicfirstapp.R;
import com.venkatesh.astegicfirstapp.pojo.AppData;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.FlickrImageViewHolder> {
    private List<AppData> mAppDataList;

    private Context mContext;
    private final String LOG_TAG = RecyclerViewAdapter.class.getSimpleName();

    public RecyclerViewAdapter(Context context, List<AppData> appDataList) {
        mContext = context;
        this.mAppDataList = appDataList;
    }

    @Override
    public FlickrImageViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_adapter, null);
        FlickrImageViewHolder flickrImageViewHolder = new FlickrImageViewHolder(view);
        return flickrImageViewHolder;
    }


    @Override
    public void onBindViewHolder(FlickrImageViewHolder flickrImageViewHolder, int i) {
        AppData appData = mAppDataList.get(i);
        Log.d(LOG_TAG,"Processing: " + appData.getTitle() + " --> " + Integer.toString(i));

        Picasso.with(mContext).load(appData.getImage())
                .error(R.drawable.recyclerviewdefualtimage)
                .placeholder(R.drawable.recyclerviewdefualtimage)
                .into(flickrImageViewHolder.thumbnail);
        flickrImageViewHolder.title.setText(appData.getTitle());
        flickrImageViewHolder.info.setText(appData.getTags());
    }

    @Override
    public int getItemCount() {
        return (null != mAppDataList ? mAppDataList.size() : 0);
    }

    public void loadNewData(List<AppData> appData) {
        mAppDataList = appData;
        notifyDataSetChanged();
    }

    public AppData getPhoto(int position) {
        return (null != mAppDataList ? mAppDataList.get(position) : null);
    }

    class FlickrImageViewHolder extends RecyclerView.ViewHolder {
        protected ImageView thumbnail;
        protected TextView title;
        protected TextView info;

        public FlickrImageViewHolder(View view) {
            super(view);

            this.thumbnail =  view.findViewById(R.id.thumbnail);
            this.title = (TextView) view.findViewById(R.id.title);
            this.info = (TextView) view.findViewById(R.id.info);

        }
    }
}
