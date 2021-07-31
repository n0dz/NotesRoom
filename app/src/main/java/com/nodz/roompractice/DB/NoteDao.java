package com.nodz.roompractice.DB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.nodz.roompractice.Model.Note;

import java.util.List;

@Dao
public interface NoteDao {

    @Query("Select * from note_table ORDER BY priority DESC")
    LiveData<List<Note>> getAllNotes();

    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("Delete from note_table")
    void deleteAll();


}
