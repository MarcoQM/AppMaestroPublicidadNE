package com.example.appmaestropublicidadne.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmaestropublicidadne.R;
import com.example.appmaestropublicidadne.zone.Zone;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ZoneRecyclerViewAdapter extends RecyclerView.Adapter<ZoneRecyclerViewAdapter.ZoneViewHolder> {

    List<Zone> zoneList;
    List<Zone> originalListZone;

    public ZoneRecyclerViewAdapter(List<Zone> zoneList) {
        this.zoneList = zoneList;
        originalListZone = new ArrayList<>();
        originalListZone.addAll(zoneList);
    }

    @NonNull
    @Override
    public ZoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, null, false);
        return new ZoneViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ZoneViewHolder holder, int position) {
        holder.codeZone.setText(zoneList.get(position).getId());
        holder.nameZone.setText(zoneList.get(position).getName());
        holder.estRegZone.setText(zoneList.get(position).getRegistrationStatus());
    }

    @Override
    public int getItemCount() {
        return zoneList.size();
    }

    public void plasticFilter(final String searchText) {
        int textLenght = searchText.length();
        if (textLenght == 0) {
            zoneList.clear();
            zoneList.addAll(originalListZone);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Zone> plasticCollection = zoneList.stream()
                        .filter(i -> i.getName().toLowerCase().contains(searchText.toLowerCase()))
                        .collect(Collectors.toList());
                zoneList.clear();
                zoneList.addAll(plasticCollection);
            } else {
                for (Zone c : originalListZone) {
                    if (c.getName().toLowerCase().contains(searchText.toLowerCase())) {
                        zoneList.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    public class ZoneViewHolder extends RecyclerView.ViewHolder {

        TextView codeZone;
        TextView nameZone;
        TextView estRegZone;
        ImageButton editZone;
        ImageButton deleteZone;

        public ZoneViewHolder(@NonNull View itemView) {
            super(itemView);

            codeZone = itemView.findViewById(R.id.recyclerItemCode);
            nameZone = itemView.findViewById(R.id.recyclerItemname);
            estRegZone = itemView.findViewById(R.id.recyclerItemEstReg);
            editZone = itemView.findViewById(R.id.recyclerItemButtonUpdate);
            deleteZone = itemView.findViewById(R.id.recyclerItemButtonDelete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("*************** Zone DETAIL***************");
                }
            });
        }
    }
}
