package com.example.books4all;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.books4all.ViewModel.ListViewModel;

public class DetailsFragment extends Fragment {

    private TextView bookTitle;
    private TextView summaryTextView;
    private TextView pageTextView;
    private ImageView bookImage;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Activity activity = getActivity();
        if (activity != null) {
            Utilities.setUpToolBar((AppCompatActivity) activity, "Details");

            bookTitle = view.findViewById(R.id.book_title);
            summaryTextView = view.findViewById(R.id.book_summary);
            pageTextView = view.findViewById(R.id.page_number);
            bookImage = view.findViewById(R.id.book_image);

            ListViewModel listViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(ListViewModel.class);
            listViewModel.getItemSelected().observe(getViewLifecycleOwner(), new Observer<CardItem>() {
                @Override
                public void onChanged(CardItem cardItem) {
                    bookTitle.setText(cardItem.getBookName());
                    summaryTextView.setText(cardItem.getBookSummary());
                    pageTextView.setText(String.valueOf(cardItem.getBookPage()));
                    String image_path = cardItem.getImageResource();
                    if(image_path.contains("ic_")){
                        Drawable drawable = ResourcesCompat.getDrawable(activity.getResources(), R.drawable.ic_baseline_android_24,
                                activity.getTheme());
                        bookImage.setImageDrawable(drawable);
                    } else {
                        Bitmap bitmap = Utilities.getImageBitmap(activity, Uri.parse(image_path));
                        if(bitmap != null){
                            bookImage.setImageBitmap(bitmap);
                            bookImage.setBackgroundColor(Color.WHITE);
                        }
                    }
                }
            });
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        menu.findItem(R.id.app_bar_search).setVisible(false);
        menu.findItem(R.id.app_bar_filter).setVisible(false);
        //menu.findItem(R.id.app_bar_gps).setVisible(false);
    }
}
