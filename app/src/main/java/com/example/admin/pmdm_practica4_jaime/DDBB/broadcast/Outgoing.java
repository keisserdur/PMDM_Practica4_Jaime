package com.example.admin.pmdm_practica4_jaime.DDBB.broadcast;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.example.admin.pmdm_practica4_jaime.DDBB.Contract;

import java.util.Date;

/**
 * Created by Admin on 01/02/2016.
 */
public class Outgoing extends BroadcastReceiver {

    private SharedPreferences pc;

    @Override
    public void onReceive(final Context context, final Intent intent) {

        pc = context.getSharedPreferences("MiPreferencia", Context.MODE_PRIVATE);

        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        tm.listen(new PhoneStateListener() {

            public void onCallStateChanged(int state, String incomingNumber) {

                if (state == 2 && !intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER).isEmpty() && pc.getBoolean("vez", true)) {
                    try {
                        Log.v("AYUDAME", "llamada saliente a " + intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER) + " estado " + state);
                        SharedPreferences.Editor ed = pc.edit();
                        ed.putBoolean("vez", false);
                        ed.commit();

                        Date d = new Date();
                        Uri uri = Contract.TableOutgoing.CONTENT_URI;
                        ContentValues valores = new ContentValues();
                        valores.put(Contract.TableOutgoing.NUMBER, intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER));
                        valores.put(Contract.TableOutgoing.DATE, d.getDay() + "/" + (d.getMonth()+1) + "/" + (d.getYear() + 1900));
                        String hora = "";
                        if (d.getMinutes() < 10)
                            hora = d.getHours() + ":0" + d.getMinutes();
                        else
                            hora = d.getHours() + ":" + d.getMinutes();
                        valores.put(Contract.TableOutgoing.TIME, "" + hora);
                        Log.v("AYUDAME","Valores "+valores.toString());
                        Uri u = context.getContentResolver().insert(uri, valores);


                    } catch (Exception ex) {

                    }

                } else {
                    SharedPreferences.Editor ed = pc.edit();
                    ed.putBoolean("vez", true);
                    ed.commit();
                }
            }
        }, PhoneStateListener.LISTEN_CALL_STATE);
    }
}
