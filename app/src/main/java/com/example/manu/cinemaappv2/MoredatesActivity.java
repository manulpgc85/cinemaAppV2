package com.example.manu.cinemaappv2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class MoredatesActivity extends AppCompatActivity {
    String name ="";
    String lastname = "";
    String address = "";
    String phone = "";
    String mail = "";
    String genre = "";
    Activity contexto;
    EditText editTextCountry;
    EditText editTextCity;
    EditText editTextZIP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moredates);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        contexto = this;

        editTextCountry = findViewById(R.id.editText_country);
        editTextCity = findViewById(R.id.editText_city);
        editTextZIP = findViewById(R.id.editText_zip);



        FloatingActionButton fabMore = (FloatingActionButton) findViewById(R.id.fabMore);
        fabMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        name = getIntent().getExtras().getString("name");
        lastname = getIntent().getExtras().getString("lastname");
        address = getIntent().getExtras().getString("address");
        phone = getIntent().getExtras().getString("phone");
        mail = getIntent().getExtras().getString("mail");
        genre = getIntent().getExtras().getString("genre");
        String country = editTextCountry.getText().toString();
        String city = editTextCity.getText().toString();
        String zip = editTextZIP.getText().toString();


    }

    private void validate() {
        String country = editTextCountry.getText().toString();
        String city = editTextCity.getText().toString();
        String zip = editTextZIP.getText().toString();


        if(TextUtils.isEmpty(country) || country.trim().isEmpty()){
            editTextCountry.setError(getString(R.string.fail_blank));
            editTextCountry.requestFocus();
            return;
        }
        else if(TextUtils.isEmpty(city) || city.trim().isEmpty()){
            editTextCity.setError(getString(R.string.fail_blank));
            editTextCity.requestFocus();
            return;
        } else if(TextUtils.isEmpty(zip) || zip.trim().isEmpty()){
            editTextZIP.setError(getString(R.string.fail_blank));
            editTextZIP.requestFocus();
            return;
        }

        goToresult();
    }

    private void goToresult() {

        String country = editTextCountry.getText().toString();
        String city = editTextCity.getText().toString();
        String zip = editTextZIP.getText().toString();

        Intent intent = new Intent(contexto, MoremoreDatesActivity.class);

        intent.putExtra("genre",genre);
        intent.putExtra("name", name);
        intent.putExtra("lastname", lastname);
        intent.putExtra("address", address);
        intent.putExtra("country",country);
        intent.putExtra("city", city);
        intent.putExtra("phone", phone);
        intent.putExtra("zip", zip) ;
        intent.putExtra("mail",mail);


        startActivity(intent);
    }

}
