package com.example.books4all.ViewModel;

import static com.example.books4all.Utilities.drawableToBitmap;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.books4all.CardItem;
import com.example.books4all.Database.CardItemRepository;
import com.example.books4all.R;

import java.util.ArrayList;

public class AddViewModel extends AndroidViewModel {

    //ArrayList<CardItem> allBook = new ArrayList<>();
    //private final Application application;

    private final MutableLiveData<Bitmap> imageBitmap = new MutableLiveData<>();
    private CardItemRepository repository;

    public AddViewModel(@NonNull Application application) {
        super(application);
        repository = new CardItemRepository(application);

        //this.application = application;
        //initializeBitmap();
    }

    public void setImageBitmap (Bitmap bitmap){
        imageBitmap.setValue(bitmap);
    }

    public LiveData<Bitmap> getImageBitmap(){
        return imageBitmap;
    }

    public void addCardItem(CardItem item){
        if(item.getBookPage()==null) {
            item.setBookPage(0);
            repository.addCardItem(item);
        }
        //this.allBook.add(item);
    }

    /*
    public ArrayList<CardItem> getAllBook() {
        return allBook;
    }

     */
}
