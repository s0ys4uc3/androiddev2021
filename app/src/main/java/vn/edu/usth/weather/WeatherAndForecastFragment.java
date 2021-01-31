package vn.edu.usth.weather;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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