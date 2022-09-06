package com.example.books4all.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.books4all.CardItem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {CardItem.class}, version = 1)
public abstract class CardItemDatabase extends RoomDatabase {

    public abstract CardItemDAO cardItemDAO();

    private static volatile CardItemDatabase INSTANCE;
    static final ExecutorService executor = Executors.newFixedThreadPool(4);

    static CardItemDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (CardItemDatabase.class){
                if(INSTANCE == null) { //si richiede perchè essendo thread diversi potrebbe essere successo qualcosa nel mentre
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CardItemDatabase.class, "books_database").build();
                }
            }
        }
        return INSTANCE;
    }
}
