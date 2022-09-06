package com.example.books4all.Database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.books4all.CardItem;
import com.example.books4all.FilterFragment;

import java.util.List;

public class CardItemRepository {

    private CardItemDAO cardItemDAO;
    private LiveData<List<CardItem>> cardItemList;
    private LiveData<List<CardItem>> cardItemListLess100;//---------------------------------------------------------

    public CardItemRepository(Application application){
        CardItemDatabase db = CardItemDatabase.getDatabase(application);
        cardItemDAO = db.cardItemDAO();
        cardItemList = cardItemDAO.getCardItems();
        //cardItemListLess100 = cardItemDAO.getCardItemsLess100();//-------------------------------------------------- penso sia giusto
    }

    public LiveData<List<CardItem>> getCardItemList(){
        return cardItemList;
    }

    public void addCardItem(CardItem cardItem){
        CardItemDatabase.executor.execute(new Runnable(){
            @Override
            public void run() {
                cardItemDAO.addCardItem(cardItem);
            }
        });
    }

    public void removeCardItem(CardItem item){
        CardItemDatabase.executor.execute(new Runnable(){
            @Override
            public void run() {
                cardItemDAO.removeCardItem(item);
            }
        });
    }
//------------------------------------------------------------------------------------------------------------------- penso sia giusto
    public LiveData<List<CardItem>> getCardItemsLess100(){
        return cardItemListLess100;
    }

}
