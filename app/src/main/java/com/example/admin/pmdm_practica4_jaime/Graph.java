package com.example.admin.pmdm_practica4_jaime;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.example.admin.pmdm_practica4_jaime.DDBB.Contract;

import java.util.Calendar;

public class Graph extends AppCompatActivity {

    private Cursor c1, c2;
    private WebView webView;
    private int[] a, b;
    private Calendar d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        a = new int[]{0, 0, 0, 0, 0, 0, 0};
        b = new int[]{0, 0, 0, 0, 0, 0, 0};

        d = new Calendar() {
            @Override
            public void add(int field, int value) {

            }

            @Override
            protected void computeFields() {

            }

            @Override
            protected void computeTime() {

            }

            @Override
            public int getGreatestMinimum(int field) {
                return 0;
            }

            @Override
            public int getLeastMaximum(int field) {
                return 0;
            }

            @Override
            public int getMaximum(int field) {
                return 0;
            }

            @Override
            public int getMinimum(int field) {
                return 0;
            }

            @Override
            public void roll(int field, boolean increment) {

            }
        };
        d = Calendar.getInstance();

        obtenerDatos();

        webView = (WebView) findViewById(R.id.webView);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/graphic.html");
        webView.addJavascriptInterface(this, "InterfazAndroid");
    }

    private void obtenerDatos() {
        c1 = getContentResolver().query(Contract.TableIncoming.CONTENT_URI, null, null, null, Contract.TableIncoming.DATE + " DESC");

        c1.moveToFirst();
        while (c1.moveToNext()) {
            String[] aux2 = (c1.getString(c1.getColumnIndex(Contract.TableOutgoing.DATE)).split("/"));
            if (Integer.parseInt(aux2[1]) == d.get(Calendar.MONTH) + 1) {
                if (Integer.parseInt(aux2[0]) <= d.get(Calendar.DAY_OF_MONTH) && Integer.parseInt(aux2[0]) >= d.get(Calendar.DAY_OF_MONTH) - 7) {
                    int today = d.get(Calendar.DAY_OF_WEEK) + 1;

                    if (Integer.parseInt(aux2[0]) == d.get(Calendar.DAY_OF_MONTH)) {
                        a[today]++;//hoy
                    } else if (Integer.parseInt(aux2[0]) == (d.get(Calendar.DAY_OF_MONTH) - 1)) {
                        if (today - 1 > 0)
                            a[today - 1]++;//Ayer
                        else
                            a[7 - 1]++;
                    } else if (Integer.parseInt(aux2[0]) == (d.get(Calendar.DAY_OF_MONTH) - 2)) {
                        if (today - 2 > 0)
                            a[today - 2]++;//Antes de ayer
                        else
                            a[7 - 2]++;
                    } else if (Integer.parseInt(aux2[0]) == (d.get(Calendar.DAY_OF_MONTH) - 3)) {
                        if (today - 3 > 0)
                            a[today - 3]++;//3 dias
                        else
                            a[7 - 3]++;
                    } else if (Integer.parseInt(aux2[0]) == d.get(Calendar.DAY_OF_MONTH) - 4) {
                        if (today - 4 > 0)
                            a[today - 4]++;//4 dias
                        else
                            a[7 - 4]++;
                    } else if (Integer.parseInt(aux2[0]) == d.get(Calendar.DAY_OF_MONTH) - 5) {
                        if (today - 5 > 0)
                            a[today - 5]++;//5 dias
                        else
                            a[7 - 5]++;
                    } else if (Integer.parseInt(aux2[0]) == d.get(Calendar.DAY_OF_MONTH) - 6) {
                        if (today - 6 > 0)
                            a[today - 6]++;//6 dias
                        else
                            a[7 - 6]++;
                    }
                }
            }
        }



        c2 = getContentResolver().query(Contract.TableOutgoing.CONTENT_URI, null, null, null, Contract.TableOutgoing.DATE + " DESC");

        c2.moveToFirst();
        while (c2.moveToNext()) {
            String[] aux = (c2.getString(c2.getColumnIndex(Contract.TableOutgoing.DATE)).split("/"));
            if (Integer.parseInt(aux[1]) == d.get(Calendar.MONTH) + 1) {
                if (Integer.parseInt(aux[0]) <= d.get(Calendar.DAY_OF_MONTH) && Integer.parseInt(aux[0]) >= d.get(Calendar.DAY_OF_MONTH) - 7) {
                    int today = d.get(Calendar.DAY_OF_WEEK) - 1;

                    if (Integer.parseInt(aux[0]) == d.get(Calendar.DAY_OF_MONTH)) {
                        b[today]++;//hoy
                    } else if (Integer.parseInt(aux[0]) == (d.get(Calendar.DAY_OF_MONTH) - 1)) {
                        if (today - 1 > 0)
                            b[today - 1]++;//Ayer
                        else
                            b[7 - 1]++;
                    } else if (Integer.parseInt(aux[0]) == (d.get(Calendar.DAY_OF_MONTH) - 2)) {
                        if (today - 2 > 0)
                            b[today - 2]++;//Antes de ayer
                        else
                            b[7 - 2]++;
                    } else if (Integer.parseInt(aux[0]) == (d.get(Calendar.DAY_OF_MONTH) - 3)) {
                        if (today - 3 > 0)
                            b[today - 3]++;//3 dias
                        else
                            b[7 - 3]++;
                    } else if (Integer.parseInt(aux[0]) == d.get(Calendar.DAY_OF_MONTH) - 4) {
                        if (today - 4 > 0)
                            b[today - 4]++;//4 dias
                        else
                            b[7 - 4]++;
                    } else if (Integer.parseInt(aux[0]) == d.get(Calendar.DAY_OF_MONTH) - 5) {
                        if (today - 5 > 0)
                            b[today - 5]++;//5 dias
                        else
                            b[7 - 5]++;
                    } else if (Integer.parseInt(aux[0]) == d.get(Calendar.DAY_OF_MONTH) - 6) {
                        if (today - 6 > 0)
                            b[today - 6]++;//6 dias
                        else
                            b[7 - 6]++;
                    }
                }
            }
        }

    }


    @JavascriptInterface
    public int incoming(int pos) {
        return a[pos];
    }

    @JavascriptInterface
    public int outgoing(int pos) {
        return b[pos];
    }

    @JavascriptInterface
    public int total(int pos) {
        return a[pos] + b[pos];
    }
}
