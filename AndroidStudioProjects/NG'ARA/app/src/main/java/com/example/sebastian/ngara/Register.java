package com.example.sebastian.ngara;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

public class Register extends AppCompatActivity implements View.OnClickListener {
    Context context;
    AlertDialog alertDialog;
    ProgressDialog progressDialog;
    ProgressDialog loading;
    // Backgroundtask(Context ctx){
    //  context = ctx;
    EditText etemail,etpassword,etphone,etfullname;
    private Button buttonSubmit;
    //defining AwesomeValidation object
    private AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //VALIDATION OF THE FORM
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        etfullname=(EditText)findViewById(R.id.idfullname);
        etpassword=(EditText)findViewById(R.id.idpassword);
        etphone=(EditText)findViewById(R.id.idphone);
        etemail=(EditText)findViewById(R.id.idemail);
        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);
        Spinner dropdown= (Spinner) findViewById(R.id.spinner1);
        String[]languages=new String[]{"Busia","Kapenguria","Kitale","Mombasa","Nyeri","Nairobi","Nyahururu","Nakuru"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,languages);
        dropdown.setAdapter(adapter);

        //adding validation to edittexts
        awesomeValidation.addValidation(this, R.id.idfullname, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.idemail, Patterns.EMAIL_ADDRESS, R.string.emailerror);
        awesomeValidation.addValidation(this, R.id.idpassword, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.passworderror);
        awesomeValidation.addValidation(this, R.id.idphone, "^[0-9]{2}[0-9]{8}$", R.string.mobileerror);
        buttonSubmit.setOnClickListener(this);



    }
    private void submitForm(){
        //first validate the form then move ahead
        //if this becomes true that means validation is successfull
        if (awesomeValidation.validate()) {
            String fullname = etfullname.getText().toString();
            String email = etemail.getText().toString();
            String password = etpassword.getText().toString();
            String phone = etphone.getText().toString();
            String type = "register";
            Backgroundtask backgroundtask = new Backgroundtask(this);
            backgroundtask.execute(type, email, password, phone, fullname);
        }
    }
    @Override
    public void onClick(View view) {
        if (view == buttonSubmit) {
            submitForm();
        }
    }
}
