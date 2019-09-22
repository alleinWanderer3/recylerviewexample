package com.example.all_app_example;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.all_app_example.data.Animal;

import java.util.List;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.AnimalVH> {
    List<Animal> animals;
    int rowIndex = -1;
    OnItemClickListener listener;
    public AnimalAdapter(OnItemClickListener listener){
        this.listener = listener;
    }
    public void setAnimals(List<Animal> animalList){
        this.animals = animalList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AnimalVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
        return new AnimalVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalVH holder, final int position) {
        if(animals == null) return;
        holder.tv_title.setText(animals.get(position).name);
        holder.tv_description.setText(animals.get(position).description);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rowIndex = position;
                if(listener!=null) listener.onItemClick(position,animals.get(position));
            }
        });
        if(position == rowIndex){
            holder.itemView.setBackgroundColor(Color.GRAY);
        } else {
            holder.itemView.setBackgroundColor(Color.WHITE);

        }
    }

    @Override
    public int getItemCount() {

        return animals == null?0:animals.size();
    }

    public class AnimalVH extends RecyclerView.ViewHolder{
        public TextView tv_title, tv_description;
        public View itemView;
        public AnimalVH(@NonNull View itemView) {
            super(itemView);
            tv_description = itemView.findViewById(R.id.tv_description);
            tv_title = itemView.findViewById(R.id.tv_title);
            this.itemView = itemView;
        }
    }
    public interface OnItemClickListener{
        public void onItemClick(int position, Animal animal);
    }
}
