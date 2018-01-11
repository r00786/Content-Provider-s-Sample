package com.pokemon.go.dialog;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.pokemon.go.R;
import com.pokemon.go.model.PokemonPlayers;

/**
 * Created by Lucifer on 11-01-2018.
 */

public class Dialog extends DialogFragment {
    EditText etName;
    EditText etIndex;
    CheckBox status;
    Button btsb;
    private int query;
    private DialogCallbacks dialogCallbacks;
    private PokemonPlayers pokemonPlayers;

    public interface DialogCallbacks {
        void add(PokemonPlayers pokemonPlayers);

        void delete(int index);

        void update(int index, PokemonPlayers pokemonPlayers);


    }

    public void setCallbacks(DialogCallbacks dialogCallbacks) {
        this.dialogCallbacks = dialogCallbacks;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.layout_dialog_main, container, false);
        etName = viewRoot.findViewById(R.id.et_content);
        etIndex = viewRoot.findViewById(R.id.et_index);
        status = viewRoot.findViewById(R.id.status);
        btsb = viewRoot.findViewById(R.id.bt_sb);
        if (getArguments() != null) {
            query = getArguments().getInt("query");
        }
        pokemonPlayers = new PokemonPlayers();
        btsb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (query == 0) {
                    if (dialogCallbacks != null) {
                        pokemonPlayers.setName(etName.getText().toString());
                        pokemonPlayers.setPlaysPokemonGo(status.isChecked());
                        dialogCallbacks.add(pokemonPlayers);
                    }
                } else if (query == 1) {
                    if (dialogCallbacks != null) {
                        dialogCallbacks.delete(Integer.valueOf(etIndex.getText().toString()));
                    }

                } else if (query == 2) {
                    if (dialogCallbacks != null) {
                        pokemonPlayers.setName(etName.getText().toString());
                        pokemonPlayers.setPlaysPokemonGo(status.isChecked());
                        dialogCallbacks.update(Integer.valueOf(etIndex.getText().toString()), pokemonPlayers);
                    }
                }
                dismiss();

            }
        });

        return viewRoot;
    }

    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }
}
