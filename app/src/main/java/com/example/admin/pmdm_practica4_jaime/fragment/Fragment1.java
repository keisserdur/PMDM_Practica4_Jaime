package com.example.admin.pmdm_practica4_jaime.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.admin.pmdm_practica4_jaime.DDBB.Contract;
import com.example.admin.pmdm_practica4_jaime.R;


public class Fragment1 extends Fragment {

    private Context c;

    public static Fragment1 newInstance(Context c) {
        Fragment1 fragment = new Fragment1();
        return fragment;
    }

    public Fragment1() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        c=getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_fragment1, container, false);

        RecyclerView rv= (RecyclerView) v.findViewById(R.id.RecyclerView);

        AdapterIncoming adaptador = new AdapterIncoming(c.getContentResolver().query(Contract.TableIncoming.CONTENT_URI,null,null,null,Contract.TableIncoming.DATE+" DESC"),R.layout.item_in);
        rv.setAdapter(adaptador);

        rv.setLayoutManager(
                new LinearLayoutManager(c, LinearLayoutManager.VERTICAL, false));

        return v;
    }

}