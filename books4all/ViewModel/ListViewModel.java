package com.example.books4all.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.books4all.CardItem;
import com.example.books4all.Database.CardItemRepository;

import java.util.ArrayList;
import java.util.List;

public class ListViewModel extends AndroidViewModel {

    private final MutableLiveData<CardItem> itemSelected = new MutableLiveData<>();
    private LiveData<List<CardItem>> cardItemsDb;
    private LiveData<List<CardItem>> cardItems; //= new MutableLiveData<>();
    private MutableLiveData<List<CardItem>> cardItemsLess100;
    private MutableLiveData<List<CardItem>> cardItemsMore100;
    private MutableLiveData<List<CardItem>> cardItemsGiallo;
    private MutableLiveData<List<CardItem>> cardItemsNature;
    private MutableLiveData<List<CardItem>> cardItemsFantasy;
    private MutableLiveData<List<CardItem>> cardItemsIta;
    private MutableLiveData<List<CardItem>> cardItemsUk;
    private MutableLiveData<List<CardItem>> cardItemsUsa;

    CardItemRepository repository;

    public ListViewModel(@NonNull Application application) {
        super(application);
        repository = new CardItemRepository(application);
        cardItems = repository.getCardItemList();
        //setValue();

        //cardItems = (MutableLiveData<List<CardItem>>) repository.getCardItemList().getValue();
        //cardItemsLess100 = repository.getCardItemsLess100();//--------------------------------------------
    }

    private void setValue() {
        //cardItems.setValue(repository.getCardItemList().getValue());
    }

    public LiveData<List<CardItem>> getCardItems() {
        /*if (cardItems == null){
            cardItems = new MutableLiveData<>();
        }

         */
        //return cardItems;
        //setValue();
        return this.cardItems;
    }

    public MutableLiveData<List<CardItem>> getCardItemsLess100() {
        System.out.println("1");
        less100Builder();
        return this.cardItemsLess100;
    }

    private void less100Builder(){
        System.out.println("0");
        ArrayList<CardItem> list = new ArrayList<>();
        this.cardItemsLess100 = new MutableLiveData<>();
        setValue();
        if(cardItems.getValue() != null) {
            System.out.println("-1");
            for (CardItem x : this.cardItems.getValue()) {
                int page = x.getBookPage();
                if (page < 100) {
                    list.add(x);
                    System.out.println("less100builder");//////////////////////////////////
                }
            }
        }
        this.cardItemsLess100.setValue(list);
    }

    public MutableLiveData<List<CardItem>> getCardItemsMore100() {
        more100Builder();
        return cardItemsMore100;
    }

    private void more100Builder() {
        ArrayList<CardItem> list = new ArrayList<>();
        this.cardItemsMore100 = new MutableLiveData<>();
        setValue();
        if(cardItems.getValue() != null) {
            for (CardItem x : this.cardItems.getValue()) {
                int page = x.getBookPage();
                if (page >= 100) {
                    list.add(x);
                }
            }
        }
        this.cardItemsMore100.setValue(list);
    }

    public LiveData<List<CardItem>> getCardItemsGiallo() {
        gialloBuilder();
        return cardItemsGiallo;
    }

    private void gialloBuilder(){
        ArrayList<CardItem> list = new ArrayList<>();
        this.cardItemsGiallo = new MutableLiveData<>();
        setValue();
        if(cardItems.getValue() != null) {
            for (CardItem x : this.cardItems.getValue()) {
                String genere = x.getBookType();
                if (genere.equals("Giallo")) {
                    list.add(x);
                }
            }
        }
        this.cardItemsGiallo.setValue(list);
    }

    public LiveData<List<CardItem>> getCardItemsNature() {
        natureBuilder();
        return this.cardItemsNature;
    }

    private void natureBuilder() {
        ArrayList<CardItem> list = new ArrayList<>();
        this.cardItemsNature = new MutableLiveData<>();
        setValue();
        if(cardItems.getValue() != null) {
            for (CardItem x : this.cardItems.getValue()) {
                String genere = x.getBookType();
                if (genere.equals("Natura")) {
                    list.add(x);
                }
            }
        }
        this.cardItemsNature.setValue(list);
    }

    public LiveData<List<CardItem>> getCardItemsFantasy() {
        fantasyBuilder();
        return this.cardItemsFantasy;
    }

    private void fantasyBuilder() {
        ArrayList<CardItem> list = new ArrayList<>();
        this.cardItemsFantasy = new MutableLiveData<>();
        setValue();
        if(cardItems.getValue() != null) {
            for (CardItem x : this.cardItems.getValue()) {
                String genere = x.getBookType();
                if (genere.equals("Fantasy")) {
                    list.add(x);
                }
            }
        }
        this.cardItemsFantasy.setValue(list);
    }

    public MutableLiveData<List<CardItem>> getCardItemsIta() {
        itaBuilder();
        return this.cardItemsIta;
    }

    private void itaBuilder() {
        ArrayList<CardItem> list = new ArrayList<>();
        this.cardItemsIta = new MutableLiveData<>();
        setValue();
        if(cardItems.getValue() != null) {
            for (CardItem x : this.cardItems.getValue()) {
                String lingua = x.getBookLang();
                if (lingua.equals("Italiano")) {
                    list.add(x);
                }
            }
        }
        this.cardItemsIta.setValue(list);
    }

    public MutableLiveData<List<CardItem>> getCardItemsUk() {
        ukBuilder();
        return this.cardItemsUk;
    }

    private void ukBuilder() {
        ArrayList<CardItem> list = new ArrayList<>();
        this.cardItemsUk = new MutableLiveData<>();
        setValue();
        if(cardItems.getValue() != null) {
            for (CardItem x : this.cardItems.getValue()) {
                String lingua = x.getBookLang();
                if (lingua.equals("Inglese (UK)")) {
                    list.add(x);
                }
            }
        }
        this.cardItemsUk.setValue(list);
    }

    public MutableLiveData<List<CardItem>> getCardItemsUsa() {
        usaBuilder();
        return this.cardItemsUsa;
    }

    private void usaBuilder() {
        ArrayList<CardItem> list = new ArrayList<>();
        this.cardItemsUsa = new MutableLiveData<>();
        setValue();
        if(cardItems.getValue() != null) {
            for (CardItem x : this.cardItems.getValue()) {
                String lingua = x.getBookLang();
                if (lingua.equals("Inglese (USA)")) {
                    list.add(x);
                }
            }
        }
        this.cardItemsUsa.setValue(list);
    }

    public void addCardItem(CardItem item){
        /*
        ArrayList<CardItem> list = new ArrayList<>();
        list.add(item);
        if (cardItems.getValue() != null){
            list.addAll(cardItems.getValue());
        }

         */
        if (cardItems.getValue() != null){
            //cardItems = new MutableLiveData<>();
            cardItems.getValue().add(0, item);
        }
        //cardItems.setValue(list);



        repository.addCardItem(item);
        //setValue();
    }

    public void removeCardItem(CardItem cardItem){
        if (cardItems.getValue() != null){
    //        cardItems = new MutableLiveData<>();
          cardItems.getValue().remove(cardItem);
        }
            //    else {
    //        ArrayList<CardItem> list = new ArrayList<>(cardItems.getValue());
    //        list.remove(cardItem);
            //cardItems.setValue(list);

            repository.removeCardItem(cardItem);
            //repository.getCardItemList().getValue().remove(cardItem);
            //repository.getCardItemList().getValue().addAll(list);
    //    }
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

    public LiveData<List<CardItem>> getCardItemsRaw() {
        return this.cardItems;
    }
}


