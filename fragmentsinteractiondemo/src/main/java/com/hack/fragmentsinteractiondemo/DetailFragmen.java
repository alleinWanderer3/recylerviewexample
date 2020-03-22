package com.hack.fragmentsinteractiondemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ToggleButton;

import androidx.fragment.app.Fragment;



public class DetailFragmen extends Fragment {

    private static final String ARG_NAME = "name";
    private static final String ARG_ID = "id";
    private static final String ARG_SEX = "sex";

    private String mName;
    private int mId;
    private boolean mIsMale;

    public DetailFragmen() {
        // Required empty public constructor
    }

    public static DetailFragmen newInstance(String name, int id, boolean isMale) {
        DetailFragmen fragment = new DetailFragmen();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, name);
        args.putInt(ARG_ID, id);
        args.putBoolean(ARG_SEX,isMale);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mName = getArguments().getString(ARG_NAME);
            mId = getArguments().getInt(ARG_ID);
            mIsMale = getArguments().getBoolean(ARG_SEX);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_detail, container, false);
        EditText ed_name = v.findViewById(R.id.ed_name);
        EditText ed_id = v.findViewById(R.id.ed_id);
        ToggleButton tb_sex = v.findViewById(R.id.tb_sex);
        ed_id.setText(""+mId);
        ed_name.setText(mName);
        tb_sex.setChecked(mIsMale);
        return v;
    }
}
