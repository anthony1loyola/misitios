package com.example.lugaresfavoritos.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Persona.class},version = 1)
public abstract class PersonaDatabase extends RoomDatabase {
    public abstract PersonaDAO getPersonaDAO();


}
