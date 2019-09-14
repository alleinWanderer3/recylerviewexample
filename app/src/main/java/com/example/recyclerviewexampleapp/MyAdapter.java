package com.example.recyclerviewexampleapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<MyData> data;
    private OnEditDeleteListaner listaner;
    public interface  OnEditDeleteListaner{
        public void onEdit(ArrayList<MyData> data, int position);
        public void onDelete(ArrayList<MyData> data, int position);

    }
    public MyAdapter(ArrayList<MyData> data) {
        this.data = data;
    }

    public ArrayList<MyData> getData(){
        return data;
    }

    public void setOnEditDeleteListener(OnEditDeleteListaner listener){
        this.listaner = listener;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        MyData item = data.get(position);
        holder.tv_name.setText(item.name);
        holder.tv_surname.setText(item.surname);
        switch (item.type){
            case 0: holder.iv_type.setImageResource(R.drawable.ic_coder);
            break;
            case 1:holder.iv_type.setImageResource(R.drawable.ic_designer);
            break;
            case 2:holder.iv_type.setImageResource(R.drawable.ic_chief);
                break;
                default:
                    holder.iv_type.setImageResource(R.drawable.ic_default);
        }
        holder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listaner!=null){
                    listaner.onEdit(data,position);
                }
            }
        });
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listaner!=null){
                    listaner.onDelete(data,position);
                    MyAdapter.this.notifyItemRemoved(position);
                    MyAdapter.this.notifyItemRangeChanged(position,data.size()-position);

                    //MyAdapter.this.notifyDataSetChanged();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv_name, tv_surname;
        ImageView  iv_type;
        ImageButton btn_edit, btn_delete;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_surname = itemView.findViewById(R.id.tv_surname);
            iv_type = itemView.findViewById(R.id.iv_type);
            btn_delete = itemView.findViewById(R.id.btn_delete);
            btn_edit = itemView.findViewById(R.id.btn_edit);
        }
    }
}
