package com.example.books4all;

import static com.example.books4all.Utilities.REQUEST_IMAGE_CAPTURE;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.books4all.RecyclerView.CardAdapter;
import com.example.books4all.ViewModel.AddViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AddViewModel addViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            Utilities.insertFragment(this, new HomeFragment(), HomeFragment.class.getSimpleName());

            addViewModel = new ViewModelProvider(this).get(AddViewModel.class);
        }
        //addViewModel = new ViewModelProvider(this).get(AddViewModel.class);
        //setRecyclerView(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_app_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        /*
        if(item.getItemId() == R.id.app_bar_filter){
            Intent intent = new Intent(this, FilterActivity.class);
            this.startActivity(intent);

            return true;
        }*/
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            if (extras != null){
                Bitmap imageBitmap = (Bitmap) extras.get("data");

                addViewModel.setImageBitmap(imageBitmap);
            }
        }
    }
}