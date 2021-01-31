package vn.edu.usth.weather;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ForecastFragment extends Fragment {
    private String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun", "Mon", "Tue", "Wed"};
    private String[] temps = {"24C - 31C", "24C - 30C", "22C - 23C", "22C - 27C", "22C - 30C", "24C - 31C", "25C - 28C", "24C - 27C", "24C - 26C", "23C - 27C"};

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    protected String mParam1;
    protected String mParam2;

    public ForecastFragment() {
        // Required empty public constructor
    }

    public static ForecastFragment newInstance(String param1, String param2) {
        ForecastFragment fragment = new ForecastFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
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
        View view = inflater.inflate(R.layout.fragment_forecast, container, false);
        String[] conditions = {getResources().getString(R.string.oomunekumori), getResources().getString(R.string.showers), getResources().getString(R.string.ame), getResources().getString(R.string.sshowers), getResources().getString(R.string.oomunekumori), getResources().getString(R.string.tkdkkumori), getResources().getString(R.string.raiu), getResources().getString(R.string.sraiu), getResources().getString(R.string.showers), getResources().getString(R.string.sraiu)};
        Log.d("zzz", conditions.toString());
//        view.setBackgroundColor(getResources().getColor(R.color.colorDrop));

        for(int i = 0; i < days.length; i++){
            addDay(days[i], conditions[i], temps[i]);
        }
        return view;
    }

    void addDay(String date, String condi, String temps){
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        DayFragment f = new DayFragment();
        Bundle args = new Bundle();
        args.putString("jour", date);
        args.putString("wcondition", condi);
        args.putString("temp", temps);
        f.setArguments(args);
        ft.add(R.id.custombg, f).commit();
    }
}