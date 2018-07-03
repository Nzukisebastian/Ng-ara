package com.example.sebastian.ngara;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main4Activity extends AppCompatActivity {

    Button click;
    public static TextView data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        //click=(Button)findViewById(R.id.button);
        //request();
        data=(TextView)findViewById(R.id.fetchdata);
        //click.setOnClickListener(new View.OnClickListener()
    }

    public void image(View view){

        startActivity(new Intent(this, Main2Activity.class));
    }
    public void info(View view){

        startActivity(new Intent(this,Uploadimage.class));
    }
}

