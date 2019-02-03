package com.example.manu.cinemaappv2.proveedor;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;

public class ProveedorDeContenido extends ContentProvider {
    // Indicates an invalid content URI
    public static final int INVALID_URI = -1;
    private static final int PPV_ONE_REG = 1;
    private static final int PPV_ALL_REGS = 2;
    private static final int PPV_ALL_REGS_PPVS = 3;

    private static final String DATABASE_NAME = "ALLFILMS.db";
    private static final int DATABASE_VERSION = 4;
    private static final String FILMS_TABLE_NAME = "ppvFilms";
    // Defines a helper object that matches content URIs to table-specific parameters
    private static final UriMatcher sUriMatcher;
    // Stores the MIME types served by this provider
    private static final SparseArray<String> sMimeTypes;

    /*
     * Initializes meta-data used by the content provider:
     * - UriMatcher that maps content URIs to codes
     * - MimeType array that returns the custom MIME type of a table
     */
    static {

        // Creates an object that associates content URIs with numeric codes
        sUriMatcher = new UriMatcher(0);

        /*
         * Sets up an array that maps content URIs to MIME types, via a mapping between the
         * URIs and an integer code. These are custom MIME types that apply to tables and rows
         * in this particular provider.
         */
        sMimeTypes = new SparseArray<String>();

        // Adds a URI "match" entry that maps picture URL content URIs to a numeric code

        sUriMatcher.addURI(
                Contrato.AUTHORITY,
                FILMS_TABLE_NAME,
                PPV_ALL_REGS);
        sUriMatcher.addURI(
                Contrato.AUTHORITY,
                FILMS_TABLE_NAME + "/#",
                PPV_ONE_REG);
        sUriMatcher.addURI(
                Contrato.AUTHORITY,
                FILMS_TABLE_NAME + "/PPVS",
                PPV_ALL_REGS_PPVS);

        // Specifies a custom MIME type for the picture URL table

        sMimeTypes.put(
                PPV_ALL_REGS,
                "vnd.android.cursor.dir/vnd." +
                        Contrato.AUTHORITY + "." + FILMS_TABLE_NAME);
        sMimeTypes.put(
                PPV_ONE_REG,
                "vnd.android.cursor.item/vnd." +
                        Contrato.AUTHORITY + "." + FILMS_TABLE_NAME);
    }

    public DatabaseHelper dbHelper;
    private SQLiteDatabase sqlDB;

    public ProveedorDeContenido() {
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public boolean onCreate() {
        dbHelper = new DatabaseHelper(getContext());
        return (dbHelper == null) ? false : true;
    }

    public void resetDatabase() {
        dbHelper.close();
        dbHelper = new DatabaseHelper(getContext());
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        sqlDB = dbHelper.getWritableDatabase();

        String table = "";
        switch (sUriMatcher.match(uri)) {
            case PPV_ALL_REGS:
                table = FILMS_TABLE_NAME;
                break;
        }

        long rowId = sqlDB.insert(table, "", values);

        if (rowId > 0) {
            Uri rowUri = ContentUris.appendId(
                    uri.buildUpon(), rowId).build();
            getContext().getContentResolver().notifyChange(rowUri, null);
            return rowUri;
        }
        throw new SQLException("Failed to insertRecord row into " + uri);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        sqlDB = dbHelper.getWritableDatabase();
        // insertRecord record in user table and get the row number of recently inserted record

        String table = "";
        switch (sUriMatcher.match(uri)) {
            case PPV_ONE_REG:
                if (null == selection) selection = "";
                selection += Contrato.PPV._ID + " = "
                        + uri.getLastPathSegment();
                table = FILMS_TABLE_NAME;
                break;
            case PPV_ALL_REGS:
                table = FILMS_TABLE_NAME;
                break;
        }
        int rows = sqlDB.delete(table, selection, selectionArgs);
        if (rows > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
            return rows;
        }
        throw new SQLException("Failed to deleteRecord row into " + uri);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String query = null;

        Log.i("tiburcio", "numURI: " + sUriMatcher.match(uri));
        Log.i("tiburcio", "uri: " + uri.getAuthority() + "," + uri.getPath());

        switch (sUriMatcher.match(uri)) {
            case PPV_ONE_REG:
                if (null == selection) selection = "";
                selection += Contrato.PPV._ID + " = "
                        + uri.getLastPathSegment();
                qb.setTables(FILMS_TABLE_NAME);
                break;
            case PPV_ALL_REGS:
                if (TextUtils.isEmpty(sortOrder)) sortOrder =
                        Contrato.PPV._ID + " ASC";
                qb.setTables(FILMS_TABLE_NAME);
                break;
            case PPV_ALL_REGS_PPVS:
                if (TextUtils.isEmpty(sortOrder)) sortOrder =
                        Contrato.PPV._ID + " ASC";
                selection += Contrato.PPV.CATEGORY + " != 'Cine'";
                qb.setTables(FILMS_TABLE_NAME);
                Log.i("tiburcio", "la bola entró");
                break;
        }

        //SELECT PROJECTION FROM TABLA(QB.SETTABLES) WHERE SELECTION

        Cursor c;
        c = qb.query(db, projection, selection, selectionArgs, null, null,
                sortOrder);
        c.setNotificationUri(getContext().getContentResolver(), uri);

        return c;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        sqlDB = dbHelper.getWritableDatabase();
        // insertRecord record in user table and get the row number of recently inserted record

        String table = "";
        switch (sUriMatcher.match(uri)) {
            case PPV_ONE_REG:
                if (null == selection) selection = "";
                selection += Contrato.PPV._ID + " = "
                        + uri.getLastPathSegment();
                table = FILMS_TABLE_NAME;
                break;
            case PPV_ALL_REGS:
                table = FILMS_TABLE_NAME;
                break;
        }

        int rows = sqlDB.update(table, values, selection, selectionArgs);
        if (rows > 0) {
            getContext().getContentResolver().notifyChange(uri, null);

            return rows;
        }
        throw new SQLException("Failed to updateRecord row into " + uri);
    }

    public static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onOpen(SQLiteDatabase db) {
            super.onOpen(db);

            //if (!db.isReadOnly()){
            //Habilitamos la integridad referencial
            db.execSQL("PRAGMA foreign_keys=ON;");
            //}
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // create table to store

            db.execSQL("Create table "
                    + FILMS_TABLE_NAME
                    + "( _id INTEGER PRIMARY KEY ON CONFLICT ROLLBACK AUTOINCREMENT, "
                    + Contrato.PPV.TITLE + " TEXT , "
                    + Contrato.PPV.DIRECTOR + " TEXT ,"
                    + Contrato.PPV.GUIONIST + " TEXT ,"
                    + Contrato.PPV.YEAR + " INTEGER ,"
                    + Contrato.PPV.CATEGORY + " TEXT" + ");"
            );

            inicializarDatos(db);

        }

        void inicializarDatos(SQLiteDatabase db) {

            db.execSQL("INSERT INTO " + FILMS_TABLE_NAME + " (" + Contrato.PPV._ID + "," + Contrato.PPV.TITLE + "," + Contrato.PPV.DIRECTOR + "," + Contrato.PPV.GUIONIST + "," + Contrato.PPV.YEAR + "," + Contrato.PPV.CATEGORY+ ") " +
                    "VALUES (1,'Home Alone','Chris Columbus','John Hughes',1990, 'Cine')");
            db.execSQL("INSERT INTO " + FILMS_TABLE_NAME + " (" + Contrato.PPV._ID + "," + Contrato.PPV.TITLE + "," + Contrato.PPV.DIRECTOR + "," + Contrato.PPV.GUIONIST + "," + Contrato.PPV.YEAR + "," + Contrato.PPV.CATEGORY+ ") " +
                    "VALUES (2,'Home Alone 2','Chris Columbus','John Hughes',1990, 'Cine')");
            db.execSQL("INSERT INTO " + FILMS_TABLE_NAME + " (" + Contrato.PPV._ID + "," + Contrato.PPV.TITLE + "," + Contrato.PPV.DIRECTOR + "," + Contrato.PPV.GUIONIST + "," + Contrato.PPV.YEAR + "," + Contrato.PPV.CATEGORY +") " +
                    "VALUES (3,'WaterWorld','Peter Columbus','John Hughes',1998, 'NetFlix')");
            /*db.execSQL("INSERT INTO " + FILMS_TABLE_NAME + " (" +  Contrato.PPV._ID + "," + Contrato.PPV.TITLE + "," + Contrato.PPV.DIRECTOR + "," + Contrato.PPV.GUIONIST + "," + Contrato.PPV.YEAR + "," + Contrato.PPV.GENRE + "," + Contrato.PPV.SUBGENRE + "," + Contrato.PPV.ACTOR1 + "," + Contrato.PPV.ACTOR2 + "," + Contrato.PPV.ACTOR3 + "," + Contrato.PPV.ACTOR4+ ") " +
                    "VALUES (4,'Sistemas Microinformáticos y Redes','SMR')");*/
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + FILMS_TABLE_NAME);

            onCreate(db);
        }

    }
}

