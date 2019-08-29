package com.venkatesh.astegicfirstapp.FlickrData;

import android.net.Uri;
import android.util.Log;

import com.venkatesh.astegicfirstapp.pojo.AppData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ParseJsonData extends FetchJsonData {
    private List<AppData> mPhotos;
    private Uri mDestinationUri;

    public ParseJsonData(String searchCriteria, boolean matchAll) {
        super(null);
        createAndUpdateUri(searchCriteria, matchAll);
        mPhotos = new ArrayList<AppData>();
    }

    public void execute() {
        super.setmRawUrl(mDestinationUri.toString());
        DownloadJsonData downloadJsonData = new DownloadJsonData();
        downloadJsonData.execute(mDestinationUri.toString());
    }

    public boolean createAndUpdateUri(String searchCriteria, boolean matchAll) {
        final String FLICKR_API_BASE_URL = "https://api.flickr.com/services/feeds/photos_public.gne";

        final String TAGS_PARAM = "tags";
        final String TAGMODE_PARAM = "tagmode";
        final String FORMAT_PARAM = "format";
        final String NO_JSON_CALLBACK_PARAM = "nojsoncallback";

        mDestinationUri = Uri.parse(FLICKR_API_BASE_URL).buildUpon()
                .appendQueryParameter(TAGS_PARAM, searchCriteria)
                .appendQueryParameter(TAGMODE_PARAM, matchAll ? "ALL" : "ANY")
                .appendQueryParameter(FORMAT_PARAM, "json")
                .appendQueryParameter(NO_JSON_CALLBACK_PARAM, "1")
                .build();

        return mDestinationUri != null;
    }

    public List<AppData> getPhotos() {
        return mPhotos;
    }

    public void processResult() {

        if (getmStatus() != Status.OK) {
            return;
        }

        final String FLICKR_ITEMS = "items";
        final String FLICKR_TITLE = "title";
        final String FLICKR_MEDIA = "media";
        final String FLICKR_PHOTO_URL = "m";
        final String FLICKR_AUTHOR = "author";
        final String FLICKR_AUTHOR_ID = "author_id";
        final String FLICKR_LINK = "link";
        final String FLICKR_TAGS = "tags";

        try {

            JSONObject jsonData = new JSONObject(getmData());
            JSONArray itemsArray = jsonData.getJSONArray(FLICKR_ITEMS);
            for (int i = 0; i < itemsArray.length(); i++) {

                JSONObject jsonPhoto = itemsArray.getJSONObject(i);
                String title = jsonPhoto.getString(FLICKR_TITLE);
                String author = jsonPhoto.getString(FLICKR_AUTHOR);
                String authorId = jsonPhoto.getString(FLICKR_AUTHOR_ID);
                String tags = jsonPhoto.getString(FLICKR_TAGS);

                JSONObject jsonMedia = jsonPhoto.getJSONObject(FLICKR_MEDIA);
                String photoUrl = jsonMedia.getString(FLICKR_PHOTO_URL);
                String link = photoUrl.replaceFirst("_m.", "_b.");

                AppData photoObject = new AppData(title, author, authorId, link, tags, photoUrl);

                this.mPhotos.add(photoObject);
            }

            for (AppData singlePhoto : mPhotos) {
                Log.v("AppData", singlePhoto.toString());
            }

        } catch (JSONException jsone) {
            jsone.printStackTrace();
        }

    }

    public class DownloadJsonData extends DownloadRawData {

        protected void onPostExecute(String webData) {
            super.onPostExecute(webData);
            processResult();

        }

        protected String doInBackground(String... params) {
            String[] par = {mDestinationUri.toString()};
            return super.doInBackground(par);
        }

    }

}
