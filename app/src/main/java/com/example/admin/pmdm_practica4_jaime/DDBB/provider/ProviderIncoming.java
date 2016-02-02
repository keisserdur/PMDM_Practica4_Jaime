package com.example.admin.pmdm_practica4_jaime.DDBB.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.example.admin.pmdm_practica4_jaime.DDBB.Contract;
import com.example.admin.pmdm_practica4_jaime.DDBB.Helper;

/**
 * Created by Admin on 31/01/2016.
 */
public class ProviderIncoming extends ContentProvider {

    //La Uri es cm una URL. LLegamos a un mismo sitio. UriMatcher establece si es un 1 haz una cosa, si es 2 haz otra cosa.
    public static final UriMatcher convierteUri2Int;
    public static final int CANCION = 1;
    public static final int CANCION_ID = 2;


    static{
        convierteUri2Int = new UriMatcher(UriMatcher.NO_MATCH);
        convierteUri2Int.addURI(Contract.TableIncoming.AUTHORITY, Contract.TableIncoming.TABLEINCOMING, CANCION);//Le damos la instrucción de qué hacer a la URI
        convierteUri2Int.addURI(Contract.TableIncoming.AUTHORITY, Contract.TableIncoming.TABLEINCOMING + "/#", CANCION_ID);
    }

    private Helper abd;

    @Override
    public boolean onCreate() {
        abd = new Helper(getContext());
        return true;
    }



    @Nullable
    @Override
    public String getType(Uri uri) {//Devuelve el tipo mime que corresponde a la uri con que se ha llamado
        switch (convierteUri2Int.match(uri)){
            case CANCION:
                return Contract.TableIncoming.MJLTIPLE_MIME;
            case CANCION_ID:
                return Contract.TableIncoming.SINGLE_MIME;
            default:
                throw new IllegalArgumentException("Tipo de actividad desconocida " + uri);
        }
    }

    //METODO INSERT
    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // Comprobar que la uri utilizada para hacer la insercion es correcta
        if (convierteUri2Int.match(uri) != CANCION) {
            throw new IllegalArgumentException("URI desconocida : " + uri);//SI no es correcta la Uri
        }

        //Si el ContentValues es nulo, crea un ContentValues
        ContentValues contentValues;
        if (values == null) {
            throw new IllegalArgumentException("Cliente null ");
        }
        //Validar

        // Inserción de nueva fila
        SQLiteDatabase db = abd.getWritableDatabase();//Conectamos a la base de datos en modo escritura

        long rowId = db.insert(Contract.TableIncoming.TABLEINCOMING, null, values);
        if (rowId > 0) {
            //Si se ha insertado el elemento correctamente, entonces devolvemos la uri del elemento que se acaba de insertar
            Uri uri_actividad = ContentUris.withAppendedId(Contract.TableIncoming.CONTENT_URI, rowId);
            getContext().getContentResolver().notifyChange(uri_actividad, null);
            return uri_actividad;
        }
        throw new SQLException("Error al insertar fila en : " + uri);

    }

    //METODO BORRAR
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = abd.getWritableDatabase();// Vuelve a abrir la base de datos para conectar con ella en modo escritura
        int match = convierteUri2Int.match(uri);//Obtengo la uri
        int affected;
        switch (match) {
            case CANCION: //Muchos clientes
                affected = db.delete(Contract.TableIncoming.TABLEINCOMING,selection,selectionArgs);
                break;
            case CANCION_ID: //Un sólo cliente
                long idActividad = ContentUris.parseId(uri);
                affected = db.delete(Contract.TableIncoming.TABLEINCOMING, Contract.TableIncoming._ID + "= ?" , new String [] {idActividad + ""});

                break;
            default:
                throw new IllegalArgumentException("Elemento actividad desconocido: " +
                        uri);
        }
        // Notificar cambio asociado a la urigetContext().getContentResolver().notifyChange(uri, null);
        getContext().getContentResolver().notifyChange(uri, null);
        return affected;//Devuelve el numero de filas borradas
    }



    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = abd.getWritableDatabase();
        int affected;
        switch (convierteUri2Int.match(uri)) {
            case CANCION:
                affected = db.update(Contract.TableIncoming.TABLEINCOMING, values, selection, selectionArgs);
                break;
            case CANCION_ID:
                //Distintas formas de obtener el idActividad
                //uri.getLastPathSegment()
                //ContentUris.parseId(uri)
                //uri.getPathSegments().get(l)
                String idActividad = uri.getPathSegments().get(1);
                affected = db.update(Contract.TableIncoming.TABLEINCOMING, values,
                        Contract.TableIncoming._ID + "= ?" , new String [] {idActividad});
                break;
            default:
                throw new IllegalArgumentException("URI desconocida: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return affected;
    }


    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        // Obtener base de datos
        SQLiteDatabase db = abd.getReadableDatabase();
        // Comparar Uri
        int match = convierteUri2Int.match(uri);

        Cursor c;

        switch (match) {
            case CANCION:
                // Consultando todos los registros
                c = db.query(Contract.TableIncoming.TABLEINCOMING, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case CANCION_ID:
                // Consultando un solo registro basado en el Id del Uri
                long idActividad = ContentUris.parseId(uri);
                c = db.query(Contract.TableIncoming.TABLEINCOMING, projection, Contract.TableIncoming._ID + "= ? " , new String [] {idActividad + ""},
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("URI no soportada: " + uri);
        }
        c.setNotificationUri(getContext().getContentResolver(), Contract.TableIncoming.CONTENT_URI);
        return c;
    }

}