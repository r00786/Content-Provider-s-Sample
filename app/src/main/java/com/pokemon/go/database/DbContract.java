package com.pokemon.go.database;

import android.provider.BaseColumns;

/**
 * Created by Lucifer on 10-01-2018.
 * DbContract class for Db components
 */

public class DbContract implements BaseColumns {
    //Database Name
    public static final String DB_NAME = "pokemon.db";

    //Table Name
    public static final String TABLE_NAME = "pokemonplayers";

    //Defining columns
    //S no.
    public static final String ID = BaseColumns._ID;

    //Player Name
    public static final String PLAYER_NAME = "playername";

    //Player status (whether he plays or not, it will be a bool in db)
    public static final String STATUS = "status";

    //Defining projections for table
    public static final String[] PROJECTIONS=new String[]{
     /*this signifies column number*//*0*/DbContract.ID,
            /*1*/DbContract.PLAYER_NAME,
            /*2*/DbContract.STATUS
    };


}
