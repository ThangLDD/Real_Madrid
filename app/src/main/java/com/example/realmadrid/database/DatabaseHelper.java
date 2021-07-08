package com.example.realmadrid.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.realmadrid.model.Player;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, DatabaseManager.DATABASE_NAME, null, DatabaseManager.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS PLAYER(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name VARCHAR(200), " +
                "birthday VARCHAR(200), " +
                "kit INTEGER, " +
                "position VARCHAR(200))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //add
    public void addPlayer(Player player) {
        String sql = "INSERT INTO PLAYER (name, birthday, kit, position) " +
                "VALUES (?, ?, ?, ?)";

        String[] args = {player.getName(), player.getBirthday(),
                Integer.toString(player.getKit()), player.getPosition()};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL(sql, args);
    }

    //get all
    public List<Player> getAll() {
        List<Player> playerList = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("PLAYER", null, null,
                null, null, null, null);
        while (cursor != null && cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String birthday = cursor.getString(2);
            int kit = cursor.getInt(3);
            String position = cursor.getString(4);

            Player player = new Player(id, name, birthday, kit, position);
            playerList.add(player);
        }
        return playerList;
    }

    //search
    public List<Player> getPlayerByName(String name) {
        List<Player> playerList = new ArrayList<>();
        String whereClause = "name LIKE?";
        String[] whereArgs = {"%" + name + "%"};
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query("PLAYER", null, whereClause,
                whereArgs, null, null, null, null);
        while (cursor != null && cursor.moveToNext()) {
            int idS = cursor.getInt(cursor.getColumnIndex("id"));
            String nameS = cursor.getString(cursor.getColumnIndex("name"));
            String birthdayS = cursor.getString(cursor.getColumnIndex("birthday"));
            int kitS = cursor.getInt(cursor.getColumnIndex("kit"));
            String positionS = cursor.getString(cursor.getColumnIndex("position"));

            playerList.add(new Player(idS, nameS, birthdayS, kitS, positionS));
        }
        cursor.close();
        return playerList;
    }

    //update
    public int updatePlayer(Player player) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", player.getName());
        contentValues.put("birthday", player.getBirthday());
        contentValues.put("kit", player.getKit());
        contentValues.put("position", player.getPosition());

        String whereClause = "id = ?";
        String[] whereArgs = {Integer.toString(player.getId())};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.update("PLAYER", contentValues, whereClause, whereArgs);
    }

    //delete
    public int deletePlayer(int id) {
        String whereClause = "id = ?";
        String[] whereArgs = {Integer.toString(id)};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete("PLAYER", whereClause, whereArgs);
    }
}
