package com.example.sebastian.ngara;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etemail,etpassword;
    private Button login;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etemail=(EditText)findViewById(R.id.etemail);
        etpassword=(EditText)findViewById(R.id.etpassword);
        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    private void login() {
        String email=etemail.getText().toString();
        String password=etpassword.getText().toString();
        String type="logins";
            Backgroundtask backgroundtask=new Backgroundtask(this);
            backgroundtask.execute(type,email,password);
    }

    @Override
    public void onClick(View v) {
        if (v == login) {
            login();
        }

        if(v == register){
            register();
        }
    }

    private void register() {

        startActivity(new Intent(this,Register.class));
    }

}
