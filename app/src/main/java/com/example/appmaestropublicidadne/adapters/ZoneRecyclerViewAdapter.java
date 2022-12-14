package com.example.appmaestropublicidadne.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmaestropublicidadne.R;
import com.example.appmaestropublicidadne.zone.Zone;

import java.util.ArrayList;

public class ZoneRecyclerViewAdapter extends RecyclerView.Adapter<ZoneRecyclerViewAdapter.ViewHolder>{

    LayoutInflater inflater;
    ArrayList<Zone> zonas;

    public ZoneRecyclerViewAdapter (Context context, ArrayList<Zone>zonas){
        this.inflater = LayoutInflater.from(context);
        this.zonas = zonas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.lista_client,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int codigo = zonas.get(position).getId();
        String nombre = zonas.get(position).getName();
        String estado = zonas.get(position).getRegistrationStatus();
        String cod = codigo+"";
        holder.codigo.setText(cod);
        holder.nombre.setText(nombre);
        holder.estado.setText(estado);
    }

    @Override
    public int getItemCount() {
        return zonas.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        CardView listClient;
        TextView codigo, nombre, estado;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            codigo = itemView.findViewById(R.id.codigo);
            nombre = itemView.findViewById(R.id.nombre);
            estado = itemView.findViewById(R.id.estado);
            listClient = itemView.findViewById(R.id.lisClient);
        }
    }
}
