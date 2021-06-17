package com.tutorials.hp.mysqlselector;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class     RegisterActivity extends Activity {

    EditText et_isim,et_email,et_sifre;
    String isim,email,sifre;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_isim=(EditText)findViewById(R.id.editisim);
        et_email=(EditText)findViewById(R.id.editemail);
        et_sifre=(EditText)findViewById(R.id.editsifre);
    }
        public void userReg(View view)
        {
            isim=et_isim.getText().toString();
            email=et_email.getText().toString();
            sifre=et_sifre.getText().toString();
            String method="register";
            BackgroundTask backgroundTask=new BackgroundTask(this);
            backgroundTask.execute(method,isim,email,sifre);
            finish();

        }
    }
