package com.example.books4all;

import static com.example.books4all.Utilities.REQUEST_IMAGE_CAPTURE;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.books4all.ViewModel.AddViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_book, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Activity activity = getActivity();

        if (activity != null) {
            Utilities.setUpToolBar((AppCompatActivity) activity, "Add Book");

            view.findViewById(R.id.capture_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
                        activity.startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                    }
                }
            });

            AddViewModel addViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(AddViewModel.class);
            ImageView imageView = view.findViewById(R.id.book_image);
            addViewModel.getImageBitmap().observe(getViewLifecycleOwner(), new Observer<Bitmap>() {
                @Override
                public void onChanged(Bitmap bitmap) {
                    imageView.setImageBitmap(bitmap);
                }
            });

            TextInputEditText bookTIET = view.findViewById(R.id.book_edittext);
            TextInputEditText summaryTIET = view.findViewById(R.id.summary_edittext);
            TextInputEditText pageTIET = view.findViewById(R.id.page_edittext);
            view.findViewById(R.id.fab_add).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bitmap bitmap = addViewModel.getImageBitmap().getValue();

                    String imageUriString;
                    try{
                        if (bitmap != null){
                            imageUriString = String.valueOf(saveImage(bitmap, activity));
                        } else {
                            imageUriString = "ic_baseline_android_24";
                        }
                        if (bookTIET.getText() != null && summaryTIET.getText() != null && pageTIET.getText() != null){
                            addViewModel.addCardItem(new CardItem(imageUriString, bookTIET.getText().toString(),
                                    summaryTIET.getText().toString(),
                                    Integer.parseInt(pageTIET.getText().toString())));

                            addViewModel.setImageBitmap(null);

                            ((AppCompatActivity)activity).getSupportFragmentManager().popBackStack();
                        }
                    } catch (FileNotFoundException e){
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private Uri saveImage(Bitmap bitmap, Activity activity) throws FileNotFoundException {
        String timestamp = new SimpleDateFormat("yyyMMdd_HHmmss", Locale.ITALY).format(new Date());
        String name = "JPEG_" + timestamp + ".jpg";

        ContentResolver contentResolver = activity.getContentResolver();

        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, name);
        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg");

        Uri imageURI = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);

        Log.d("AddFragment", String.valueOf(imageURI));

        OutputStream outputStream = contentResolver.openOutputStream(imageURI);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);

        try {
            outputStream.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        return imageURI;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        menu.findItem(R.id.app_bar_search).setVisible(false);
        menu.findItem(R.id.app_bar_filter).setVisible(false);
        //menu.findItem(R.id.app_bar_gps).setVisible(false);
    }
}
