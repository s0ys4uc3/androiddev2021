package vn.edu.usth.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class WeatherActivity extends AppCompatActivity {

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
        pager.setOffscreenPageLimit(3);
        pager.setAdapter(adapter);
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