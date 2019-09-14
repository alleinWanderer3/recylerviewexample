package com.example.recyclerviewexampleapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class List2Activity extends AppCompatActivity implements MyAdapter2.OnMyDataEditListener {
    ArrayList<MyData> data;
    LiveData<List<MyData>> liveData;
    MyAdapter2 adapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list2);
        RecyclerView recyclerView = findViewById(R.id.rc_view);
        liveData = MyApp.getDataDataBase(this).getMyDataDAO().getAll();
        liveData.observe(this, new Observer<List<MyData>>() {
            @Override
            public void onChanged(List<MyData> myData) {
                data = new ArrayList<>(myData);
                adapter2.setData(data);
            }
        });
        data = new ArrayList<>();
        adapter2 = new MyAdapter2(data);
        recyclerView.setAdapter(adapter2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter2.setOnMyDataEditListener(this);

    }


    @Override
    public void OnEditData(ArrayList<MyData> data, int position) {
        Intent intent = new Intent(this, EditActivity.class);
        MyData item = data.get(position);
        intent.putExtra("name", item.name);
        intent.putExtra("surname", item.surname);
        intent.putExtra("type", item.type);
        intent.putExtra("position", position);
        startActivityForResult(intent, 1111);
    }

    @Override
    public void OnLoadNeeds(final ArrayList<MyData> data) {
       /* final View pb = findViewById(R.id.pb);
        pb.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                data.addAll(MyData.dataGenerator(getApplicationContext(), 0));
                if (adapter2 != null) adapter2.setLoaded(30);
                pb.setVisibility(View.GONE);
            }
        }, 2000);*/

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1111 && resultCode == RESULT_OK && data != null) {
            String name = data.getStringExtra("name");
            String surname = data.getStringExtra("surname");
            int type = data.getIntExtra("type", 0);
            int pos = data.getIntExtra("position", -1);
            MyData item = this.data.get(pos);
            item.name = name;
            item.surname = surname;
            item.type = type;
            adapter2.notifyItemChanged(pos);

        } else if(requestCode == 2222 && resultCode == RESULT_OK && data != null){
            String name = data.getStringExtra("name");
            String surname = data.getStringExtra("surname");
            int type = data.getIntExtra("type", 0);
            MyData item = new MyData(name,surname,1111,type);
            List2Activity.this.data.add(0,item);
            adapter2.notifyItemInserted(0);
            adapter2.notifyItemRangeChanged(0,adapter2.getItemCount());
            //adapter2.notifyDataSetChanged();
        }
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, EditActivity.class);
        startActivityForResult(intent, 2222);
    }

    @Override
    protected void onDestroy() {
        MyApp.data = data;
        startService(new Intent(this,MyService.class));
        super.onDestroy();
    }
}
