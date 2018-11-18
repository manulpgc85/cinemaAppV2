package com.example.manu.cinemaappv2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    Activity contexto;
    Button button_more;
    String country = "";
    String city = "";
    String zip = "";
    String year = "";
    String day = "";
    String month = "";
    String birth = "";
    String ppv = "";
    String kind = "";
    String runname;
    String runmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        contexto = this;

        String name = getIntent().getExtras().getString("name");
        TextView name_r= (TextView) findViewById(R.id.textView_result_name);
        name_r.setText(name);

        String lastname = getIntent().getExtras().getString("lastname");
        TextView lastname_r = (TextView) findViewById(R.id.textView_result_lastname);
        lastname_r.setText(lastname);

        String address = getIntent().getExtras().getString("address");
        TextView address_r = (TextView) findViewById(R.id.textView_result_address);
        address_r.setText(address);

        String phone = getIntent().getExtras().getString("phone");
        TextView phone_r = (TextView) findViewById(R.id.textView_result_phone);
        phone_r.setText(phone);

        String mail = getIntent().getExtras().getString("mail");
        TextView mail_r = (TextView) findViewById(R.id.textView_result_mail);
        mail_r.setText(mail);

        String genre = getIntent().getExtras().getString("genre");
        TextView genre_r = (TextView) findViewById(R.id.textView_result_genre);
        genre_r.setText(genre);

        country = getIntent().getExtras().getString("country");
        city = getIntent().getExtras().getString("city");
        zip = getIntent().getExtras().getString("zip");
        year = getIntent().getExtras().getString("year");
        month = getIntent().getExtras().getString("month");
        day = getIntent().getExtras().getString("day");
        birth = day + "/ " + month + "/ " +year;
        ppv = getIntent().getExtras().getString("ppv");
        kind= getIntent().getExtras().getString("kind");


        button_more = (Button) findViewById(R.id.button_more);
        button_more.setOnClickListener(new  View.OnClickListener(){
            @Override
                    public void onClick (View view){
                Intent intent = new Intent(contexto, ResultActivity2.class);

                intent.putExtra("country", country);
                intent.putExtra("city", city);
                intent.putExtra("zip", zip);
                //intent.putExtra("day",day);
                //intent.putExtra("month",month);
                //intent.putExtra("year", year);
                intent.putExtra("birth", birth);
                intent.putExtra("ppv", ppv);
                intent.putExtra("kind",kind);

                startActivity(intent);
            }
        });
    }
}
