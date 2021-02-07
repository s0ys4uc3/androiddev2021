package vn.edu.usth.weather;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherAndForecastFragment extends Fragment {

    private static final String ARG_PARAM1 = "city name";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public WeatherAndForecastFragment() {
        // Required empty public constructor
        super(R.layout.fragment_weather_and_forecast);
    }

    public static WeatherAndForecastFragment newInstance() {
        WeatherAndForecastFragment fragment = new WeatherAndForecastFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.overmenu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //handle menu item clicks
        int id = item.getItemId();

        if (id == R.id.rafraichir) {
            TextView temp = getView().findViewById(R.id.wtemp);
            TextView tempdes = getView().findViewById(R.id.wtempdes);
            String tempURL = "http://api.openweathermap.org/data/2.5/weather?q=" + mParam1 + "&appid=7e29cda89b3ee0d5e152ae41598e7bdb&units=metric";
            Toast.makeText(getActivity(), "Refreshing", Toast.LENGTH_LONG).show();
            RequestQueue queue = Volley.newRequestQueue(getContext());
            JsonObjectRequest jsreq = new JsonObjectRequest(Request.Method.GET,
                    tempURL, null,
                    (Response.Listener<JSONObject>) response -> {
                        try {
                            JSONObject outtemp = response.getJSONObject("main");
                            temp.setText(outtemp.getString("temp"));
                            JSONArray tenki = response.getJSONArray("weather");
                            JSONObject wt = tenki.getJSONObject(0);
                            tempdes.setText(wt.getString("main"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    },
                    (Response.ErrorListener) error -> tempdes.setText("Error")
            );
            Response.Listener<Bitmap> listener = response -> {
                LinearLayout ln = (LinearLayout) getView().findViewById(R.id.custombg);
                BitmapDrawable bitimg = new BitmapDrawable(getContext().getResources(), response);
                bitimg.setAlpha(75);
                ln.setBackground(bitimg);
            };
            ImageRequest imageRequest = new ImageRequest(
                    "https://static.wikia.nocookie.net/charlotte-anime/images/8/8c/Charlotte_Promotional_Picture.jpeg/",
                    listener,
                    0,
                    0, ImageView.ScaleType.CENTER,
                    Bitmap.Config.ARGB_8888,
                    null);
            queue.add(imageRequest);
            queue.add(jsreq);
        }
        if (id == R.id.settei) {
            Intent setteiIntent = new Intent(getContext(), PrefActivity.class);
            startActivity(setteiIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the overflowMenu for this fragment
        View view = inflater.inflate(R.layout.fragment_weather_and_forecast, container, false);
        wView(new uppercloud(), mParam1, R.id.kumori);
        wView(new ForecastFragment(), mParam1, R.id.forecast_);
        return view;
    }

    void wView(Fragment frak, String name, int id){
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        Bundle args = new Bundle();
        args.putString("city name", name);
        frak.setArguments(args);

        ft.replace(id, frak).commit();
    }

}