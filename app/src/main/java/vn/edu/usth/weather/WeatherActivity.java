package vn.edu.usth.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class WeatherActivity extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.overmenu, menu);
        return true;
    }
//    final Handler handler = new Handler(Looper.getMainLooper()) {
//        @Override
//        public void handleMessage(Message msg) {
//            //This method is executed in main thread
//            String content = msg.getData().getString("server_response");
//            Toast.makeText(getApplicationContext(), content, Toast.LENGTH_SHORT).show();
//        }
//    };
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle item selection
        switch (item.getItemId()) {
            case R.id.rafraichir:
                Toast.makeText(getApplicationContext(), "Refreshing", Toast.LENGTH_LONG).show();
//                Thread t = new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        //this method run in a worker thread
//                        try {
//                            Thread.sleep(0);
//                        }
//                        catch (InterruptedException e){
//                            e.printStackTrace();
//                        }
//
//                        //Assume that we got our data from server
//                        Bundle bundle = new Bundle();
//                        bundle.putString("server_response", "some sample json here");
//
//                        //notify main thread
//                        Message msg = new Message();
//                        msg.setData(bundle);
//                        handler.sendMessage(msg);
//                    }
//                });
//                t.start();
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

                Response.Listener<Bitmap> listener = new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        LinearLayout ln = (LinearLayout) findViewById(R.id.custombg);
                        BitmapDrawable bitimg = new BitmapDrawable(getApplicationContext().getResources(), response);
                        bitimg.setAlpha(75);
                        ln.setBackground(bitimg);
                    }
                };

                ImageRequest imageRequest = new ImageRequest(
                        "https://static.wikia.nocookie.net/charlotte-anime/images/8/8c/Charlotte_Promotional_Picture.jpeg/",
                        listener,
                        0,
                        0, ImageView.ScaleType.CENTER,
                        Bitmap.Config.ARGB_8888,
                        null);

                queue.add(imageRequest);
                return true;
            case R.id.settei:
                  Intent setteiIntent = new Intent(this, PrefActivity.class);
                startActivity(setteiIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        ForecastFragment firstFragment = new ForecastFragment();
//        uppercloud kumoriFragment = new uppercloud();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        Toast.makeText(getApplicationContext(), "onCreate()", Toast.LENGTH_SHORT).show();
//        getSupportFragmentManager().beginTransaction().replace(R.id.container_,firstFragment).commit();
//        getSupportFragmentManager().beginTransaction().replace(R.id.kumori, kumoriFragment).commit();
        PagerAdapter adapter = new HomePagerAdapter(getSupportFragmentManager());
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLo);
        pager.setOffscreenPageLimit(3);
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);
//        InputStream is = getApplicationContext().getResources().openRawResource(R.raw.aqside);
        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.start);
        mediaPlayer.start(); // no need to call prepare(); create() does that for you

//        AsyncTask<String, Integer, Bitmap> task = new AsyncTask<String, Integer, Bitmap>() {
//            @Override
//            protected void onPreExecute() {
//            }
//            @Override
//            protected Bitmap doInBackground(String... strings) {
////                try {
////                    Thread.sleep(5000);
////                } catch (InterruptedException e) {
////                    e.printStackTrace();
////                }
////                return null;
//                try {
//                    URL url = new URL("https://static.wikia.nocookie.net/charlotte-anime/images/8/8c/Charlotte_Promotional_Picture.jpeg/");
//
//                    HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
//                    connection.setRequestMethod("GET");
//                    connection.setDoInput(true);
//                    connection.connect();
//
//                    int response = connection.getResponseCode();
//                    Log.i("USTHWeather", "Response: " + response);
//                    InputStream is = connection.getInputStream();
//
//                    Bitmap bm = BitmapFactory.decodeStream(is);
//                    LinearLayout logo = (LinearLayout) findViewById(R.id.custombg);
//                    BitmapDrawable bitimg = new BitmapDrawable(getApplicationContext().getResources(), bm);
//                    bitimg.setAlpha(75);
//                    logo.setBackground(bitimg);
//                } catch (MalformedURLException | ProtocolException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                return null;
//            }
//            @Override
//            protected void onProgressUpdate(Integer... values){
//
//            }
//            @Override
//            protected void onPostExecute(Bitmap bitmap){
//                Toast.makeText(getApplicationContext(), "Image downloaded", Toast.LENGTH_SHORT).show();
//            }
//        };
//        task.execute("https://static.wikia.nocookie.net/charlotte-anime/images/8/8c/Charlotte_Promotional_Picture.jpeg/");


    }

    protected void onStart() {
        super.onStart();
        Log.i("Tenki", "Now onStart()");
        Toast.makeText(getApplicationContext(),"onStart()", Toast.LENGTH_LONG).show();
    }

    protected void onResume () {
        super.onResume();
        Log.i("Tenki", "Now onResume()");
        Toast.makeText(getApplicationContext(), "onResume()", Toast.LENGTH_LONG).show();
    }

    protected void onPause () {
        super.onPause();
        Log.i("Tenki", "Now onPause()");
        Toast.makeText(getApplicationContext(), "onPause()", Toast.LENGTH_LONG).show();
    }

    protected void onStop () {
        super.onStop();
        Log.i("Tenki", "onStop()");
        Toast.makeText(getApplicationContext(), "onStop()", Toast.LENGTH_LONG).show();
    }

    protected void onDestroy () {
        super.onDestroy();
        Log.i("Tenki", "onDestroy()");
        Toast.makeText(getApplicationContext(), "onDestroy()", Toast.LENGTH_LONG).show();
    }

    public class HomePagerAdapter extends FragmentPagerAdapter {
        private String titles[] = new String[] {"Hanoi", "Paris", "Toulouse"};
        public HomePagerAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public WeatherAndForecastFragment getItem(int page) {
//            switch (page) {
//                case 0: return WeatherAndForecastFragment.newInstance();
//                case 1: return WeatherAndForecastFragment.newInstance();
//                case 2: return WeatherAndForecastFragment.newInstance();
//            }
            return WeatherAndForecastFragment.newInstance();
        }

        @Override
        public CharSequence getPageTitle(int page) {
            return titles[page];
        }
    }

}