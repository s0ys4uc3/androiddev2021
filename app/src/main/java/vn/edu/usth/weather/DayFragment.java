package vn.edu.usth.weather;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DayFragment extends Fragment {

    private static final String ARG_PARAM1 = "jour";
    private static final String ARG_PARAM2 = "wcondition";
    private static final String ARG_PARAM3 = "temp";

    private String mParam1;
    private String mParam2;
    private String mParam3;

    public DayFragment() {
        // Required empty public constructor
    }

    public static DayFragment newInstance(String param1, String param2) {
        DayFragment fragment = new DayFragment();
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
            mParam3 = getArguments().getString(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_day, container, false);
        TextView jour = view.findViewById(R.id.days);
        TextView condi = view.findViewById(R.id.wconditions);
        TextView temps = view.findViewById(R.id.temps);
        jour.setText(mParam1);
        condi.setText(mParam2);
        temps.setText(mParam3);
        Log.d("FPX", mParam1.toString()+" "+mParam2.toString()+" "+mParam3.toString());
        return view;
    }
}