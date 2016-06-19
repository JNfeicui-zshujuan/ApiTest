package com.example.l.apitest;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by l on 2016/6/19.
 */
public class ListViewAdapter extends BaseAdapter {
    private final Context context;
    private final LayoutInflater inflater;
    private List<String> urls;
    private ImageView imageView;
    private String TAG = "mytag";

    public ListViewAdapter(Context context, List<String> urls) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.urls = urls;
    }

    @Override
    public int getCount() {
        return urls.size();
    }

    @Override
    public Object getItem(int position) {
        return urls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        convertView = inflater.inflate(R.layout.item_image, null);
        viewHolder = new ViewHolder();
        viewHolder.imageView = (ImageView) convertView.findViewById(R.id.picture);
        String url = urls.get(position);
        Log.d(TAG, "getView: " + url);

        Picasso.with(context)
                .load(url)
                .into(viewHolder.imageView);
        ViewCompat.setTransitionName(viewHolder.imageView,url);
        return convertView;
    }

    private class ViewHolder {
        public ImageView imageView;
    }
}
