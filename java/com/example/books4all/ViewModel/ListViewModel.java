package com.example.books4all.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.books4all.CardItem;
import com.example.books4all.Database.CardItemRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListViewModel extends AndroidViewModel {

    private final MutableLiveData<CardItem> itemSelected = new MutableLiveData<>();
    private LiveData<List<CardItem>> cardItems;
    private LiveData<List<CardItem>> cardItemsLess100;//--------------------------------------------------

    public ListViewModel(@NonNull Application application) {
        super(application);
        CardItemRepository repository = new CardItemRepository(application);
        cardItems = repository.getCardItemList();
        //cardItemsLess100 = repository.getCardItemsLess100();//--------------------------------------------
    }

    public LiveData<List<CardItem>> getCardItems() {
        return cardItems;
    }
/*
    public LiveData<List<CardItem>> getCardItemsLess100() {//--------------------------------------------
        return cardItemsLess100;
    }

 */

    public MutableLiveData<CardItem> getItemSelected() {
        return itemSelected;
    }

    public void setItemSelected (CardItem cardItem){
        itemSelected.setValue(cardItem);
    }
}


