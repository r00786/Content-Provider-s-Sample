package com.pokemon.go.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pokemon.go.R;
import com.pokemon.go.model.PokemonPlayers;

import java.util.List;

/**
 * Created by Lucifer on 10-01-2018.
 */

public class AdapterPokemon extends RecyclerView.Adapter<AdapterPokemon.ViewHolder> {
    private Context context;
    private List<PokemonPlayers> items;
    int i;

    public AdapterPokemon(Context context) {
        this.context = context;
        i = 0;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_rv_pokemon_players, parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PokemonPlayers pokemonPlayers = items.get(position);
        holder.tvName.setText(pokemonPlayers.getName());
        if (pokemonPlayers.isPlaysPokemonGo()) {
            holder.tvStatus.setText("plays");
        } else {
            holder.tvStatus.setText("do not plays");
        }
        holder.tvno.setText(String.valueOf(i));
        i++;

    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public void addData(List<PokemonPlayers> items) {
        i=0;
        this.items = items;
        notifyDataSetChanged();


    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvno;
        private TextView tvName;
        private TextView tvStatus;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvno = itemView.findViewById(R.id.tv_index);
            tvStatus = itemView.findViewById(R.id.tv_status);
        }
    }
}
