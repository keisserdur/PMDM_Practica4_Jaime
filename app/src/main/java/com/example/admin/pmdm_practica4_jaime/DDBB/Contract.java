package com.example.admin.pmdm_practica4_jaime.DDBB;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Admin on 31/01/2016.
 */
public class Contract {
    private Contract(){

    }

    public static abstract class TableIncoming implements BaseColumns{
        public static final String TABLEINCOMING="incoming";
        public static final String NUMBER="number";
        public static final String DATE="date";
        public static final String TIME="time";
        public static final String STATUS="status";

        public final static String AUTHORITY = "com.example.admin.pmdm_practica4_jaime.DDBB.Incoming";

        public final static Uri CONTENT_URI =
                Uri.parse("content://" + AUTHORITY + "/" + TABLEINCOMING);
        public final static String SINGLE_MIME =
                "vnd.android.cursor.item/vnd." + AUTHORITY + TABLEINCOMING;
        public final static String MJLTIPLE_MIME =
                "vnd.android.cursor.dir/vnd." + AUTHORITY + TABLEINCOMING;

    }

    public static abstract class TableOutgoing implements BaseColumns {
        public static final String TABLEOUTGOING = "outgoing";
        public static final String NUMBER = "number";
        public static final String DATE = "date";
        public static final String TIME = "time";
        public static final String STATUS="status";


        public final static String AUTHORITY = "com.example.admin.pmdm_practica4_jaime.DDBB.Outgoing";

        public final static Uri CONTENT_URI =
                Uri.parse("content://" + AUTHORITY + "/" + TABLEOUTGOING);
        public final static String SINGLE_MIME =
                "vnd.android.cursor.item/vnd." + AUTHORITY + TABLEOUTGOING;
        public final static String MJLTIPLE_MIME =
                "vnd.android.cursor.dir/vnd." + AUTHORITY + TABLEOUTGOING;
    }
}
