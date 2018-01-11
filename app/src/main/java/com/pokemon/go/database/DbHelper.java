package com.pokemon.go.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.pokemon.go.model.PokemonPlayers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucifer on 10-01-2018.
 * <p>
 * Db class for pokemon player
 */

public class DbHelper extends SQLiteOpenHelper {
    //Db version
    private static int DB_VERSION = 1;
    private static final String LOG_TAG = DbHelper.class.getSimpleName();

    //Sql statement to create a table
    private String CREATE_TABLE = "CREATE TABLE " + DbContract.TABLE_NAME + " (" + DbContract.ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + DbContract.PLAYER_NAME + " TEXT, " +
            DbContract.STATUS + " BOOLEAN" + " );";

    /**
     * Constructor matching super
     *
     * @param context context of the invoking Activity
     */
    public DbHelper(Context context) {
        super(context, DbContract.DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Create Table
        sqLiteDatabase.execSQL(CREATE_TABLE);

        //For checking the query is precise or not
        Log.i(LOG_TAG, "Creating table with query: " + CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        //Deleting the table if exists
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DbContract.TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    /**
     * Method to add a new Row in database
     * <p>
     * * @param pokePlayer pokeplayer obj to add into the database
     */
    public void insertPokePlayer(PokemonPlayers pokePlayer) {

        Log.i(LOG_TAG, "Added a PokePlayer  - " + pokePlayer.toString());

        //Getting a reference to writable db
        SQLiteDatabase writableDatabase = this.getWritableDatabase();

        //Creating content values to Add data

        ContentValues contentValues = new ContentValues();
        contentValues.put(DbContract.PLAYER_NAME, pokePlayer.getName());
        contentValues.put(DbContract.STATUS, pokePlayer.isPlaysPokemonGo());
        //Insert data to table
        writableDatabase.insert(DbContract.TABLE_NAME, /*Table name*/
                null, contentValues);

        //Remember to close the db

        writableDatabase.close();


    }

    /**
     * Method for getting the database and adding the rows into a list
     *
     * @return Pokemon Players List
     */
    public List<PokemonPlayers> getPokePlayers() {
        //Getting ref to Readable database
        SQLiteDatabase db = this.getWritableDatabase();

        List<PokemonPlayers> pokemonPlayers = new ArrayList<>();

        Cursor cursor = db.query(DbContract.TABLE_NAME,
                DbContract.PROJECTIONS,
                null,
                null,
                null,
                null,
                null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            PokemonPlayers obj = cursorToAndroidVersion(cursor);
            pokemonPlayers.add(obj);
            cursor.moveToNext();
        }
        //close the cursor
        cursor.close();

        //close the db
        db.close();

        return pokemonPlayers;


    }

    /**
     * Convert a cursor to a Pokemon Players Object object
     *
     * @param cursor cursor obj of the db
     * @return
     */
    public PokemonPlayers cursorToAndroidVersion(Cursor cursor) {

        PokemonPlayers pokemonPlayers = new PokemonPlayers();

        pokemonPlayers.setName(cursor.getString(cursor.getColumnIndex(DbContract.PLAYER_NAME)));
        pokemonPlayers.setPlaysPokemonGo(cursor.getInt(cursor.getColumnIndex(DbContract.STATUS)) > 0);

        return pokemonPlayers;
    }

    /**
     * Deleting a row from the database
     *
     * @param dbIndex index  of the row to be deleted
     */
    public void deleteFromDb(int dbIndex) {

        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.execSQL("DELETE FROM " + DbContract.TABLE_NAME + " WHERE " + DbContract.ID + "='" + dbIndex + "'");
        } catch (RuntimeException ignored) {
            //Silent Catch
        }
        db.close();

    }

    /**
     * updating a row in a db
     *
     * @param pokemonPlayers obj to be considered
     * @param dbIndex        index of the row to be updated
     */
    public void updateDatabase(PokemonPlayers pokemonPlayers, int dbIndex) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put(DbContract.PLAYER_NAME, pokemonPlayers.getName());
        data.put(DbContract.STATUS, pokemonPlayers.isPlaysPokemonGo());
        try {
            sqLiteDatabase.update(DbContract.TABLE_NAME, data, DbContract.ID + " = " + dbIndex, null);
        } catch (RuntimeException ignored) {
            //Silent catch
        }

        sqLiteDatabase.close();
    }

}
