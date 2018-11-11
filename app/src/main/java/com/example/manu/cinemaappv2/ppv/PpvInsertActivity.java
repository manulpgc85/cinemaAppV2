package com.example.manu.cinemaappv2.ppv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.manu.cinemaappv2.R;
import com.example.manu.cinemaappv2.constants.G;
import com.example.manu.cinemaappv2.pojos.Ppv;
import com.example.manu.cinemaappv2.proveedor.PpvProveedor;

public class PpvInsertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ppv_details);
        Toolbar toolbar = findViewById(R.id.toolbar_details_activity);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuItem menuItem = menu.add(Menu.NONE, G.SAVE, Menu.NONE, "Save");
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menuItem.setIcon(R.drawable.ic_guardar_archivo_opcion);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case G.SAVE:
                attempSave();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    void attempSave(){
        EditText editTextPPVTitle =(EditText) findViewById(R.id.Title);
        EditText editTextPPVDirector =(EditText) findViewById(R.id.Director);
        EditText editTextPPVGuionist =(EditText) findViewById(R.id.Guionist);
        EditText editTextPPVYear =(EditText) findViewById(R.id.Year);

        editTextPPVTitle.setError(null);
        editTextPPVDirector.setError(null);
        editTextPPVGuionist.setError(null);
        editTextPPVYear.setError(null);

        String Title = String.valueOf(editTextPPVTitle.getText());
        String Director = String.valueOf(editTextPPVDirector.getText());
        String Guionist = String.valueOf(editTextPPVGuionist.getText());
        String Year = String.valueOf(editTextPPVYear.getText());

        if(TextUtils.isEmpty(Title) || Title.trim().isEmpty()){
            editTextPPVTitle.setError(getString(R.string.error_validate));
            editTextPPVTitle.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(Director) || Director.trim().isEmpty()){
            editTextPPVDirector.setError(getString(R.string.error_validate));
            editTextPPVDirector.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(Guionist) || Guionist.trim().isEmpty()){
            editTextPPVGuionist.setError(getString(R.string.error_validate));
            editTextPPVGuionist.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(Year) || Year.trim().isEmpty()){
            editTextPPVYear.setError(getString(R.string.error_validate));
            editTextPPVYear.requestFocus();
            return;
        }


        Ppv ppv = new Ppv(G.SIN_VALOR_INT, Title, Director, Guionist, Integer.parseInt(Year));
        PpvProveedor.insertRecord(getContentResolver(),ppv);
        finish();
    }
}
