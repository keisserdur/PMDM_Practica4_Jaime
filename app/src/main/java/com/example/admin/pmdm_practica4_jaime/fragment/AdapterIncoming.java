package com.example.admin.pmdm_practica4_jaime.fragment;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.pmdm_practica4_jaime.DDBB.Contract;
import com.example.admin.pmdm_practica4_jaime.DDBB.POJO.Incoming;
import com.example.admin.pmdm_practica4_jaime.R;

/**
 * Created by Admin on 02/02/2016.
 */
public class AdapterIncoming extends RecyclerView.Adapter<AdapterIncoming.ViewHolder> {
    private Cursor datos;
    private int resources;

    public AdapterIncoming(Cursor datos, int resources){
        this.datos=datos;
        this.resources=resources;
    }

    //Crea los nuevos objetos
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater i = (LayoutInflater) parent.getContext().getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        View view = i.inflate(resources, null);

        ViewHolder tvh = new ViewHolder(view);

        return tvh;
    }

    //Actualiza los datos del ViewHolder
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        datos.moveToPosition(position);
        Incoming in=new Incoming();
        in.setNumber(datos.getString(datos.getColumnIndex(Contract.TableIncoming.NUMBER)));
        in.setTime(datos.getString(datos.getColumnIndex(Contract.TableIncoming.TIME)));
        in.setDate(datos.getString(datos.getColumnIndex(Contract.TableIncoming.DATE)));
        holder.bindRegister(in);
    }

    //Indica el numero de elementos
    @Override
    public int getItemCount() {
        return datos.getCount();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView number;
        private TextView time;
        private TextView date;

        public ViewHolder(View itemView) {
            super(itemView);

            number = (TextView)itemView.findViewById(R.id.textView);
            time = (TextView)itemView.findViewById(R.id.textView2);
            date = (TextView)itemView.findViewById(R.id.textView3);
        }

        public void bindRegister(Incoming t) {
            number.setText(t.getNumber());
            time.setText(t.getTime());
            date.setText(t.getDate());
        }
    }
}
