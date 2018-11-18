package com.example.manu.cinemaappv2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity2 extends AppCompatActivity {
    Activity contexto;
    Button button_finish;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contexto = this;
        setContentView(R.layout.activity_result2);
        String country = getIntent().getExtras().getString("country");
        TextView country_r = (TextView) findViewById(R.id.textView_result_country);
        country_r.setText(country);

        String city = getIntent().getExtras().getString("city");
        TextView city_r = (TextView) findViewById(R.id.textView_result_city);
        city_r.setText(city);

        String zip = getIntent().getExtras().getString("zip");
        TextView zip_r = (TextView) findViewById(R.id.textView_result_zip);
        zip_r.setText(zip);

        String birth = getIntent().getExtras().getString("birth");
        //String month = getIntent().getExtras().getString("month");
        //String day = getIntent().getExtras().getString("day");
        //String birth = day + "/ " + month + "/ " +year;
        TextView birth_r = (TextView) findViewById(R.id.textView_result_birth);
        birth_r.setText(birth);

        String ppv = getIntent().getExtras().getString("ppv");
        TextView ppv_r = (TextView) findViewById(R.id.textView_result_ppv);
        ppv_r.setText(ppv);

        String kind = getIntent().getExtras().getString("kind");
        TextView kind_r = (TextView) findViewById(R.id.textView_result_kind);
        kind_r.setText(kind);

        button_finish = (Button) findViewById(R.id.button_finish);
        button_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(contexto, postRegister.class);

                startActivity(intent);
            }
        });
   }
}
