package com.example.manu.cinemaappv2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class KindFilmsActivity extends AppCompatActivity {
    //to this Activity
    CheckBox Terms;
    CheckBox action;
    CheckBox adventure;
    CheckBox horror;
    CheckBox comedy;
    CheckBox western;
    CheckBox thriller;
    CheckBox fantasy;
    CheckBox superheroes;
    CheckBox drama;
    Activity contexto;
    TextView textViewKinds;
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
    //from Moremoredates
    String year = "";
    String month = "";
    String day = "";
    String ppv = "";
    String kind = "";
    int contador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kind_films);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        contexto = this;

        textViewKinds = findViewById(R.id.textView_kind);
        Terms = findViewById(R.id.checkBox_terms);
        action = findViewById(R.id.checkBox_action);
        adventure = findViewById(R.id.checkBox_adventure);
        horror = findViewById(R.id.checkBox_horror);
        comedy = findViewById(R.id.checkBox_comedy);
        western = findViewById(R.id.checkBox_western);
        thriller = findViewById(R.id.checkBox_thriller);
        fantasy = findViewById(R.id.checkBox_fantasy);
        superheroes = findViewById(R.id.checkBox_heroes);
        drama = findViewById(R.id.checkBox_drama);


        FloatingActionButton fab = findViewById(R.id.fab);
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
        //from MoremoreDates
        year = getIntent().getExtras().getString("year");
        month = getIntent().getExtras().getString("month");
        day = getIntent().getExtras().getString("day");
        ppv = getIntent().getExtras().getString("ppv");
    }

    private void validate() {
        contador = 0;

        textViewKinds.setError(null);
        Terms.setError(null);

        if (action.isChecked()) {
            contador++;
        }
        if (adventure.isChecked()) {
            contador++;
        }
        if (drama.isChecked()) {
            contador++;
        }
        if (comedy.isChecked()) {
            contador++;
        }
        if (horror.isChecked()) {
            contador++;
        }
        if (fantasy.isChecked()) {
            contador++;
        }
        if (western.isChecked()) {
            contador++;
        }
        if (thriller.isChecked()) {
            contador++;
        }
        if (superheroes.isChecked()) {
            contador++;
        }
        if (contador > 3) {
            textViewKinds.setError(getString(R.string.minimum));
            textViewKinds.requestFocus();
            return;
        }
        if (!Terms.isChecked()) {
            Terms.setError(getString(R.string.fail_blank));
            Terms.requestFocus();
            return;
        }

        goToResult();
    }

    private void goToResult() {
        if (action.isChecked())
            kind = "Action";
        if (adventure.isChecked()) {
            if (!kind.equals(""))
                kind += ", Adventure";
            else
                kind = "Adventure";
        }
        if (horror.isChecked()) {
            if (!kind.equals(""))
                kind += ", Horror";
            else
                kind = "Horror";
        }
        if (comedy.isChecked()) {
            if (!kind.equals(""))
                kind += ", Comedy";
            else
                kind = "Comedy";
        }
        if (western.isChecked()) {
            if (!kind.equals(""))
                kind += ", Western";
            else
                kind = "Western";
        }
        if (thriller.isChecked()) {
            if (!kind.equals(""))
                kind += ", Thriller";
            else
                kind = "Thriller";
        }
        if (fantasy.isChecked()) {
            if (!kind.equals(""))
                kind += ", Fantasy";
            else
                kind = "Fantasy";
        }
        if (superheroes.isChecked()) {
            if (!kind.equals(""))
                kind += ", Superheroes";
            else
                kind = "Superheroes";
        }
        if (drama.isChecked()) {
            if (!kind.equals(""))
                kind += ", Drama";
            else
                kind = "Drama";
        }
        Intent intent = new Intent(contexto, ResultActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("lastname", lastname);
        intent.putExtra("address", address);
        intent.putExtra("mail", mail);
        intent.putExtra("phone", phone);
        intent.putExtra("genre", genre);
        intent.putExtra("country", country);
        intent.putExtra("city", city);
        intent.putExtra("zip", zip);
        intent.putExtra("day", day);
        intent.putExtra("month", month);
        intent.putExtra("year", year);
        intent.putExtra("ppv", ppv);
        intent.putExtra("kind", kind);
        startActivity(intent);
    }

}
