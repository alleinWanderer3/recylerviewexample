package com.hack.fragmentsinteractiondemo;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class RListFragment extends Fragment {

    public RListFragment() {
        // Required empty public constructor
    }
    public interface ListItemClickListener{
        public void onListItemClicked(int position, User user);
    }
    private ListItemClickListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(getActivity() instanceof LoginFragment.loginFragmentListener){
            mListener = (ListItemClickListener) getActivity();
        } else {
            throw  new RuntimeException("Host Acivity must implements ListItemClickListener");
        }
    }

    ArrayList<User> getData(){
        String[] names = getContext().getResources().getStringArray(R.array.data);
        ArrayList<User> data = new ArrayList<>();
        Random rnd = new Random();
        for(int i=0;i<names.length;++i){
            data.add(new User(names[i],i+1,rnd.nextBoolean()));
        }
        return data;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_r_list, container, false);
        RecyclerView recyclerView = v.findViewById(R.id.rc_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        MyAdapter adapter = new MyAdapter(getData(),mListener);
        recyclerView.setAdapter(adapter);
        
        return v;
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        private ListItemClickListener mListener;
        ArrayList<User> mData;

        public MyAdapter(ArrayList<User> data, ListItemClickListener listItemClickListener) {
            mData = data;
            mListener = listItemClickListener;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemview = getLayoutInflater().inflate(R.layout.my_list_item, parent, false);
            return new MyViewHolder(itemview);
        }

        @Override
        public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
            holder.tv_id.setText(""+mData.get(position).id);
            holder.tv_name.setText(mData.get(position).name);

            if (mData.get(position).isMale) {
                holder.imageView.setImageResource(android.R.drawable.ic_lock_lock);
            } else {
                holder.imageView.setImageResource(android.R.drawable.ic_input_add);
            }
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mData.get(position).selected) {
                        holder.tv_name.setBackgroundColor(Color.RED);
                    } else {
                        holder.tv_name.setBackgroundColor(Color.BLUE);
                    }
                    mData.get(position).selected = !mData.get(position).selected;
                }
            });
            holder.tv_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onListItemClicked(position, mData.get(position));
                }
            });
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tv_id, tv_name;
            ImageView imageView;


            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                tv_id = itemView.findViewById(R.id.tv_position);
                tv_name = itemView.findViewById(R.id.tv_decription);
                imageView = itemView.findViewById(R.id.iv_logo);
            }
        }
    }

}
