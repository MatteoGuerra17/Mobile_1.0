package com.example.books4all;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.books4all.ViewModel.AddViewModel;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;

public class FilterActivity extends AppCompatActivity {

    private Chip chipLess100, chipMore100, chipNature, chipCrime, chipFantasy;
    private Button btnApply;
    private ArrayList<String> selectedChip = new ArrayList<>();
    private AddViewModel addViewModel;
    private ArrayList<CardItem> listLess100;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        chipCrime = findViewById(R.id.chipCrime);
        chipNature = findViewById(R.id.chipNature);
        chipFantasy = findViewById(R.id.chipFantasy);




        CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    selectedChip.add(compoundButton.getText().toString());
                } else{
                    selectedChip.remove(compoundButton.getText().toString());
                }
            }
        };
        /*
        chipMore100.setOnCheckedChangeListener(checkedChangeListener);
        chipLess100.setOnCheckedChangeListener(checkedChangeListener);
        chipFantasy.setOnCheckedChangeListener(checkedChangeListener);
        chipNature.setOnCheckedChangeListener(checkedChangeListener);
        chipCrime.setOnCheckedChangeListener(checkedChangeListener);

        btnApply = findViewById(R.id.buttonApplica);
        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
         */
    }

    @Override
    public void finish() {
        super.finish();
    }
}
