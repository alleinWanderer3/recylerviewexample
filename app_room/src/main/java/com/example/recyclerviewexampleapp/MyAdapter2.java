package com.example.recyclerviewexampleapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.MyViewHolder> {
    private ArrayList<MyData> data;


    /**
     * interface to bind with edit
     */
    public interface OnMyDataEditListener{
        public void OnEditData(ArrayList<MyData> data, int position);
        public void OnLoadNeeds(ArrayList<MyData> data);
    }

    public MyAdapter2(ArrayList<MyData> data) {
        this.data = data;
        isLoadRunnned = false;
        last_position = 0;
    }
    public void setData(ArrayList<MyData> data){
        this.data = data;
        notifyDataSetChanged();
    }


    private OnMyDataEditListener onMyDataEditListener;
    public void setOnMyDataEditListener(OnMyDataEditListener onMyDataEditListener) {
        this.onMyDataEditListener = onMyDataEditListener;
    }

    @NonNull
    @Override
    public MyAdapter2.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item,parent,false);
        MyAdapter2.MyViewHolder viewHolder = new MyViewHolder(itemview);
        return viewHolder;
    }
    private int last_position;
    private boolean isLoadRunnned;
    public void setLoaded(int count){
        isLoadRunnned = false;
       MyAdapter2.this.notifyItemRangeInserted(data.size()-1,count);
    }
    @Override
    public void onBindViewHolder(@NonNull MyAdapter2.MyViewHolder holder, final int position) {
        MyData item = data.get(position);
        holder.tv_name.setText(item.name);
        holder.tv_surname.setText(item.surname);
        switch (item.type){
            case 0: holder.iv_type.setImageResource(R.drawable.ic_coder);
            break;
            case 1: holder.iv_type.setImageResource(R.drawable.ic_designer);
            break;
            case 2: holder.iv_type.setImageResource(R.drawable.ic_chief);
            break;
            default:
                holder.iv_type.setImageResource(R.drawable.ic_default);
        }
        holder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMyDataEditListener.OnEditData(data,position);
            }
        });
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.remove(position);
                //MyAdapter2.this.notifyDataSetChanged();
                MyAdapter2.this.notifyItemRemoved(position);
                MyAdapter2.this.notifyItemRangeChanged(position,data.size()-position);

            }
        });

        if(!isLoadRunnned &&(position> getItemCount() - 10) && (position - last_position > 0)){
            isLoadRunnned = true;
            onMyDataEditListener.OnLoadNeeds(data);
        }
        last_position = position;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name, tv_surname;
        public ImageButton btn_edit, btn_delete;
        public ImageView iv_type;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_surname = itemView.findViewById(R.id.tv_surname);
            btn_delete = itemView.findViewById(R.id.btn_delete);
            btn_edit = itemView.findViewById(R.id.btn_edit);
            iv_type = itemView.findViewById(R.id.iv_type);
        }
    }
}
