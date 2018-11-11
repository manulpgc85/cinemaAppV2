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
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MoremoreDatesActivity extends AppCompatActivity {
    //from datesActivity
    String name = "";
    String lastname = "";
    String address = "";
    String phone = "";
    String mail = "";
    String genre = "";
    //from MoreDates
    String country = "";
    String city = "";
    String zip = "";
    //to this activity
    Activity contexto;
    String year = "";
    String month = "";
    String day = "";
    String ppv = "";
    EditText editText_day;
    EditText editText_month;
    EditText editText_year;
    TextView textView_ppv;
    RadioGroup ppvSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moremore_dates);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        contexto = this;

        ppvSelected = findViewById(R.id.ppvSelected);
        ppvSelected.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checked) {
                switch (checked){
                    case R.id.radioButton_amazon:
                        ppv = "Amazon";
                        break;
                    case R.id.radioButton_hbo:
                        ppv = "HBO";
                        break;
                    case R.id.radioButton_movistar:
                        ppv = "Movistar";
                        break;
                    case R.id.radioButton_netflix:
                        ppv = "Netflix";
                        break;
                    case R.id.radioButton_vodafone:
                        ppv = "Vodafone";
                        break;
                }
                Toast.makeText(contexto, "You have been selected "+ ppv, Toast.LENGTH_SHORT).show();
            }
        });
        editText_day = findViewById(R.id.editText_day);
        editText_month = findViewById(R.id.editText_month);
        editText_year = findViewById(R.id.editText_year);
        textView_ppv = findViewById(R.id.textView_ppv);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //from datesActivity
        name = getIntent().getExtras().getString("name");
        lastname = getIntent().getExtras().getString("lastname");
        address = getIntent().getExtras().getString("address");
        mail = getIntent().getExtras().getString("mail");
        phone = getIntent().getExtras().getString("phone");
        genre = getIntent().getExtras().getString("genre");
        //from MoreDates
        country = getIntent().getExtras().getString("country");
        city = getIntent().getExtras().getString("city");
        zip = getIntent().getExtras().getString("zip");
    }

    private void validate(){
       editText_year.setError(null);
       editText_day.setError(null);
       editText_month.setError(null);

        String day = editText_day.getText().toString();
        String month = editText_month.getText().toString();
        String year = editText_year.getText().toString();

        if(TextUtils.isEmpty(day) || day.trim().isEmpty()){
            editText_day.setError(getString(R.string.fail_blank));
            editText_day.requestFocus();
            return;
            }else if (TextUtils.isEmpty(month) || month.trim().isEmpty()) {
                editText_month.setError(getString(R.string.fail_blank));
                editText_month.requestFocus();
                return;
                }else if (TextUtils.isEmpty(year) || year.trim().isEmpty()) {
                    editText_year.setError(getString(R.string.fail_blank));
                    editText_year.requestFocus();
                    return;
                    }
        if (ppv.equals ("null")){
            textView_ppv.setError(getString(R.string.fail_blank));
            textView_ppv.requestFocus();
            return;
            }
            goTOmoremore();
    }

    private void goTOmoremore(){
        String day = editText_day.getText().toString();
        String month = editText_month.getText().toString();
        String year = editText_year.getText().toString();

        Intent intent = new Intent(contexto, KindFilmsActivity.class);

        intent.putExtra("name", name);
        intent.putExtra("lastname", lastname);
        intent.putExtra("address", address);
        intent.putExtra("mail", mail);
        intent.putExtra("phone", phone);
        intent.putExtra("genre", genre);
        intent.putExtra("country", country);
        intent.putExtra("city", city);
        intent.putExtra("zip", zip);
        intent.putExtra("day",day);
        intent.putExtra("month",month);
        intent.putExtra("year", year);
        intent.putExtra("ppv", ppv);
        startActivity(intent);
    }

}
