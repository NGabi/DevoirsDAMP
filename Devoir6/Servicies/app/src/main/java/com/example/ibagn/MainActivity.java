package com.example.ibagn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.example.ibagn.servicies.R;

public class MainActivity extends AppCompatActivity {
    ImageButton start, end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start=(ImageButton)findViewById(R.id.start);
        end=(ImageButton)findViewById(R.id.end);
        start.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {
                //Create the intent to start another activity
                Intent inte =new Intent(getBaseContext(),MyService.class);
                startService(inte);

            }
        });
        end.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {
                //Create the intent to start another activity
                Intent inte =new Intent(getBaseContext(),MyService.class);
                startService(inte);

            }
        });




    }

}
