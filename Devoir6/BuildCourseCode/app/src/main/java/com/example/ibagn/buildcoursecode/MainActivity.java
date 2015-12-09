package com.example.ibagn.buildcoursecode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView input;
    Button queryCalls;
    Integer number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       input = (TextView) findViewById(R.id.input);


        queryCalls = (Button) findViewById(R.id.query_calls);
        queryCalls.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

                Intent intent = new Intent(MainActivity.this, CallsActivity.class);

                if(input.getText().toString().trim().length()!=0){
                    number=Integer.parseInt(input.getText().toString().trim());
                    intent.putExtra("number",number);



                    MainActivity.this.startActivity(intent);

                }else{
                    Toast.makeText(getBaseContext(),"Input number !",
                            Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
}
