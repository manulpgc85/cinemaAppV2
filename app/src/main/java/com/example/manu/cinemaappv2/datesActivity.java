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

public class datesActivity extends AppCompatActivity {
    EditText editTextName;
    EditText editTextLastname;
    EditText editTextAddress;
    EditText editTextPhone;
    EditText editTextEmail;
    RadioGroup sexSelected;
    TextView textView_Genre;
    Activity contexto;
    String genre = "null";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dates);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        contexto = this;

        sexSelected = findViewById(R.id.sexSelected);
        sexSelected.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checked) {

                switch (checked){
                    case R.id.radioButton_male:
                        genre = "Male";
                        break;
                    case R.id.radioButton_female:
                        genre = "Female";
                        break;
                }
                Toast.makeText(contexto,"You pushed " + genre, Toast.LENGTH_SHORT).show();

            }
        });

        editTextName = findViewById(R.id.editText_name);
        editTextLastname = findViewById(R.id.editText_lastname);
        editTextAddress = findViewById(R.id.editText_address);
        editTextPhone = findViewById(R.id.editText_phone);
        editTextEmail = findViewById(R.id.editText_mail);
        textView_Genre= findViewById(R.id.textView_Genre);

        FloatingActionButton fabDate = (FloatingActionButton) findViewById(R.id.fabDate);
        fabDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void validate() {
        editTextName.setError(null);
        editTextLastname.setError(null);
        editTextAddress.setError(null);
        editTextPhone.setError(null);
        editTextEmail.setError(null);

        String name = editTextName.getText().toString();
        String lastname = editTextLastname.getText().toString();
        String address = editTextAddress.getText().toString();
        String phone = editTextPhone.getText().toString();
        String mail = editTextEmail.getText().toString();


        if(TextUtils.isEmpty(name) || name.trim().isEmpty()){
            editTextName.setError(getString(R.string.fail_blank));
            editTextName.requestFocus();
            return;
        }else if(TextUtils.isEmpty(lastname) || lastname.trim().isEmpty()){
            editTextLastname.setError(getString(R.string.fail_blank));
            editTextLastname.requestFocus();
            return;
            } else if(TextUtils.isEmpty(address) || address.trim().isEmpty()){
                editTextAddress.setError(getString(R.string.fail_blank));
                editTextAddress.requestFocus();
                return;
                }else if(TextUtils.isEmpty(mail) || mail.trim().isEmpty()){
                    editTextEmail.setError(getString(R.string.fail_blank));
                    editTextEmail.requestFocus();
                    return;
                    }else if(TextUtils.isEmpty(phone) || phone.trim().isEmpty()){
                        editTextPhone.setError(getString(R.string.fail_blank));
                        editTextPhone.requestFocus();
                        return;
                        }
         if (genre.equals("null")){
             textView_Genre.setError(getString(R.string.fail_blank));
             textView_Genre.requestFocus();
             return;
        }

        goTOmore();
    }

    private void goTOmore(){
        String name = editTextName.getText().toString();
        String lastname = editTextLastname.getText().toString();
        String address = editTextAddress.getText().toString();

        String phone = editTextPhone.getText().toString();

        String mail = editTextEmail.getText().toString();

        Intent intent = new Intent(contexto, MoredatesActivity.class);

        intent.putExtra("genre",genre);
        intent.putExtra("name", name);
        intent.putExtra("lastname", lastname);
        intent.putExtra("address", address);
        intent.putExtra("phone", phone);
        intent.putExtra("mail",mail);



        startActivity(intent);
    }
}
