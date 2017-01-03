package com.tringapps.dummy;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class CustomAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    Bitmap[] bitmap;


    public CustomAdapter(MainActivity mainActivity) {

        context = mainActivity;
        bitmap = new Bitmap[10];

    }



    @Override
    public int getCount() {

        return 10;
    }

    public void addBitmap(int position , Bitmap bitmap) {

        this.bitmap[position] = bitmap;
        notifyDataSetChanged();

    }

    @Override
    public Object getItem(int i) {

        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View rowView = view;
        MyHolderClass holderTag;


        if (view == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.list_view, null);
            holderTag = new MyHolderClass(rowView);
            rowView.setTag(holderTag);


        } else {
            holderTag = (MyHolderClass) rowView.getTag();

        }

        if(bitmap[i] != null) {
            holderTag.imageV.setImageBitmap(bitmap[i]);

        } else {
            holderTag.imageV.setImageResource(R.mipmap.ic_launcher);

        }
        return rowView;
    }

    private class MyHolderClass {
        ImageView imageV;

        public MyHolderClass(View rowView) {

            imageV = (ImageView) rowView.findViewById(R.id.imageView);


        }
    }


}