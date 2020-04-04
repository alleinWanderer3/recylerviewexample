package com.hack.fragmentsinteractiondemo;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment {
    String mpair_crypto;
    public static MenuFragment newInstance(Pair pair_crypto) {

        Bundle args = new Bundle();
        args.putString("pair_crypto",pair_crypto);
        MenuFragment fragment = new MenuFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public MenuFragment() {
        // Required empty public constructor
    }

       @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            mpair_crypto = getArguments().getString("user");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_menu, container, false);
        TextView tv_user = v.findViewById(R.id.tv_user);
        tv_user.setText(mpair_crypto);
        return v;
    }
}
