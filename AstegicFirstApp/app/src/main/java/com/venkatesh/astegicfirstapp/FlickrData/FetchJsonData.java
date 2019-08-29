package com.venkatesh.astegicfirstapp.FlickrData;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FetchJsonData {
    private String mRawUrl;
    private String mData;
    private Status mStatus;

    public FetchJsonData(String mRawUrl) {
        this.mRawUrl = mRawUrl;
        this.mStatus = Status.IDLE;
    }


    public String getmData() {
        return mData;
    }

    public Status getmStatus() {
        return mStatus;
    }

    public void setmRawUrl(String mRawUrl) {
        this.mRawUrl = mRawUrl;
    }

    public void execute() {
        this.mStatus = Status.PROCESSING;
        DownloadRawData downloadRawData = new DownloadRawData();
        downloadRawData.execute(mRawUrl);
    }

    public class DownloadRawData extends AsyncTask<String, Void, String> {

        protected void onPostExecute(String webData) {
            mData = webData;
            if (mData == null) {
                if (mRawUrl == null) {
                    mStatus = com.venkatesh.astegicfirstapp.FlickrData.Status.NOT_INITIALISED;
                } else {
                    mStatus = com.venkatesh.astegicfirstapp.FlickrData.Status.FAILED_OR_EMPTY;
                }
            } else {
                mStatus = com.venkatesh.astegicfirstapp.FlickrData.Status.OK;
            }
        }


        protected String doInBackground(String... params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            if (params == null)
                return null;

            try {
                URL url = new URL(params[0]);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                if (inputStream == null) {
                    return null;
                }

                StringBuffer buffer = new StringBuffer();

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                return buffer.toString();

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
