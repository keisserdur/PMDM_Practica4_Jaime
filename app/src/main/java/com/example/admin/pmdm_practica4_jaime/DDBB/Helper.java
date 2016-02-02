package com.example.admin.pmdm_practica4_jaime.DDBB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Admin on 31/01/2016.
 */
public class Helper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="CallLog.sqlite";
    public static final int DATABASE_VERSION=1;

    public Helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.v("AYUDAME","database");
        String sql;
        sql="CREATE TABLE "+ Contract.TableIncoming.TABLEINCOMING +" (" +
                Contract.TableIncoming._ID+" integer primary key autoincrement," +
                Contract.TableIncoming.NUMBER+" text ," +
                Contract.TableIncoming.DATE+" text, " +
                Contract.TableIncoming.TIME+" text)";
        db.execSQL(sql);

        sql="CREATE TABLE "+ Contract.TableOutgoing.TABLEOUTGOING +" (" +
                Contract.TableOutgoing._ID+" integer primary key autoincrement," +
                Contract.TableOutgoing.NUMBER+" text," +
                Contract.TableOutgoing.DATE+" text," +
                Contract.TableOutgoing.TIME+" text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql="drop table if exists "
                + Contract.TableOutgoing.TABLEOUTGOING;
        db.execSQL(sql);

        sql="drop table if exists "
                + Contract.TableIncoming.TABLEINCOMING;
        db.execSQL(sql);
    }
}
