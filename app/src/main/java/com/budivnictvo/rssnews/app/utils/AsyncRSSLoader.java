package com.budivnictvo.rssnews.app.utils;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.budivnictvo.rssnews.app.OnRSSLoadCallback;
import com.budivnictvo.rssnews.app.R;
import com.budivnictvo.rssnews.app.data.RssItem;
import com.github.kevinsawicki.http.HttpRequest;
import java.util.ArrayList;

/**
 * Created by Администратор on 16.12.2014.
 */
public class AsyncRSSLoader extends AsyncTask<String, Void, ArrayList<RssItem>> {
    private ProgressDialog dialog;
    private Fragment mFragment;

    public AsyncRSSLoader(Fragment fragment) {
        this.mFragment = fragment;
    }


    @Override
    protected void onPreExecute() {
        this.dialog = new ProgressDialog(mFragment.getActivity());
        this.dialog.setMessage(mFragment.getResources().getString(R.string.loading));
        if (!this.dialog.isShowing()) {
            this.dialog.show();
        }
    }


    @Override
    protected ArrayList<RssItem> doInBackground(String... data) {

        ArrayList<RssItem> items = new ArrayList<RssItem>();
        for (int i = 0; i < data.length; i++) {
            String response = null;

            HttpRequest request = HttpRequest.get(data[i]);
            if (request.code() == 200) {
                response = request.body();
                RssParser parser = new RssParser();
                parser.parseXml(response);
                items.addAll(parser.getItems());
            }
        }
        return items;
    }

    @Override
    protected void onPostExecute(ArrayList<RssItem> _data) {
        OnRSSLoadCallback callback = (OnRSSLoadCallback) mFragment;
        callback.onRSSLoad(_data);
        this.dialog.dismiss();
    }

}
