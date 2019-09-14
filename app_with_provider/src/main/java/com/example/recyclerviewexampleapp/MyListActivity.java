package com.example.recyclerviewexampleapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

import static androidx.recyclerview.widget.ItemTouchHelper.LEFT;
import static androidx.recyclerview.widget.ItemTouchHelper.RIGHT;

public class MyListActivity extends AppCompatActivity implements MyAdapter.OnEditDeleteListaner {
    final int CODE_EDIT = 1111;
    ArrayList<MyData> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rv = findViewById(R.id.rc_view);
        if(savedInstanceState!= null){
            data = savedInstanceState.getParcelableArrayList("data");
        } else {
            data = MyData.dataGenerator(this,100);
        }

        RecyclerView.Adapter adapter = new MyAdapter(data);
        prepareListLayoutManager();
        prepareListener(rv);
        //simple default decorator
        //rv.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));

        //simple custom decorators
        DividerItemDecoration decoration_horizont = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        decoration_horizont.setDrawable(getDrawable(R.drawable.divider_res));
        DividerItemDecoration decoration_vert = new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL);
        decoration_vert.setDrawable(getDrawable(R.drawable.divider_res_vert));
        rv.addItemDecoration(decoration_horizont);
        rv.addItemDecoration(decoration_vert);
        //animation
        rv.setItemAnimator(new DefaultItemAnimator());

        ((MyAdapter) adapter).setOnEditDeleteListener(this);
        rv.setAdapter(adapter);
    }
    public void prepareListener(final RecyclerView rv){
        ItemTouchHelper.SimpleCallback itSimpleCallback = new ItemTouchHelper.SimpleCallback(0, RIGHT|LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if (direction==RIGHT){
                    int pos = viewHolder.getAdapterPosition();
                    data.remove(pos);
                    rv.getAdapter().notifyItemRemoved(pos);
                } else if(direction == LEFT){
                    int pos = viewHolder.getAdapterPosition();
                    data.remove(pos);
                    rv.getAdapter().notifyItemRemoved(pos);
                }
            }
        };
        new ItemTouchHelper(itSimpleCallback).attachToRecyclerView(rv);
    }
    public void prepareListLayoutManager(){
        final RecyclerView rv = findViewById(R.id.rc_view);
        RadioGroup rg = findViewById(R.id.rg_layouts);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                switch (id){
                    case R.id.rb_list_vertical: rv.setLayoutManager(new LinearLayoutManager(MyListActivity.this,RecyclerView.VERTICAL,false));
                    break;
                    case R.id.rb_list_horizont: rv.setLayoutManager(new LinearLayoutManager(MyListActivity.this,RecyclerView.HORIZONTAL,false));
                        break;
                    case R.id.rb_grid_vertical: rv.setLayoutManager(new GridLayoutManager(MyListActivity.this,2,RecyclerView.VERTICAL,false));
                        break;
                    case R.id.rb_grid_horizont: rv.setLayoutManager(new GridLayoutManager(MyListActivity.this,3,RecyclerView.HORIZONTAL,false));
                        break;
                }
            }
        });
        RadioButton rb = findViewById(rg.getCheckedRadioButtonId());
        rg.clearCheck();
        rb.setChecked(true);
    }

    @Override
    public void onEdit(ArrayList<MyData> data, int position) {
        Intent editIntent = new Intent(this, EditActivity.class);
        editIntent.putExtra("name",data.get(position).name);
        editIntent.putExtra("surname",data.get(position).surname);
        editIntent.putExtra("type",data.get(position).type);
        editIntent.putExtra("position",position);

        startActivityForResult(editIntent, CODE_EDIT);
    }

    @Override
    public void onDelete(ArrayList<MyData> data, int position) {
        data.remove(position);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("data",data);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode == CODE_EDIT){
            RecyclerView rv = findViewById(R.id.rc_view);
            int position = data.getIntExtra("position",-1);
            if (position== -1) return;
            MyData item = ((MyAdapter)rv.getAdapter()).getData().get(position);
            item.name = data.getStringExtra("name");
            item.surname = data.getStringExtra("surname");
            item.type = data.getIntExtra("type",4);
            rv.getAdapter().notifyItemChanged(position);
            //rv.getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.mi_start_list){
            startActivity(new Intent(this,List2Activity.class));
            return  true;
        }
        return super.onOptionsItemSelected(item);

    }
}
