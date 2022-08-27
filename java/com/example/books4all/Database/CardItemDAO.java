
package com.example.books4all.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.books4all.CardItem;

import java.util.List;

@Dao
public interface CardItemDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addCardItem(CardItem cardItem);

    @Transaction //esegue la query in modo atomico
    @Query("SELECT * FROM item ORDER BY item_id DESC")
    LiveData<List<CardItem>> getCardItems();

    @Transaction//--------------------------------------------------------------------------------
    @Query("SELECT * FROM item WHERE item_page < 100 ORDER BY item_id DESC")
    LiveData<List<CardItem>> getCardItemsLess100();
}


