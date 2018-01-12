package com.pokemon.go.model;

/**
 * Created by Lucifer on 10-01-2018.
 */

/**
 * model class for a pokemon player
 */
public class PokemonPlayers {
    /**
     * Name of the pokemon player
     */
    private String name;
    /**
     * bool whether one plays or not
     */

    /**
     * Id of the player
     */
    private String id;
    private boolean playsPokemonGo;

    public boolean isPlaysPokemonGo() {
        return playsPokemonGo;
    }

    public void setPlaysPokemonGo(boolean playsPokemonGo) {
        this.playsPokemonGo = playsPokemonGo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
