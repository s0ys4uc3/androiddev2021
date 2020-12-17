package vn.edu.usth.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.io.InputStream;

public class WeatherActivity extends AppCompatActivity {
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