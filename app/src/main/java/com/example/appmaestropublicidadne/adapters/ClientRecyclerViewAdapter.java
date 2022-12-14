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
import com.example.appmaestropublicidadne.client.Client;

import java.util.ArrayList;

public class ClientRecyclerViewAdapter extends RecyclerView.Adapter<ClientRecyclerViewAdapter.ViewHolder>{

    LayoutInflater inflater;
    ArrayList<Client> clientes;

    public ClientRecyclerViewAdapter (Context context, ArrayList<Client>clientes){
        this.inflater = LayoutInflater.from(context);
        this.clientes = clientes;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.lista_client,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        int codigo = clientes.get(position).getId();
        String nombre = clientes.get(position).getName();
        String estado = clientes.get(position).getRegistrationStatus();
        String cod = codigo+"";
        holder.codigo.setText(cod);
        holder.nombre.setText(nombre);
        holder.estado.setText(estado);
    }

    @Override
    public int getItemCount() {
        return clientes.size();
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
