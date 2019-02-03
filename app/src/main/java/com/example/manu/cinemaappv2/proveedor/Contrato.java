package com.example.manu.cinemaappv2.proveedor;

import android.net.Uri;
import android.provider.BaseColumns;

public class Contrato {

    public static final String AUTHORITY = "com.example.manu.cinemaappv2.proveedor.ProveedorDeContenido";

    public static final class PPV implements BaseColumns {

        public static final Uri CONTENT_URI = Uri
                .parse("content://" + AUTHORITY + "/ppvFilms");
        //TABLE COLUMNS
        public static final String TITLE = "Title";
        public static final String DIRECTOR = "Director";
        public static final String GUIONIST = "Guionist";
        public static final String YEAR = "Year";
        public static final String CATEGORY = "Category";
    }
}
