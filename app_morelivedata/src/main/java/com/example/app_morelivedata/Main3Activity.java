package com.example.app_morelivedata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static java.lang.Thread.sleep;

public class Main3Activity extends AppCompatActivity {
    MutableLiveData<Integer> liveData = new MutableLiveData<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        liveData.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer s) {
                ((TextView)findViewById(R.id.tv_progress)).setText(""+s);
            }
        });
    }

    public void runThread(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 10; ++i){
                    try {
                        sleep(500);
                        liveData.postValue(i+1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
