package com.pokemon.go.activities;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.pokemon.go.R;
import com.pokemon.go.adapters.AdapterPokemon;
import com.pokemon.go.database.DbHelper;
import com.pokemon.go.dialog.Dialog;
import com.pokemon.go.model.PokemonPlayers;

public class MainActivity extends AppCompatActivity implements Dialog.DialogCallbacks {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    AdapterPokemon adapterPokemon;
    private DbHelper sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv_pokemon);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapterPokemon = new AdapterPokemon(this);
        recyclerView.setAdapter(adapterPokemon);

        sqLiteDatabase = new DbHelper(this);


        adapterPokemon.addData(sqLiteDatabase.getPokePlayers());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.add:
                Dialog dialog = new Dialog();
                Bundle bundle = new Bundle();
                bundle.putInt("query", 0);
                dialog.setCallbacks(this);
                dialog.setArguments(bundle);
                dialog.show(getSupportFragmentManager(), "");
                break;
            case R.id.delete:
                Dialog dialog1 = new Dialog();
                Bundle bundle1 = new Bundle();
                bundle1.putInt("query", 1);
                dialog1.setCallbacks(this);
                dialog1.setArguments(bundle1);
                dialog1.show(getSupportFragmentManager(), "");
                break;
            case R.id.update:
                Dialog dialog2 = new Dialog();
                Bundle bundle2 = new Bundle();
                bundle2.putInt("query", 2);
                dialog2.setCallbacks(this);
                dialog2.setArguments(bundle2);
                dialog2.show(getSupportFragmentManager(), "");
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void add(PokemonPlayers pokemonPlayers) {
        sqLiteDatabase.insertPokePlayer(pokemonPlayers);
        adapterPokemon.addData(sqLiteDatabase.getPokePlayers());
    }

    @Override
    public void delete(int index) {
        sqLiteDatabase.deleteFromDb(index);
        adapterPokemon.addData(sqLiteDatabase.getPokePlayers());

    }

    @Override
    public void update(int index, PokemonPlayers pokemonPlayers) {
        sqLiteDatabase.updateDatabase(pokemonPlayers, index);
        adapterPokemon.addData(sqLiteDatabase.getPokePlayers());


    }
}
