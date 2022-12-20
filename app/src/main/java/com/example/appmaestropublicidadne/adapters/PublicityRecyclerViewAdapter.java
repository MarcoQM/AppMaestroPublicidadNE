package com.example.appmaestropublicidadne.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmaestropublicidadne.Publicity.Publicity;
import com.example.appmaestropublicidadne.R;
import com.example.appmaestropublicidadne.client.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PublicityRecyclerViewAdapter extends RecyclerView.Adapter<PublicityRecyclerViewAdapter.PublicityViewHolder> {


    List<Publicity> publicityList;
    List<Publicity> originalListPublicity;

    public PublicityRecyclerViewAdapter(List<Publicity> publicityList) {
        this.publicityList = publicityList;
        originalListPublicity = new ArrayList<>();
        originalListPublicity.addAll(publicityList);
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("*************** Publicity DETAIL***************");
                }
            });
        }
    }
}
