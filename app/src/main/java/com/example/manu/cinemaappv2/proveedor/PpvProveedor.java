package com.example.manu.cinemaappv2.proveedor;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import com.example.manu.cinemaappv2.pojos.Ppv;

public class PpvProveedor {
    public static void insertRecord(ContentResolver resolver, Ppv ppv){
        Uri uri = Contrato.PPV.CONTENT_URI;

        ContentValues values =new ContentValues();
        values.put(Contrato.PPV.TITLE,ppv.getTitulo());
        values.put(Contrato.PPV.DIRECTOR,ppv.getDirector());
        values.put(Contrato.PPV.GUIONIST,ppv.getGuionist());
        values.put(Contrato.PPV.YEAR,ppv.getYear());

        resolver.insert(uri, values);
    }

    public static void deleteRecord(ContentResolver resolver, int ppvId){
        Uri uri = Uri.parse(Contrato.PPV.CONTENT_URI + "/" +ppvId);
        resolver.delete(uri, null, null);
    }

    public static void updateRecord(ContentResolver resolver, Ppv ppv){
        Uri uri = Uri.parse(Contrato.PPV.CONTENT_URI + "/" + ppv.getId());
        ContentValues values = new ContentValues();
        values.put(Contrato.PPV.TITLE, ppv.getTitulo());
        values.put(Contrato.PPV.DIRECTOR, ppv.getDirector());
        values.put(Contrato.PPV.GUIONIST, ppv.getGuionist());
        values.put(Contrato.PPV.YEAR, ppv.getYear());

        resolver.update(uri, values, null, null);
    }

    public static Ppv readRecord(ContentResolver resolver, int ppvId){
        Uri uri = Uri.parse(Contrato.PPV.CONTENT_URI + "/" + ppvId);
        String[] projection = {
                Contrato.PPV.TITLE,
                Contrato.PPV.DIRECTOR,
                Contrato.PPV.GUIONIST,
                Contrato.PPV.YEAR
        };

        Cursor cursor = resolver.query(uri, projection, null, null, null);

        if (cursor.moveToFirst()) {

            Ppv ppv = new Ppv();
            ppv.setId(ppvId);
            ppv.setTitulo(cursor.getString(cursor.getColumnIndex(Contrato.PPV.TITLE)));
            ppv.setDirector(cursor.getString(cursor.getColumnIndex(Contrato.PPV.DIRECTOR)));
            ppv.setGuionist(cursor.getString(cursor.getColumnIndex(Contrato.PPV.GUIONIST)));
            ppv.setYear(cursor.getInt(cursor.getColumnIndex(Contrato.PPV.YEAR)));

            return ppv;
        }
        return null;
    }
}
