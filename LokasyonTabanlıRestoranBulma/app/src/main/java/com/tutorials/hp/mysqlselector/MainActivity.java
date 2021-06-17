package com.tutorials.hp.mysqlselector;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {

    EditText email, sifre;
    Button btn;
    CheckBox checkBox;
    TextView txtUnut, txtYeni;
    String login_mail, login_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText) findViewById(R.id.editEmail);
        sifre = (EditText) findViewById(R.id.editSifre);
        btn = (Button) findViewById(R.id.button);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        txtUnut = (TextView) findViewById(R.id.textUnut);
        txtYeni = (TextView) findViewById(R.id.textYeni);


        txtYeni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    btn.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View view){
        Intent intent = new Intent(MainActivity.this,Navigation.class);
        startActivity(intent);

        //Intent tintent = new Intent(ctx, MapsActivity.class);
        //ctx.startActivity(tintent);
       //((Activity) ctx).finish();
    }
    });
}

    public void  userLogin(View view){

        login_mail=email.getText().toString();
        login_pass=sifre.getText().toString();
        String method="login";
        BackgroundTask backgroundTask=new BackgroundTask(this);
        backgroundTask.execute(method,login_mail,login_pass);
    }
}

