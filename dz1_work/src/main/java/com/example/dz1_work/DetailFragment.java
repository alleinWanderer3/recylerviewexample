package com.example.dz1_work;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dz1_work.data.Animal;
import com.example.dz1_work.data.AnimalViewModel;
import com.example.dz1_work.data.Repository;


public class DetailFragment extends Fragment {
    AnimalViewModel animalViewModel;
    private static final String ARG_NAME = "name";
    private static final String ARG_DESCRIPTION = "descr";
    private static final String ARG_POSITION = "pos";
    private String mName;
    private String mDescription;
    private int mPosition;


    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment newInstance(String name, String description, int position) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, name);
        args.putString(ARG_DESCRIPTION, description);
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        animalViewModel = ViewModelProviders.of(getActivity()).get(AnimalViewModel.class);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mName = getArguments().getString(ARG_NAME);
            mDescription = getArguments().getString(ARG_DESCRIPTION);
            mPosition = getArguments().getInt(ARG_POSITION);
        } else {
            mName = "";
            mDescription = "";
            mPosition = -1;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail, container, false);
        final EditText ed_name = v.findViewById(R.id.ed_name);
        ed_name.setText(mName);
        final EditText ed_descr = v.findViewById(R.id.ed_description);
        ed_descr.setText(mDescription);
        Button btn_edit_ok = v.findViewById(R.id.btn_detail_ok);
        btn_edit_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ed_name.getText().toString().equals("")|| ed_descr.getText().toString().equals("")){
                    Toast.makeText(getContext(),"fill fields",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(mPosition == -1) {
                    animalViewModel.insert(new Animal(ed_name.getText().toString(),
                            ed_descr.getText().toString()));
                } else {
                    animalViewModel.update(new Animal(ed_name.getText().toString(),
                            ed_descr.getText().toString()));
                }

                if (getActivity().findViewById(R.id.container_detail) == null) {
                    getFragmentManager().popBackStack();
                }
            }
        });
        return v;
    }


}
