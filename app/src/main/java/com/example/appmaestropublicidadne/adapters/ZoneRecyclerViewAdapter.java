package com.example.appmaestropublicidadne.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmaestropublicidadne.R;
import com.example.appmaestropublicidadne.activities.AddZoneActivity;
import com.example.appmaestropublicidadne.activities.ZoneActivity;
import com.example.appmaestropublicidadne.zone.Zone;
import com.example.appmaestropublicidadne.zone.ZonesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ZoneRecyclerViewAdapter extends RecyclerView.Adapter<ZoneRecyclerViewAdapter.ZoneViewHolder> {

    List<Zone> zoneList;
    List<Zone> originalListZone;
    Context context;
    ZonesRepository zonesRepository;

    public ZoneRecyclerViewAdapter(List<Zone> zoneList, Context context) {
        this.zoneList = zoneList;
        originalListZone = new ArrayList<>();
        originalListZone.addAll(zoneList);
        this.context = context;
        zonesRepository = ZonesRepository.get(this.context);
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

    public void zoneFilter(final String searchText) {
        int textLenght = searchText.length();
        if (textLenght == 0) {
            zoneList.clear();
            zoneList.addAll(originalListZone);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Zone> zoneCollection = zoneList.stream()
                        .filter(i -> i.getName().toLowerCase().contains(searchText.toLowerCase()))
                        .collect(Collectors.toList());
                zoneList.clear();
                zoneList.addAll(zoneCollection);
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

            deleteZone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AlertDialog dialogo = new AlertDialog
                            .Builder(context)
                            .setPositiveButton("Sí, eliminar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Zone zone = new Zone();
                                    zone.setId(codeZone.getText().toString());
                                    zonesRepository.deleteZone(zone);

                                    Intent intent = new Intent(context, ZoneActivity.class);
                                    context.startActivity(intent);
                                }
                            })
                            .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setTitle("Confirmar")
                            .setMessage("¿Deseas eliminar la Zona?")
                            .create();

                    dialogo.show();
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {

                EditText editTextZoneCodeDetail;
                EditText editTextZoneNameDetail;
                EditText editTextZoneEstRegDetail;

                @Override
                public void onClick(View view) {

                    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View dialogView = inflater.inflate(R.layout.activity_detail_zone, null);

                    AlertDialog builder = new AlertDialog
                            .Builder(context)
                            .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setTitle("Detalle Zona")
                            .setView(dialogView)
                            .create();


                    editTextZoneCodeDetail = dialogView.findViewById(R.id.editTextZoneCodeDetail);
                    editTextZoneNameDetail = dialogView.findViewById(R.id.editTextZoneNameDetail);
                    editTextZoneEstRegDetail = dialogView.findViewById(R.id.editTextZoneEstRegDetail);

                    editTextZoneCodeDetail.setKeyListener(null);
                    editTextZoneNameDetail.setKeyListener(null);
                    editTextZoneEstRegDetail.setKeyListener(null);

                    //Recupero todos los campos de la zona mediante una consulta con el id
                    Zone zone = zonesRepository.getZone(codeZone.getText().toString());

                    editTextZoneCodeDetail.setText(zone.getId());
                    editTextZoneNameDetail.setText(zone.getName());
                    editTextZoneEstRegDetail.setText(zone.getRegistrationStatus());

                    builder.show();

                    System.out.println("*************** Zone DETAIL***************");
                }
            });
        }
    }
}
