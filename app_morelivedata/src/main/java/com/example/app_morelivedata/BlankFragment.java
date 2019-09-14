package com.example.app_morelivedata;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.chip.Chip;


public class BlankFragment extends Fragment implements Observer<String> {
    MutableLiveData<String> liveData;


    public BlankFragment() {
        // Required empty public constructor
    }


    public static BlankFragment newInstance() {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof Main2Activity){
            liveData = ((Main2Activity)context).live_string;
        }
    }

    @Override
    public void onDetach() {
        liveData = null;
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_blank, container, false);
        Chip chip = v.findViewById(R.id.chip);
        chip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                liveData.setValue(liveData.getValue()+"A!");
            }
        });
        chip.setOnCloseIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Duncan MacLeod mode is on!!",Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }

    @Override
    public void onChanged(String s) {
        if(getView()!=null){
            ((TextView)getView().findViewById(R.id.tv_fragment)).setText(s);
        }
    }
}
