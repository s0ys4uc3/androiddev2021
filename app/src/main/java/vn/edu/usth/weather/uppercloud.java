package vn.edu.usth.weather;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class uppercloud extends Fragment {

    private static final String ARG_PARAM1 = "city name";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public uppercloud() {
        // Required empty public constructor
        super(R.layout.fragment_uppercloud);
    }

    public static uppercloud newInstance(String param1, String param2) {
        uppercloud fragment = new uppercloud();
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
        // Inflate the overflowMenu for this fragment
        View view = inflater.inflate(R.layout.fragment_uppercloud, container, false);
        TextView text = view.findViewById(R.id.fururin);
        text.setText(mParam1);
        view.setBackgroundColor(getResources().getColor(R.color.colorDrop));
        return view;
    }
}