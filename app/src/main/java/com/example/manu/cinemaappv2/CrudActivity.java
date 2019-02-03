package com.example.manu.cinemaappv2;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CrudActivity extends AppCompatActivity {
    daoFilms dao;
    Adapter adapter;
    ArrayList<Films> list;
    Films f;
    Activity context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);
        dao = new daoFilms(this);
        list = dao.seeAlls();
        adapter = new Adapter(list, this, dao);
        final ListView list = (ListView) findViewById(R.id.list);
        Button add = (Button) findViewById(R.id.b_add);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Dialog para ver activity_crud_view.xml
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                //Dialog para ver activity_crud_dialogue.xml
                final Dialog dialog = new Dialog(CrudActivity.this);
                dialog.setTitle("New Register");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.activity_crud_dialogue);
                dialog.show();
                final EditText title = (EditText) dialog.findViewById(R.id.d_title);
                final EditText director = (EditText) dialog.findViewById(R.id.d_director);
                final EditText guionist = (EditText) dialog.findViewById(R.id.d_guionist);
                final EditText genre = (EditText) dialog.findViewById(R.id.d_genre);
                final EditText subgenre = (EditText) dialog.findViewById(R.id.d_subgenre);
                final EditText actor1 = (EditText) dialog.findViewById(R.id.d_actor1);
                final EditText actor2 = (EditText) dialog.findViewById(R.id.d_actor2);
                final EditText actor3 = (EditText) dialog.findViewById(R.id.d_actor3);
                final EditText actor4 = (EditText) dialog.findViewById(R.id.d_actor4);
                Button save = (Button) dialog.findViewById(R.id.d_addButton);
                final Button cancel = (Button) dialog.findViewById(R.id.d_cancelButton);
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            f = new Films(title.getText().toString(),
                                    director.getText().toString(),
                                    guionist.getText().toString(),
                                    genre.getText().toString(),
                                    subgenre.getText().toString(),
                                    actor1.getText().toString(),
                                    actor2.getText().toString(),
                                    actor3.getText().toString(),
                                    actor4.getText().toString());
                            dao.insert(f);
                            //list=dao.seeAlls();
                            adapter.notifyDataSetChanged();
                            dialog.dismiss();
                            Intent intent = new Intent(context, CrudActivity.class );
                            startActivity(intent);
                        } catch (Exception e) {
                            Toast.makeText(getApplication(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }
}
