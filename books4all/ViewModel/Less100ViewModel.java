package com.example.books4all.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.books4all.CardItem;
import com.example.books4all.Database.CardItemRepository;

import java.util.List;

public class Less100ViewModel extends AndroidViewModel {


    private LiveData<List<CardItem>> cardItemsLess100;

    public Less100ViewModel(@NonNull Application application) {
        super(application);
        CardItemRepository repository = new CardItemRepository(application);
        cardItemsLess100 = repository.getCardItemsLess100();
    }

    public LiveData<List<CardItem>> getCardItemsLess100() {
        return cardItemsLess100;
    }
/*
    public void setCardItemsLess100(LiveData<List<CardItem>> cardItemsLess100) {
        this.cardItemsLess100 = cardItemsLess100;
    }

 */
}
