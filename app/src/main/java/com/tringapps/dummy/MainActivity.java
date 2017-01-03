package com.tringapps.dummy;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.widget.ListView;

public class MainActivity extends Activity {


    private static String[] url = {
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ5zMJSxs75XUMZo3qVrkCcJbMK11TyGph_BC0Z34UBPgDrT2Nj7Q",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTsSKLt4iKH3QPxeZFYmxwOVKZl84p0t1EQVvBMR5OlRbJioE_f",
            "http://cdn.wallpapersafari.com/18/56/uyVhFe.jpg",
            "http://img.freepik.com/free-photo/nature-design-with-bokeh-effect_1048-1882.jpg?size=338&ext=jpg",
            "https://www.colourbox.com/preview/1255587-one-blue-flower-isolated-on-white-background-close-up-studio-photography.jpg",
            "http://splitsea.tours/wp-content/uploads/2016/09/l1ZF597.jpg",
            "http://www.newstatesman.com/sites/default/files/styles/nodeimage/public/blogs_2015/06/sean-bean.jpg?itok=zDluvo7Y",
            "http://cdn.inquisitr.com/wp-content/uploads/2016/07/Game-of-THrones-winter-is-coming-wallpaper-via-wallpapercraze-670x377.jpeg",
            "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcRtz0ECkkux5ChUqyuSQgTlk9TEGCBFOOOyMvJe8YI6uhDOL2TC",
            "http://www.hdwallpapers.in/walls/daenerys_stormborn_game_of_thrones-wide.jpg"};

    Bitmap bitmap;
    ListView lv;
    private LruCache<String, Bitmap> mMemoryCache;
    CustomAdapter adapter;
    Bitmap bitmap1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        int cacheSize = maxMemory / 4;
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {

            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getByteCount() / 1024;

            }


        };


        lv = (ListView) findViewById(R.id.list);
        adapter = new CustomAdapter(this);
        lv.setAdapter(adapter);
        for (int i = 0; i < url.length; i++) {
            new ImageDownload(i).execute();

        }

    }

    private class ImageDownload extends AsyncTask<String, String, Bitmap> {
        private static final String TAG = "yhy";
        private int mPosition;

        public ImageDownload(int positon) {
            mPosition = positon;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();




        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            HttpHandler sh = new HttpHandler();

            if (getBitmapFromMemCache(url[mPosition]) == null) {

                bitmap = sh.makeServiceCall(url[mPosition]);
                addBitmapToCache(url[mPosition],bitmap);
            }
            /*bitmap = sh.makeServiceCall(url[mPosition]);
            addBitmapToCache(url[mPosition],bitmap);*/
            else
            {
                bitmap = getBitmapFromMemCache(url[mPosition]);

            }



            return null;
        }

        @Override
        protected void onPostExecute(Bitmap jsonObject) {
            super.onPostExecute(jsonObject);

           /* bitmap1 = getBitmapFromMemCache(url[mPosition]);

            adapter.addBitmap(mPosition,bitmap1);*/
            adapter.addBitmap(mPosition,bitmap);


        }


        public void addBitmapToCache(String key, Bitmap bitmap) {

            if (getBitmapFromMemCache(key) == null) {
                mMemoryCache.put(key, bitmap);

            }

        }

        public Bitmap getBitmapFromMemCache(String key) {
            return mMemoryCache.get(key);
        }


    }

}