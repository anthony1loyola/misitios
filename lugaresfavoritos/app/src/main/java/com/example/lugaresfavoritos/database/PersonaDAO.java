package com.example.lugaresfavoritos.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface PersonaDAO {
    @Query("select * from persona")
    List<Persona> getPersonas();

    @Query("select * from persona where id LIKE:uuid")
    Persona getPersona(String uuid);

    //insert,update,delete
    @Insert
    void insertPersona(Persona persona);

    @Update
    void updatePersona(Persona persona);
    @Delete
    void deletePersona(Persona persona);


}
