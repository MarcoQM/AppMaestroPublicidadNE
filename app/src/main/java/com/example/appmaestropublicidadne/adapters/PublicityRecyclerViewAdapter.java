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

import com.example.appmaestropublicidadne.Publicity.PublicitiesRepository;
import com.example.appmaestropublicidadne.Publicity.Publicity;
import com.example.appmaestropublicidadne.R;
import com.example.appmaestropublicidadne.activities.EditPublicityActivity;
import com.example.appmaestropublicidadne.activities.PublicityActivity;
import com.example.appmaestropublicidadne.activities.ZoneActivity;
import com.example.appmaestropublicidadne.client.Client;
import com.example.appmaestropublicidadne.client.ClientsRepository;
import com.example.appmaestropublicidadne.zone.Zone;
import com.example.appmaestropublicidadne.zone.ZonesRepository;

import java.sql.SQLClientInfoException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PublicityRecyclerViewAdapter extends RecyclerView.Adapter<PublicityRecyclerViewAdapter.PublicityViewHolder> {


    List<Publicity> publicityList;
    List<Publicity> originalListPublicity;
    Context context;
    PublicitiesRepository publicitiesRepository;

    public PublicityRecyclerViewAdapter(List<Publicity> publicityList, Context context) {
        originalListPublicity = new ArrayList<>();
        this.publicityList = new ArrayList<>();
        for (Publicity z : publicityList){
            if (z.getRegistrationStatus().equalsIgnoreCase("A")||z.getRegistrationStatus().equalsIgnoreCase("I")){
                originalListPublicity.add(z);
                this.publicityList.add(z);
            }
        }

        this.context = context;
        publicitiesRepository = PublicitiesRepository.get(this.context);
    }

    @NonNull
    @Override
    public PublicityRecyclerViewAdapter.PublicityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, null, false);
        return new PublicityRecyclerViewAdapter.PublicityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PublicityRecyclerViewAdapter.PublicityViewHolder holder, int position) {
        holder.codePublicity.setText(publicityList.get(position).getId());
        holder.namePublicity.setText(publicityList.get(position).getName());
        holder.estRegPublicity.setText(publicityList.get(position).getRegistrationStatus());
    }

    @Override
    public int getItemCount() {
        return publicityList.size();
    }

    public void publicityFilter(final String searchText) {
        int textLenght = searchText.length();
        if (textLenght == 0) {
            publicityList.clear();
            publicityList.addAll(originalListPublicity);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Publicity> publicityCollection = publicityList.stream()
                        .filter(i -> i.getName().toLowerCase().contains(searchText.toLowerCase()))
                        .collect(Collectors.toList());
                publicityList.clear();
                publicityList.addAll(publicityCollection);
            } else {
                for (Publicity c : originalListPublicity) {
                    if (c.getName().toLowerCase().contains(searchText.toLowerCase())) {
                        publicityList.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    public class PublicityViewHolder extends RecyclerView.ViewHolder {

        TextView codePublicity;
        TextView namePublicity;
        TextView estRegPublicity;
        ImageButton editPublicity;
        ImageButton deletePublicity;

        public PublicityViewHolder(@NonNull View itemView) {
            super(itemView);

            codePublicity = itemView.findViewById(R.id.recyclerItemCode);
            namePublicity = itemView.findViewById(R.id.recyclerItemname);
            estRegPublicity = itemView.findViewById(R.id.recyclerItemEstReg);
            editPublicity = itemView.findViewById(R.id.recyclerItemButtonUpdate);
            deletePublicity = itemView.findViewById(R.id.recyclerItemButtonDelete);


            editPublicity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, EditPublicityActivity.class);
                    //Le envio el id de la publicidad que quiero editar
                    intent.putExtra("ID", codePublicity.getText());
                    context.startActivity(intent);
                }
            });



            deletePublicity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AlertDialog dialogo = new AlertDialog
                            .Builder(context)
                            .setPositiveButton("Sí, eliminar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Publicity publicity = new Publicity();
                                    publicity.setId(codePublicity.getText().toString());
                                    publicitiesRepository.deletePublicity(publicity);

                                    Intent intent = new Intent(context, PublicityActivity.class);
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
                            .setMessage("¿Deseas eliminar la Publicidad?")
                            .create();

                    dialogo.show();
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    EditText editTextPublicityCodeDetail;
                    EditText editTextPublicityNameDetail;
                    EditText editTextPublicityClientDetail;
                    EditText editTextPublicityZoneDetail;
                    EditText editTextPublicityEstRegDetail;

                    //Consultas de la tabla relacionada
                    ClientsRepository clientsRepository = ClientsRepository.get(context);
                    ZonesRepository zonesRepository = ZonesRepository.get(context);

                    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View dialogView = inflater.inflate(R.layout.activity_detail_publicity, null);

                    AlertDialog builder = new AlertDialog
                            .Builder(context)
                            .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setTitle("Detalle Publicidad")
                            .setView(dialogView)
                            .create();


                    editTextPublicityCodeDetail = dialogView.findViewById(R.id.editTextPublicityCodeDetail);
                    editTextPublicityNameDetail = dialogView.findViewById(R.id.editTextPublicityNameDetail);
                    editTextPublicityClientDetail = dialogView.findViewById(R.id.editTextPublicityClientDetail);
                    editTextPublicityZoneDetail = dialogView.findViewById(R.id.editTextPublicityZoneDetail);
                    editTextPublicityEstRegDetail = dialogView.findViewById(R.id.editTextPublicityEstRegDetail);

                    editTextPublicityCodeDetail.setKeyListener(null);
                    editTextPublicityNameDetail.setKeyListener(null);
                    editTextPublicityClientDetail.setKeyListener(null);
                    editTextPublicityZoneDetail.setKeyListener(null);
                    editTextPublicityEstRegDetail.setKeyListener(null);

                    //Recupero todos los campos del cliente mediante una consulta con el id
                    Publicity publicity = publicitiesRepository.getPublicity(codePublicity.getText().toString());

                    editTextPublicityCodeDetail.setText(publicity.getId());
                    editTextPublicityNameDetail.setText(publicity.getName());
                    editTextPublicityClientDetail.setText(clientsRepository.getClient(publicity.getClientId()).getName());
                    editTextPublicityZoneDetail.setText(zonesRepository.getZone(publicity.getClientId()).getName());
                    editTextPublicityEstRegDetail.setText(publicity.getRegistrationStatus());


                    builder.show();

                    System.out.println("*************** Publicity DETAIL***************");
                }
            });
        }
    }
}
