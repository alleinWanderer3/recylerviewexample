package com.hack.fragmentsinteractiondemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ToggleButton;

import androidx.fragment.app.Fragment;



public class DetailFragmen extends Fragment {

    private static final String ARG_CURRENCY = "currency";
    private static final String ARG_COST = "cost";
    private static final String ARG_VOLUME = "volume";

    private String mCurrency;
    private double mCost;
    private double mVolume;

    public DetailFragmen() {
        // Required empty public constructor
    }

    public static void newInstance(String mCurrency, double mCost, double mVolume){
        DetailFragmen fragment = new DetailFragmen();
        Bundle args = new Bundle();
        args.putString(ARG_CURRENCY, mCurrency);
        args.putDouble(ARG_COST, mCost);
        args.putDouble(ARG_VOLUME, mVolume);
        fragment.setArguments(args);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCurrency = getArguments().getString(ARG_CURRENCY);
            mCost = getArguments().getDouble(ARG_COST);
            mVolume = getArguments().getDouble(ARG_VOLUME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_detail, container, false);
        EditText ed_currency = v.findViewById(R.id.tv_decription);
        EditText ed_cost = v.findViewById(R.id.tv_cost);
        ToggleButton tb_sex = v.findViewById(R.id.tb_sex);
        ed_currency.setText(""+mCurrency);
        ed_cost.setText((int) mCost);
        return v;
    }
}
