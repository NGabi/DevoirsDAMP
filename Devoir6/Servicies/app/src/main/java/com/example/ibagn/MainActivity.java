package com.example.ibagn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.ibagn.servicies.R;

public class MainActivity extends AppCompatActivity {
    Button start, end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start=(Button)findViewById(R.id.start);
        end=(Button)findViewById(R.id.end);
        start.onC




    }

}
