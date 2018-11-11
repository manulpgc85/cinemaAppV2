package com.example.manu.cinemaappv2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class daoFilms {
    SQLiteDatabase cx;
    ArrayList<Films> List = new ArrayList<Films>();
    Films f;
    Context ct;
    String DBname = "BDFilmLibrary";
    String table = "create table if not exists films(id integer primary key autoincrement, title text, director text, guionist text, genre text, subgenre text, actor1 text, actor2 text, actor3 text, actor4 text)";

    public daoFilms(Context c) {
        this.ct = c;
        cx = c.openOrCreateDatabase(DBname, c.MODE_PRIVATE, null);
        cx.execSQL(table);
    }

    public boolean insert(Films f) {
        ContentValues container = new ContentValues();
        container.put("title", f.getTitles());
        container.put("director", f.getDirector());
        container.put("guionist", f.getGuionist());
        container.put("genre", f.getGenre());
        container.put("subgenre", f.getSubgenre());
        container.put("actor1", f.getActor1());
        container.put("actor2", f.getActor2());
        container.put("actor3", f.getActor3());
        container.put("actor4", f.getActor4());
        return (cx.insertOrThrow("films", null, container)) > 0;
    }

    public boolean delete(int id) {
        return (cx.delete("films", "id=" + id, null)) > 0;
    }

    public boolean update(Films f) {
        ContentValues container = new ContentValues();
        container.put("title", f.getTitles());
        container.put("director", f.getDirector());
        container.put("guionist", f.getGuionist());
        container.put("genre", f.getGenre());
        container.put("subgenre", f.getSubgenre());
        container.put("actor1", f.getActor1());
        container.put("actor2", f.getActor2());
        container.put("actor3", f.getActor3());
        container.put("actor4", f.getActor4());
        return (cx.update("films", container, "id=" + f.getId(), null)) > 0;
    }

    public ArrayList<Films> seeAlls() {
        List.clear();
        Cursor cursor = cx.rawQuery("select * from films", null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                List.add(new Films(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getString(9)));
            } while (cursor.moveToNext());
        }
        return List;
    }

    public Films seeOne(int position) {
        Cursor cursor = cx.rawQuery("select * from films", null);
        cursor.moveToPosition(position);
        f = new Films(cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getString(6),
                cursor.getString(7),
                cursor.getString(8),
                cursor.getString(9));
        return f;
    }

}
