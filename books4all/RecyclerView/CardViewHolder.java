package com.example.books4all.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.books4all.R;

public class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    ImageView bookImage;
    TextView bookTitle;
    //TextView bookDesc;
    //TextView date;

    private final OnItemListener itemListener;

    public CardViewHolder(@NonNull View itemView, OnItemListener listener) {
        super(itemView);
        bookImage = itemView.findViewById(R.id.book_image);
        bookTitle = itemView.findViewById(R.id.book_title);
        //bookDesc = itemView.findViewById(R.id.book_description);
        //this.date = date;

        itemListener = listener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        itemListener.onItemClick(getAdapterPosition());
    }
}
