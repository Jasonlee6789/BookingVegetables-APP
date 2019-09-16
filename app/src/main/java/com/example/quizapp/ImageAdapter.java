package com.example.quizapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

    public static  int[] image_Id = {R.drawable.s1, R.drawable.s2, R.drawable.s3, R.drawable.s4};
    public static String[] packNames = {"Sep-1st","Sep-2nd","Sep-3rd","Sep-4th"};

    Context ctx;

    public ImageAdapter(Context ctx) { this.ctx = ctx; }

    @Override
    public int getCount() {
        return image_Id.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView img;
        if (view == null) {
            img = new ImageView(ctx);
            img.setLayoutParams(new GridView.LayoutParams(470,470));
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            img.setPadding(8,8,8,8);
        }  else {
            img = (ImageView) view;
        }
        img.setImageResource(image_Id[i]);
        return img;
    }
}
