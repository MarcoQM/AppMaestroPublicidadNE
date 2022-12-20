package com.example.appmaestropublicidadne.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmaestropublicidadne.R;
import com.example.appmaestropublicidadne.client.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClientRecyclerViewAdapter extends RecyclerView.Adapter<ClientRecyclerViewAdapter.ClientViewHolder> {

    List<Client> clientList;
    List<Client> originalListClient;

    public ClientRecyclerViewAdapter(List<Client> clientList) {
        this.clientList = clientList;
        originalListClient = new ArrayList<>();
        originalListClient.addAll(clientList);
    }

    @NonNull
    @Override
    public ClientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, null, false);
        return new ClientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientViewHolder holder, int position) {
        holder.codeClient.setText(clientList.get(position).getId());
        holder.nameClient.setText(clientList.get(position).getName());
        holder.estRegClient.setText(clientList.get(position).getRegistrationStatus());
    }

    @Override
    public int getItemCount() {
        return clientList.size();
    }

    public void clientFilter(final String searchText) {
        int textLenght = searchText.length();
        if (textLenght == 0) {
            clientList.clear();
            clientList.addAll(originalListClient);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Client> clientCollection = clientList.stream()
                        .filter(i -> i.getName().toLowerCase().contains(searchText.toLowerCase()))
                        .collect(Collectors.toList());
                clientList.clear();
                clientList.addAll(clientCollection);
            } else {
                for (Client c : originalListClient) {
                    if (c.getName().toLowerCase().contains(searchText.toLowerCase())) {
                        clientList.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    public class ClientViewHolder extends RecyclerView.ViewHolder {

        TextView codeClient;
        TextView nameClient;
        TextView estRegClient;
        ImageButton editClient;
        ImageButton deleteClient;

        public ClientViewHolder(@NonNull View itemView) {
            super(itemView);

            codeClient = itemView.findViewById(R.id.recyclerItemCode);
            nameClient = itemView.findViewById(R.id.recyclerItemname);
            estRegClient = itemView.findViewById(R.id.recyclerItemEstReg);
            editClient = itemView.findViewById(R.id.recyclerItemButtonUpdate);
            deleteClient = itemView.findViewById(R.id.recyclerItemButtonDelete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("*************** Client DETAIL***************");
                }
            });
        }
    }
}
