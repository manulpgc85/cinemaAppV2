package com.example.manu.cinemaappv2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manu.cinemaappv2.*;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {
    ArrayList<Films> list;
    daoFilms dao;
    Films f;
    Activity act;
    int id = 0;

    public Adapter(ArrayList<Films> list, Activity act, daoFilms dao) {

        this.list = list;
        this.act = act;
        this.dao = dao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Films getItem(int i) {
        f = list.get(i);
        return null;
    }

    @Override
    public long getItemId(int i) {
        f = list.get(i);
        return f.getId();
    }

    @Override
    public View getView(final int pos, final View view, ViewGroup viewGroup) {
        View v = view;
        if (v == null) {
            LayoutInflater li = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.activity_crud_item, null);
        }
        f = list.get(pos);
        TextView title = (TextView) v.findViewById(R.id.t_title);
        TextView director = (TextView) v.findViewById(R.id.t_director);
        TextView guionist = (TextView) v.findViewById(R.id.t_guionist);
        TextView genre = (TextView) v.findViewById(R.id.t_genre);
        TextView subgenre = (TextView) v.findViewById(R.id.t_subgenre);
        TextView actor1 = (TextView) v.findViewById(R.id.t_actor1);
        TextView actor2 = (TextView) v.findViewById(R.id.t_actor2);
        TextView actor3 = (TextView) v.findViewById(R.id.t_actor3);
        TextView actor4 = (TextView) v.findViewById(R.id.t_actor4);
        Button update = (Button) v.findViewById(R.id.b_edit);
        Button delete = (Button) v.findViewById(R.id.b_delete);
        title.setText(f.getTitles());
        director.setText(f.getDirector());
        guionist.setText(f.getGuionist());
        genre.setText(f.getGenre());
        subgenre.setText(f.getSubgenre());
        actor1.setText(f.getActor1());
        actor2.setText(f.getActor2());
        actor3.setText(f.getActor3());
        actor4.setText(f.getActor4());
        update.setTag(pos);
        Log.i("tiburcio", "el pos: " + pos);
        delete.setTag(pos);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dialogo de updateRecord que es activity_crud_dialogue.xml
                Log.i("tiburcio", "el pos después: " + ((int) v.getTag()));
                int po = (int) v.getTag();
                final Dialog dialog = new Dialog(act);
                dialog.setTitle("Update Register");
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
                f = list.get(po);
                setId(f.getId());
                title.setText(f.getTitles());
                guionist.setText(f.getGuionist());
                genre.setText(f.getGenre());
                subgenre.setText(f.getSubgenre());
                actor1.setText(f.getActor1());
                actor2.setText(f.getActor2());
                actor3.setText(f.getActor3());
                actor4.setText(f.getActor4());
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            f = new Films(getId(), title.getText().toString(),
                                    director.getText().toString(),
                                    guionist.getText().toString(),
                                    genre.getText().toString(),
                                    subgenre.getText().toString(),
                                    actor1.getText().toString(),
                                    actor2.getText().toString(),
                                    actor3.getText().toString(),
                                    actor4.getText().toString());
                            dao.update(f);
                            list = dao.seeAlls();
                            notifyDataSetChanged();
                            dialog.dismiss();
                        } catch (Exception e) {
                            Toast.makeText(act, "Error", Toast.LENGTH_SHORT).show();
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

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int po = Integer.parseInt(v.getTag().toString());
                f = list.get(po);
                setId(f.getId());
                AlertDialog.Builder del = new AlertDialog.Builder(act);
                del.setMessage("Are you sure to deleteRecord this register?");
                del.setCancelable(false);
                del.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dao.delete(getId());
                        list = dao.seeAlls();
                        notifyDataSetChanged();
                    }
                });
                del.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                    }
                });
                del.show();
            }
        });
        return v;
    }
}

