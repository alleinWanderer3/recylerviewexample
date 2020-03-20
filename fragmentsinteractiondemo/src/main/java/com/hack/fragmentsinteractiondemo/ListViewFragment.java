package com.hack.fragmentsinteractiondemo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;


public class ListViewFragment extends Fragment {

    public ListViewFragment() {
        // Required empty public constructor
    }


    public static ListViewFragment newInstance() {
        ListViewFragment fragment = new ListViewFragment();
        Bundle args = new Bundle();
       /* args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);*/
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_todel, container, false);
        ListView listView = v.findViewById(R.id.listview);
        String[] data = getContext().getResources().getStringArray(R.array.data);
       // ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,data);
        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
        for(int i=0; i< data.length;++i){
            HashMap<String,String> map = new HashMap<String, String>();
            map.put("id",""+i+1);
            map.put("descr",data[i]);
            arrayList.add(map);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(),
                arrayList,
                R.layout.my_list_item,new String[]{"id","descr"},new int[]{R.id.tv_position,R.id.tv_decription});
        listView.setAdapter(simpleAdapter);

        return v;


    }
}
