package com.example.recyclerviewexampleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Intent intent = getIntent();
        final EditText ed_name = findViewById(R.id.ed_name);
        final EditText ed_surname = findViewById(R.id.ed_surname);
        final Spinner spinner = findViewById(R.id.sp_types);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.types));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(3);
        findViewById(R.id.ib_edit_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(getIntent().getExtras()!=null) {
                    String name = ed_name.getText().toString();
                    String surname = ed_surname.getText().toString();
                    int type = spinner.getSelectedItemPosition();
                    if (name.equals("") || surname.equals("")) {
                        Toast.makeText(EditActivity.this, "fill all data", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Intent intent = new Intent();
                    intent.putExtra("name", name);
                    intent.putExtra("surname", surname);
                    intent.putExtra("type", type);

                    intent.putExtra("position", getIntent().getExtras().getInt("position"));
                    setResult(RESULT_OK, intent);
                } else {
                    String name = ed_name.getText().toString();
                    String surname = ed_surname.getText().toString();
                    int type = spinner.getSelectedItemPosition();
                    if (name.equals("") || surname.equals("")) {
                        Toast.makeText(EditActivity.this, "fill all data", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Intent intent = new Intent();
                    intent.putExtra("name", name);
                    intent.putExtra("surname", surname);
                    intent.putExtra("type", type);
                    setResult(RESULT_OK, intent);
                }
                EditActivity.this.finish();
            }
        });

        if(intent!=null && intent.getExtras()!=null){
            ed_name.setText(intent.getExtras().getString("name",""));
            ed_surname.setText(intent.getExtras().getString("surname",""));
            spinner.setSelection(intent.getExtras().getInt("type",3));

        }
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        super.onBackPressed();

    }
}
