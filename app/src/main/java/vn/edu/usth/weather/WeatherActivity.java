package vn.edu.usth.weather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
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
import com.google.android.material.tabs.TabLayoutMediator;


public class WeatherActivity extends AppCompatActivity {
    public String[] titles = {"Hanoi", "Paris", "Toulouse"};
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.overmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle item selection
        switch (item.getItemId()) {
            case R.id.rafraichir:
                Toast.makeText(getApplicationContext(), "Refreshing", Toast.LENGTH_LONG).show();
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        Toast.makeText(getApplicationContext(), "onCreate()", Toast.LENGTH_SHORT).show();
        HomePagerAdapter adapter = new HomePagerAdapter(this);
        ViewPager2 pager = findViewById(R.id.pager);
        TabLayout tabLayout = findViewById(R.id.tabLo);
        pager.setOffscreenPageLimit(3);
        pager.setAdapter(adapter);
        new TabLayoutMediator(tabLayout, pager,
                (tab, position) -> tab.setText(titles[position])
        ).attach();
//        InputStream is = getApplicationContext().getResources().openRawResource(R.raw.aqside);
        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.start);
        mediaPlayer.start(); // no need to call prepare(); create() does that for you

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

    public class HomePagerAdapter extends FragmentStateAdapter {
        public HomePagerAdapter(@NonNull FragmentActivity fa){
            super(fa);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position){
            WeatherAndForecastFragment frag = new WeatherAndForecastFragment();
            Bundle args = new Bundle();
            args.putString("city name", titles[position]);
            frag.setArguments(args);
            return frag;
        }


        @Override
        public int getItemCount() {return 3;}
    }

}