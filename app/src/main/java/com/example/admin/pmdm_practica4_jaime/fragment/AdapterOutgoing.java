package com.example.admin.pmdm_practica4_jaime.fragment;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.pmdm_practica4_jaime.DDBB.Contract;
import com.example.admin.pmdm_practica4_jaime.DDBB.POJO.Outgoing;
import com.example.admin.pmdm_practica4_jaime.R;

/**
 * Created by Admin on 02/02/2016.
 */
public class AdapterOutgoing extends RecyclerView.Adapter<AdapterOutgoing.ViewHolder> {
    private Cursor datos;
    private int resources;

    public AdapterOutgoing(Cursor datos, int resources){
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
        Outgoing in=new Outgoing();
        in.setNumber(datos.getString(datos.getColumnIndex(Contract.TableOutgoing.NUMBER)));
        in.setTime(datos.getString(datos.getColumnIndex(Contract.TableOutgoing.TIME)));
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

        public void bindRegister(Outgoing t) {
            number.setText(t.getNumber());
            time.setText(t.getTime());
            date.setText(t.getDate());
        }
    }
}