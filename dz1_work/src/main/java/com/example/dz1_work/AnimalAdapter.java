package com.example.dz1_work;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dz1_work.data.Animal;

import java.util.List;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder> {
    List<Animal> animals;
    int rowIndex = -1;

    OnItemClickListener listener;
    public AnimalAdapter(OnItemClickListener listener){
        this.listener = listener;
    }

    public int getSelectedIndex(){
        return rowIndex;
    }
    void setData(List<Animal> animals){
        this.animals = animals;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public AnimalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new AnimalViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final AnimalViewHolder holder, final int position) {
        holder.tv_name.setText(animals.get(position).name);
        holder.tv_description.setText(animals.get(position).description);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimalAdapter.this.notifyItemChanged(rowIndex);
                rowIndex = position;
                listener.onItemClick(position,animals.get(position));
                holder.itemView.setBackgroundColor(Color.RED);
            }
        });
        if(rowIndex == position){
            holder.itemView.setBackgroundColor(Color.RED);
        } else {
            holder.itemView.setBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public int getItemCount() {
        if(animals!=null){
            return animals.size();
        }
        return 0;
    }

    public class AnimalViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_name, tv_description;
        public AnimalViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_description = itemView.findViewById(R.id.tv_description);
            tv_name = itemView.findViewById(R.id.tv_name);

        }
    }
    public interface OnItemClickListener{
        void onItemClick(int position, Animal animal);
    }
}
