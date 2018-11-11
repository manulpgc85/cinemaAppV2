package com.example.manu.cinemaappv2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class postRegister extends AppCompatActivity {

    Activity contexto;
    ImageButton end;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_register);

    contexto = this;

    end =  (ImageButton) findViewById(R.id.imageButton_finish);
    end.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(contexto, FinishActivity.class);
            startActivity(intent);
        }
    });

    }
}
