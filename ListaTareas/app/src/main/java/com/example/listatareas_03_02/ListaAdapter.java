package com.example.listatareas_03_02;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListaAdapter extends RecyclerView.Adapter<ListaAdapter.MiVistaHolder> implements View.OnClickListener{
    private ArrayList<ListaItem> miLista;
    private final int [] colores={R.drawable.ic_green,R.drawable.ic_yellow,R.drawable.ic_red};
    private View.OnClickListener listener;

    public ListaAdapter(ArrayList<ListaItem>al){
        miLista=al;
    }



    public class MiVistaHolder extends RecyclerView.ViewHolder {
        TextView tvNombre,tvLugar;
        ImageView iv;
        public MiVistaHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre= itemView.findViewById(R.id.tvDNombre);
            tvLugar=itemView.findViewById(R.id.tvLugar);
            iv=itemView.findViewById(R.id.ivImpor);
        }
    }

    @NonNull
    @Override
    public MiVistaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v= inflater.inflate(R.layout.lista_layout,parent,false);
        v.setOnClickListener(this);
        return new MiVistaHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MiVistaHolder holder, int position) {
        holder.tvNombre.setText(miLista.get(position).getNombre());
        holder.tvLugar.setText(miLista.get(position).getLugar());
        int imp=miLista.get(position).getImportancia();
        switch (imp){
            case 1:
                holder.iv.setImageResource(colores[0]);
                break;
            case 2:
                holder.iv.setImageResource(colores[1]);
                break;
            default:
                holder.iv.setImageResource(colores[2]);

        }
    }

    @Override
    public int getItemCount() {
        return miLista.size();
    }

    public void setOnClickListener(View.OnClickListener lis){
        listener=lis;
    }

    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }

    }

}
